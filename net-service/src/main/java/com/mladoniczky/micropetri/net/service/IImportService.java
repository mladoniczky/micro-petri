package com.mladoniczky.micropetri.net.service;

import com.mladoniczky.micropetri.petri4j.Net;
import com.mladoniczky.micropetri.net.web.model.NetResource;

public interface IImportService {
    Net importNet(NetResource netResource) throws InterruptedException;
}
