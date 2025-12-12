package com.xcell.GTManager.model.services;

import com.xcell.GTManager.model.repositories.DimHouseholdRepository;
import com.xcell.GTManager.model.repositories.HouseholdRepository;
import com.xcell.GTManager.model.tables.DimHousehold;
import com.xcell.GTManager.model.tables.Household;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@Transactional
public class HouseholdService {

    private final HouseholdRepository householdRepo;
    private final DimHouseholdRepository dimRepo;

    public HouseholdService(HouseholdRepository householdRepo, DimHouseholdRepository dimRepo) {
        this.householdRepo = householdRepo;
        this.dimRepo = dimRepo;
    }

    private void createNewHistoryRecord(Household h) {
        DimHousehold d = new DimHousehold();
        d.copyFrom(h);
        d.setValidFrom(LocalDateTime.now());
        d.setValidTo(null);

        dimRepo.save(d);
    }

    public void create(Household h) {
        if(householdRepo.existsById(h.getHouseholdId()))
            throw new IllegalArgumentException("Household with ID " + h.getHouseholdId() + " already exists");

        householdRepo.save(h);
        createNewHistoryRecord(h);
    }

    public void update(Integer id, Household newData){
        if(!householdRepo.existsById(id))
            throw new IllegalArgumentException("Household with ID " + id + " doesn't exist");

        Household h = householdRepo.findById(id).orElseThrow();
        h.copyFrom(newData);
        householdRepo.save(h);

        DimHousehold prev = dimRepo.findByHouseholdIdAndValidToIsNull(id).orElseThrow();
        prev.setValidTo(LocalDateTime.now());
        dimRepo.save(prev);

        createNewHistoryRecord(h);
    }

    public void delete(Integer id) {
        householdRepo.deleteById(id);

        DimHousehold last = dimRepo.findByHouseholdIdAndValidToIsNull(id).orElseThrow();
        last.setValidTo(LocalDateTime.now());
        dimRepo.save(last);
    }
}
