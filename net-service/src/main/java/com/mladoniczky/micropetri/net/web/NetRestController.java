package com.mladoniczky.micropetri.net.web;

import com.mladoniczky.micropetri.net.model.Net;
import com.mladoniczky.micropetri.net.service.INetService;
import com.mladoniczky.micropetri.net.service.export.IExportService;
import com.mladoniczky.micropetri.net.service.imports.IImportService;
import com.mladoniczky.micropetri.net.web.resource.NetResource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/nets")
public class NetRestController {

    private final INetService netService;
    private final IImportService importService;
    private final IExportService exportService;

    public NetRestController(INetService netService, IImportService importService, IExportService exportService) {
        this.netService = netService;
        this.importService = importService;
        this.exportService = exportService;
    }

    @GetMapping
    public Page<NetResource> getAllNets(@RequestParam(name = "name", required = false) String name, Pageable pageable) throws InterruptedException {
        Page<Net> nets;
        if (name != null && name.length() > 0)
            nets = netService.getNets(name, pageable);
        else
            nets = netService.getAllNets(pageable);
        return new PageImpl<>(exportService.export(nets.getContent()), nets.getPageable(), nets.getTotalElements());
    }

    @PostMapping
    public NetResource createNet(@RequestBody @Valid NetResource net) throws InterruptedException {
        return exportService.export(netService.save(importService.importNet(net)));
    }

    @PutMapping
    public NetResource updateNet(@RequestBody @Valid NetResource net) throws InterruptedException {
        return exportService.export(netService.update(importService.importNet(net)));
    }

    @PatchMapping
    public NetResource partiallyUpdateNet(@RequestBody NetResource net) throws InterruptedException {
        return exportService.export(netService.patch(importService.importNet(net)));
    }

    @DeleteMapping
    public boolean deleteAllNets() {
        return netService.deleteAll();
    }

    @GetMapping("/{id}")
    public NetResource getNet(@PathVariable String id) throws InterruptedException {
        return exportService.export(netService.getNet(id).orElse(null));
    }

    @DeleteMapping("/{id}")
    public boolean deleteNet(@PathVariable String id) {
        return netService.delete(id);
    }

    @PutMapping("/{id}")
    public NetResource updateNet(@PathVariable String id, @RequestBody @Valid NetResource net) throws InterruptedException {
        net.setId(id);
        return exportService.export(netService.update(importService.importNet(net)));
    }

    @PatchMapping("/{id}")
    public NetResource patchNet(@PathVariable String id, @RequestBody NetResource net) throws InterruptedException {
        net.setId(id);
        return exportService.export(netService.patch(importService.importNet(net)));
    }
}
