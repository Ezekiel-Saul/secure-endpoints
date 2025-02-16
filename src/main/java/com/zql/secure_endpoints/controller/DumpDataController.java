package com.zql.secure_endpoints.controller;


import com.zql.secure_endpoints.entity.DumpData;
import com.zql.secure_endpoints.service.DumpDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/dump")
public class DumpDataController {

    @Autowired
    private DumpDataService dumpDataService;

    @GetMapping
    public List<DumpData> getAllDumpData() {
        return dumpDataService.getAllDumpData();
    }

    @PostMapping
    @PreAuthorize("hasAnyRole('ADMIN', 'MANAGER')")
    public DumpData addDumpData(@RequestBody DumpData data) {
        return dumpDataService.addDumpData(data);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN', 'MANAGER')")
    public DumpData updateDumpData(@PathVariable Long id, @RequestBody DumpData data) {
        DumpData updatedData = dumpDataService.updateDumpData(id, data);
        if (updatedData == null) {
            throw new RuntimeException("Dump data not found with id: " + id);
        }
        return updatedData;
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public void removeDumpData(@PathVariable Long id) {
        dumpDataService.removeDumpData(id);
    }
}
