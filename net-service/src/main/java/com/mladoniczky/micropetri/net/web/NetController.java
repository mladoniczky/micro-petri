package com.mladoniczky.micropetri.net.web;

import com.mladoniczky.micropetri.petri4j.Net;
import com.mladoniczky.micropetri.net.service.IExportService;
import com.mladoniczky.micropetri.net.service.IImportService;
import com.mladoniczky.micropetri.net.service.INetService;
import com.mladoniczky.micropetri.net.web.model.NetResource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Arrays;

@RestController
@RequestMapping("/api/net")
public class NetController {

    private final INetService netService;
    private final IImportService importService;
    private final IExportService exportService;

    public NetController(INetService netService, IImportService importService, IExportService exportService) {
        this.netService = netService;
        this.importService = importService;
        this.exportService = exportService;
    }

    @GetMapping("/{id}")
    public NetResource getNet(@PathVariable String id) throws InterruptedException {
        return exportService.export(netService.getNet(id).orElse(null));
    }

    @GetMapping("/{ids}")
    public Iterable<NetResource> getNets(@PathVariable String[] ids) throws InterruptedException {
        return exportService.export(netService.getNets(Arrays.asList(ids)));
    }

    @GetMapping("/name/{name}")
    public Page<NetResource> getNets(@PathVariable String name, Pageable pageable) throws InterruptedException {
        Page<Net> nets = netService.getNets(name, pageable);
        return new PageImpl<>(exportService.export(nets.getContent()), nets.getPageable(), nets.getTotalElements());
    }

    @DeleteMapping("/{id}")
    public boolean deleteNet(@PathVariable String id) {
        return netService.delete(id);
    }

    @PostMapping
    public NetResource createNet(@RequestBody @Valid NetResource net) throws InterruptedException {
        return exportService.export(netService.save(importService.importNet(net)));
    }

    @PostMapping("/{id}")
    public NetResource updateNet(@PathVariable String id, @RequestBody @Valid NetResource net) throws InterruptedException {
        net.setId(id);
        return exportService.export(netService.update(importService.importNet(net)));
    }
}
