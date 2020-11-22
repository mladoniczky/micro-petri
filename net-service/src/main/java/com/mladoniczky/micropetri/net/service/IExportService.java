package com.mladoniczky.micropetri.net.service;

import com.mladoniczky.micropetri.petri4j.Net;
import com.mladoniczky.micropetri.net.web.model.NetResource;

import java.util.Collection;
import java.util.List;

public interface IExportService {
    List<NetResource> export(Collection<Net> nets) throws InterruptedException;

    NetResource export(Net net) throws InterruptedException;
}
