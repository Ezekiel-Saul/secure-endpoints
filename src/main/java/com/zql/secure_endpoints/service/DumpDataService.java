package com.zql.secure_endpoints.service;

import com.zql.secure_endpoints.entity.DumpData;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class DumpDataService {
    private List<DumpData> dumpDataList = new ArrayList<>();

    public List<DumpData> getAllDumpData() {
        return new ArrayList<>(dumpDataList);
    }

    public DumpData addDumpData(DumpData data) {
        data.setId((long) (dumpDataList.size() + 1)); // Simple ID generation
        dumpDataList.add(data);
        return data;
    }

    public DumpData updateDumpData(Long id, DumpData data) {
        Optional<DumpData> dumpData = dumpDataList.stream().filter(d -> d.getId().equals(id)).findFirst();
        if (dumpData.isPresent()) {
            DumpData existingData = dumpData.get();
            existingData.setData(data.getData());
            return existingData;
        }
        return null;
    }

    public void removeDumpData(Long id) {
        dumpDataList.removeIf(d -> d.getId().equals(id));
    }
}
