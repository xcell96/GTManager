package com.xcell.GTManager.model.services;

import com.xcell.GTManager.model.repositories.DimHouseholdRepository;
import com.xcell.GTManager.model.repositories.DimPersonRepository;
import com.xcell.GTManager.model.repositories.PersonRepository;
import com.xcell.GTManager.model.tables.DimHousehold;
import com.xcell.GTManager.model.tables.DimPerson;
import com.xcell.GTManager.model.tables.Person;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@Transactional
public class PeopleService {

    private final PersonRepository personRepo;
    private final DimPersonRepository dimRepo;
    private final DimHouseholdRepository dimHouseholdRepo;

    public PeopleService(PersonRepository personRepo, DimPersonRepository dimRepo, DimHouseholdRepository dimHouseholdRepo) {
        this.personRepo = personRepo;
        this.dimRepo = dimRepo;
        this.dimHouseholdRepo = dimHouseholdRepo;
    }

    private void createNewHistoryRecord(Person p) {
        DimPerson d = new DimPerson();
        d.copyFrom(p);
        d.setPersonId(p.getPersonId());

        //household?
        DimHousehold h = dimHouseholdRepo.findByHouseholdIdAndValidToIsNull(p.getHousehold().getHouseholdId()).orElseThrow();
        d.setHousehold(h);

        d.setValidFrom(LocalDateTime.now());
        d.setValidTo(null);

        dimRepo.save(d);
    }

    public void create(Person p) {
        if(personRepo.existsById(p.getPersonId()))
            throw new IllegalArgumentException("Person with ID " + p.getPersonId() + " already exists");

        personRepo.save(p);
        createNewHistoryRecord(p);
    }

    public void update(Integer id, Person newData){
        if(!personRepo.existsById(id))
            throw new IllegalArgumentException("Person with ID " + id + " doesn't exist");

        Person p = personRepo.findById(id).orElseThrow();
        p.copyFrom(newData);
        personRepo.save(p);

        DimPerson prev = dimRepo.findByPersonIdAndValidToIsNull(id).orElseThrow();
        prev.setValidTo(LocalDateTime.now());
        dimRepo.save(prev);

        createNewHistoryRecord(p);
    }

    public void delete(Integer id) {
        personRepo.deleteById(id);

        DimPerson last = dimRepo.findByPersonIdAndValidToIsNull(id).orElseThrow();
        last.setValidTo(LocalDateTime.now());
        dimRepo.save(last);
    }
}
