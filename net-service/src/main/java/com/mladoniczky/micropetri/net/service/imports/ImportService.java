package com.mladoniczky.micropetri.net.service.imports;


import com.mladoniczky.micropetri.net.model.Net;
import com.mladoniczky.micropetri.net.web.resource.Arc;
import com.mladoniczky.micropetri.net.web.resource.NetResource;
import com.mladoniczky.micropetri.net.web.resource.Place;
import com.mladoniczky.micropetri.net.web.resource.Transition;
import com.mladoniczky.micropetri.petri4j.arc.input.InhibitorArc;
import com.mladoniczky.micropetri.petri4j.arc.input.ReadArc;
import com.mladoniczky.micropetri.petri4j.arc.input.RegularInputArc;
import com.mladoniczky.micropetri.petri4j.arc.input.ResetArc;
import com.mladoniczky.micropetri.petri4j.arc.output.RegularOutputArc;
import org.bson.types.ObjectId;
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

        ExecutorService executorService = Executors.newFixedThreadPool(2);

        executorService.execute(() -> {
            net.getPlaces().putAll(netResource.getPlaces().stream()
                    .collect(Collectors.toMap(Place::getId, (Place p) ->
                            new com.mladoniczky.micropetri.net.model.Place(p.getId(), p.getName(), p.getResources().longValue())
                    )));
        });
        executorService.execute(() -> {
            net.getTransitions().putAll(netResource.getTransitions().stream()
                    .collect(Collectors.toMap(Transition::getId, (Transition t) ->
                            new com.mladoniczky.micropetri.net.model.Transition(t.getId(), t.getName()))));
        });

        executorService.shutdown();
        executorService.awaitTermination(Integer.MAX_VALUE, TimeUnit.SECONDS);

        net.getArcs().putAll(netResource.getArcs().stream()
                .collect(Collectors.toMap(Arc::getId, (Arc a) -> createArc(a, net))));

//        net.makeExecutable();
        if (netResource.getId() != null && netResource.getId().length() > 0)
            net.setId(new ObjectId(netResource.getId()));
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
                if (isOutput)
                    throw new IllegalArgumentException("Reset arc " + a.getId() + " cannot be output arc!");
                return new ResetArc(a.getId(),
                        net.getPlaces().get(a.getSource()),
                        net.getTransitions().get(a.getTarget()));
            case READ:
                //throw new UnsupportedOperationException("Arc type " + a.getType() + " is not supported,yet.");
                if (isOutput)
                    throw new IllegalArgumentException("Read arc " + a.getId() + " cannot be output arc!");
                return new ReadArc(a.getId(),
                        net.getPlaces().get(a.getSource()),
                        net.getTransitions().get(a.getTarget()),
                        a.getWeight().longValue());
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
