package com.xcell.GTManager.model.services;

import com.xcell.GTManager.dto.HouseholdDto;
import com.xcell.GTManager.dto.HouseholdHistoryDto;
import com.xcell.GTManager.model.repositories.DimHouseholdRepository;
import com.xcell.GTManager.model.repositories.HouseholdRepository;
import com.xcell.GTManager.model.repositories.PersonRepository;
import com.xcell.GTManager.model.tables.DimHousehold;
import com.xcell.GTManager.model.tables.Household;
import com.xcell.GTManager.model.tables.Person;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@Transactional
public class HouseholdService {

    private final HouseholdRepository householdRepo;
    private final DimHouseholdRepository dimRepo;
    private final PersonRepository personRepo;

    public HouseholdService(
            HouseholdRepository householdRepo,
            DimHouseholdRepository dimRepo,
            PersonRepository personRepo
    ) {
        this.householdRepo = householdRepo;
        this.dimRepo = dimRepo;
        this.personRepo = personRepo;
    }

    private void createNewHistoryRecord(Household h) {
        DimHousehold d = new DimHousehold();
        d.copyFrom(h);
        d.setValidFrom(LocalDateTime.now());
        d.setValidTo(null);

        dimRepo.save(d);
    }

    public void create(Household h) {
        if(h.getHouseholdId() != null)
            throw new IllegalArgumentException("Household IDs are automatically generated.");

        Household saved = householdRepo.saveAndFlush(h);
        createNewHistoryRecord(saved);
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

    public HouseholdDto getCurrent(Integer id) {
        Household h = householdRepo.findById(id).orElseThrow();
        return HouseholdDto.fromEntity(h);
    }

    public List<HouseholdHistoryDto> getHistory(Integer id) {
        return dimRepo.findAll().stream()
                .filter(d -> d.getHouseholdId().equals(id))
                .map(HouseholdHistoryDto::fromEntity)
                .toList();
    }

    public List<HouseholdDto> getAll(){
        return householdRepo.findAll().stream().map(HouseholdDto::fromEntity).toList();
    }

    public long getMemberCount(Integer householdId){
        return personRepo.countByHouseholdHouseholdId(householdId);
    }

    public List<Person> getMembers(Integer householdId){
        return personRepo.findByHouseholdHouseholdId(householdId);
    }
}
