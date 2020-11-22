package com.mladoniczky.micropetri.net.service;

import com.mladoniczky.micropetri.petri4j.Net;
import com.mladoniczky.micropetri.net.repository.NetRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class NetService implements INetService {

    private final NetRepository repository;

    public NetService(NetRepository repository) {
        this.repository = repository;
    }

    @Override
    public Net save(Net net) {
        return repository.save(net);
    }

    @Override
    public Net update(Net net) {
        if (net.getId() == null)
            throw new IllegalArgumentException("Net " + net.getName() + " has not defined id!");
        if (!repository.existsById(net.getStringId()))
            throw new IllegalArgumentException("Net [" + net.getStringId() + "] " + net.getName() + " was not found!");
        return repository.save(net);
    }

    @Override
    public boolean delete(String id) {
        repository.deleteById(id);
        return !repository.existsById(id);
    }

    @Override
    public Optional<Net> getNet(String id) {
        return repository.findById(id);
    }

    @Override
    public List<Net> getNets(Iterable<String> ids) {
        return StreamSupport.stream(repository.findAllById(ids).spliterator(), false).collect(Collectors.toList());
    }

    @Override
    public Page<Net> getNets(String name, Pageable pageable) {
        return repository.findAllByName(name, pageable);
    }

}
