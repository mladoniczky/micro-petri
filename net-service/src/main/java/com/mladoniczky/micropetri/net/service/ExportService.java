package com.mladoniczky.micropetri.net.service;

import com.mladoniczky.micropetri.petri4j.Net;
import com.mladoniczky.micropetri.petri4j.arc.ArcType;
import com.mladoniczky.micropetri.net.web.model.Arc;
import com.mladoniczky.micropetri.net.web.model.NetResource;
import com.mladoniczky.micropetri.net.web.model.Place;
import com.mladoniczky.micropetri.net.web.model.Transition;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.*;
import java.util.stream.Collectors;

@Slf4j
@Service
public class ExportService implements IExportService {

    @Override
    public List<NetResource> export(Collection<Net> nets) throws InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool(nets.size());
        List<Future<NetResource>> futureList = executorService.invokeAll(nets.stream()
                .map(net -> (Callable<NetResource>) () -> export(net))
                .collect(Collectors.toList()));
        executorService.shutdown();
        executorService.awaitTermination(Integer.MAX_VALUE, TimeUnit.SECONDS);
        return futureList.stream().filter(Future::isDone)
                .map(f -> {
                    try {
                        return f.get();
                    } catch (InterruptedException | ExecutionException e) {
                        log.error(e.getMessage(), e);
                        return null;
                    }
                })
                .filter(Objects::nonNull)
                .collect(Collectors.toList());
    }

    @Override
    public NetResource export(Net net) throws InterruptedException {
        if (net == null)
            return null;

        NetResource resource = new NetResource(net.getStringId(), net.getName(), null, null, null);

        ExecutorService executorService = Executors.newFixedThreadPool(3);

        executorService.execute(() -> {
            resource.setPlaces(net.getPlaces().values()
                    .stream().map(p -> new Place(p.getId(), p.getName(), p.getResources().intValue()))
                    .collect(Collectors.toList()));
        });
        executorService.execute(() -> {
            resource.setTransitions(net.getTransitions().values()
                    .stream().map(t -> new Transition(t.getId(), t.getName()))
                    .collect(Collectors.toList()));
        });
        executorService.execute(() -> {
            resource.setArcs(net.getArcs().values()
                    .stream().map(a -> {
                        Arc arc = new Arc(a.getId(), getArcType(a.getType()), a.getSource().getId(), a.getTarget().getId(), null);
                        try {
                            arc.setWeight(getWeight(a).intValue());
                        } catch (NoSuchMethodException | InvocationTargetException | IllegalAccessException e) {
                            log.error(e.getMessage(), e);
                        }
                        return arc;
                    })
                    .collect(Collectors.toList()));
        });

        executorService.shutdown();
        executorService.awaitTermination(Integer.MAX_VALUE, TimeUnit.SECONDS);

        return resource;
    }

    private Arc.Type getArcType(ArcType type) {
        return Arc.Type.valueOf(type.toString().toUpperCase());
    }

    private Long getWeight(com.mladoniczky.micropetri.petri4j.arc.Arc arc) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Method method = arc.getClass().getMethod("getWeight");
        return (Long) method.invoke(arc);
    }

}
