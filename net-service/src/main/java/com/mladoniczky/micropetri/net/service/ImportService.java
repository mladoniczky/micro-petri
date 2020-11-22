package com.mladoniczky.micropetri.net.service;

import com.mladoniczky.micropetri.petri4j.Net;
import com.mladoniczky.micropetri.petri4j.arc.input.InhibitorArc;
import com.mladoniczky.micropetri.petri4j.arc.input.RegularInputArc;
import com.mladoniczky.micropetri.petri4j.arc.output.RegularOutputArc;
import com.mladoniczky.micropetri.net.web.model.Arc;
import com.mladoniczky.micropetri.net.web.model.NetResource;
import com.mladoniczky.micropetri.net.web.model.Place;
import com.mladoniczky.micropetri.net.web.model.Transition;
import org.springframework.stereotype.Service;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

@Service
public class ImportService implements IImportService {

    @Override
    public Net importNet(NetResource netResource) throws InterruptedException {
        if (netResource == null)
            return null;

        Net net = new Net(netResource.getName());
        net.setId(netResource.getId());

        ExecutorService executorService = Executors.newFixedThreadPool(2);

        executorService.execute(() -> {
            net.getPlaces().putAll(netResource.getPlaces().stream()
                    .collect(Collectors.toMap(Place::getId, (Place p) ->
                            new com.mladoniczky.micropetri.petri4j.place.Place(p.getId(), p.getName(), p.getResources().longValue())
                    )));
        });
        executorService.execute(() -> {
            net.getTransitions().putAll(netResource.getTransitions().stream()
                    .collect(Collectors.toMap(Transition::getId, (Transition t) ->
                            new com.mladoniczky.micropetri.petri4j.transition.Transition(t.getId(), t.getName()))));
        });

        executorService.shutdown();
        executorService.awaitTermination(Integer.MAX_VALUE, TimeUnit.SECONDS);

        net.getArcs().putAll(netResource.getArcs().stream()
                .collect(Collectors.toMap(Arc::getId, (Arc a) -> createArc(a, net))));

        net.makeExecutable();
        return net;
    }

    private com.mladoniczky.micropetri.petri4j.arc.Arc createArc(Arc a, Net net) {
        boolean isInput = isInputArc(a, net);
        boolean isOutput = isOutputArc(a, net);
        if (!isInput && !isOutput)
            throw new IllegalStateException("Arc " + a.getId() + " source or target cannot be found in transition or place definition!");
        switch (a.getType()) {
            case REGULAR:
                if (isInput) {
                    return new RegularInputArc(a.getId(),
                            net.getPlaces().get(a.getSource()),
                            net.getTransitions().get(a.getTarget()),
                            a.getWeight().longValue());
                } else {
                    return new RegularOutputArc(a.getId(),
                            net.getTransitions().get(a.getSource()),
                            net.getPlaces().get(a.getTarget()),
                            a.getWeight().longValue());
                }
            case INHIBITOR:
                if (isOutput)
                    throw new IllegalArgumentException("Inhibitor arc " + a.getId() + " cannot be output arc!");
                return new InhibitorArc(a.getId(),
                        net.getPlaces().get(a.getSource()),
                        net.getTransitions().get(a.getTarget()),
                        a.getWeight().longValue());
            case RESET:
            case READ:
                throw new UnsupportedOperationException("Arc type " + a.getType() + " is not supported,yet.");
            default:
                throw new IllegalArgumentException("Cannot find arc of type " + a.getType());
        }
    }

    private boolean isInputArc(Arc arc, Net net) {
        return net.getTransitions().containsKey(arc.getTarget()) && net.getPlaces().containsKey(arc.getSource());
    }

    private boolean isOutputArc(Arc arc, Net net) {
        return net.getTransitions().containsKey(arc.getSource()) && net.getPlaces().containsKey(arc.getTarget());
    }

}
