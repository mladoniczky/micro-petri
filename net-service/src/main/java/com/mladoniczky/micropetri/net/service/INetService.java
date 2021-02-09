package com.mladoniczky.micropetri.net.service;

import com.mladoniczky.micropetri.net.model.Net;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface INetService {
    Net save(Net net);

    Net update(Net net);

    Net patch(Net net);

    boolean delete(String id);

    boolean deleteAll();

    Optional<Net> getNet(String id);

    List<Net> getNets(Iterable<String> ids);

    Page<Net> getNets(String name, Pageable pageable);

    Page<Net> getAllNets(Pageable pageable);
}
