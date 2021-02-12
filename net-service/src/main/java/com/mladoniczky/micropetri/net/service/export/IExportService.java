package com.mladoniczky.micropetri.net.service.export;


import com.mladoniczky.micropetri.net.model.Net;
import com.mladoniczky.micropetri.net.web.resource.NetResource;

import java.util.Collection;
import java.util.List;

public interface IExportService {
    List<NetResource> export(Collection<Net> nets) throws InterruptedException;

    NetResource export(Net net) throws InterruptedException;
}
