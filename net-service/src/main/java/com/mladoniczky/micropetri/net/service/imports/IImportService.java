package com.mladoniczky.micropetri.net.service.imports;


import com.mladoniczky.micropetri.net.model.Net;
import com.mladoniczky.micropetri.net.web.model.NetResource;

public interface IImportService {
    Net importNet(NetResource netResource) throws InterruptedException;
}
