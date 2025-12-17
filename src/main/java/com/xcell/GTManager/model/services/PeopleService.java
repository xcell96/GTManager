package com.xcell.GTManager.model.services;

import com.xcell.GTManager.dto.PersonDto;
import com.xcell.GTManager.dto.PersonHistoryDto;
import com.xcell.GTManager.model.repositories.DimHouseholdRepository;
import com.xcell.GTManager.model.repositories.DimPersonRepository;
import com.xcell.GTManager.model.repositories.HouseholdRepository;
import com.xcell.GTManager.model.repositories.PersonRepository;
import com.xcell.GTManager.model.tables.DimHousehold;
import com.xcell.GTManager.model.tables.DimPerson;
import com.xcell.GTManager.model.tables.Household;
import com.xcell.GTManager.model.tables.Person;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@Transactional
public class PeopleService {

    private final PersonRepository personRepo;
    private final DimPersonRepository dimRepo;
    private final DimHouseholdRepository dimHouseholdRepo;
    private final HouseholdRepository householdRepo;

    public PeopleService(
            PersonRepository personRepo,
            DimPersonRepository dimRepo,
            DimHouseholdRepository dimHouseholdRepo,
            HouseholdRepository householdRepo
    ) {
        this.personRepo = personRepo;
        this.dimRepo = dimRepo;
        this.dimHouseholdRepo = dimHouseholdRepo;
        this.householdRepo = householdRepo;
    }

    private void createNewHistoryRecord(Person p) {
        DimPerson d = new DimPerson();
        d.copyFrom(p);

        DimHousehold h = dimHouseholdRepo.findByHouseholdIdAndValidToIsNull(p.getHousehold().getHouseholdId()).orElseThrow();
        d.setHousehold(h);

        d.setValidFrom(LocalDateTime.now());
        d.setValidTo(null);

        dimRepo.save(d);
    }

    private void applyDto(Person target, PersonDto dto, Household household) {
        target.setFirstName(dto.getFirstName());
        target.setLastName(dto.getLastName());
        target.setSex(dto.getSex());
        target.setDateOfBirth(dto.getDateOfBirth());
        target.setCNP(dto.getCNP());
        target.setCitizenship(dto.getCitizenship());
        target.setHousehold(household);
        target.setKinship(dto.getKinship());
        target.setEducationLevel(dto.getEducationLevel());
        target.setJob(dto.getJob());
        target.setPlaceOfWork(dto.getPlaceOfWork());
    }

    public void create(PersonDto dto) {
        if(dto.getPersonId() != null)
            throw new IllegalArgumentException("Person IDs are automatically generated.");

        if(dto.getHouseholdId() == null)
            throw new IllegalArgumentException("Person must belong to a household.");

        Household household = householdRepo.findById(dto.getHouseholdId()).orElseThrow();

        Person p = new Person();
        applyDto(p, dto, household);

        // save and flush - to solve timing faults
        // and force Hibernate to save the changes now, not later
        Person saved = personRepo.saveAndFlush(p);
        createNewHistoryRecord(saved);
    }

    public void update(Integer id, PersonDto dto){
        if(!personRepo.existsById(id))
            throw new IllegalArgumentException("Person with ID " + id + " doesn't exist");

        if(dto.getHouseholdId() == null)
            throw new IllegalArgumentException("Person must belong to a household.");

        Household household = householdRepo.findById(dto.getHouseholdId()).orElseThrow();

        Person p = personRepo.findById(id).orElseThrow();
        applyDto(p, dto, household);
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

    public List<PersonDto> getAll(){
        return personRepo.findAll().stream().map(PersonDto::fromEntity).toList();
    }

    public PersonDto getCurrent(Integer id){
        return PersonDto.fromEntity(personRepo.findById(id).orElseThrow());
    }

    public List<PersonHistoryDto> getHistory(Integer id){
        return dimRepo.findByPersonIdOrderByValidFromDesc(id).stream()
                .map(PersonHistoryDto::fromEntity)
                .toList();
    }
}
