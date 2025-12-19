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

/**
 * Provides business logic for managing {@link Person} entities.
 * <p>
 * This service ensures that, for each CRUD operation performed on a Person entity,
 * a corresponding record is added to the {@link DimPerson} table via the {@link DimPersonRepository}.
 * This service uses the {@link PersonRepository} to perform CRUD operations on people records,
 * the {@link HouseholdRepository} to retrieve household records
 * and the {@link DimHouseholdRepository} to retrieve historical household records.
 * <p>
 * TODO: check that the CNP is unique; perhaps use that as a PK in {@link Person}
 */
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

    /**
     * Creates a new record in the {@link DimPerson} table for the given person.
     * <p>
     * It invalidates the most recent historical record for the person by setting its {@code validTo} field to {@code now()}
     * and creates a new record with {@code validFrom} set to {@code now()}.
     * @param p the person to create a record for
     */
    private void createNewHistoryRecord(Person p) {
        DimPerson d = new DimPerson();
        d.copyFrom(p);

        DimHousehold h = dimHouseholdRepo.findByHouseholdIdAndValidToIsNull(p.getHousehold().getHouseholdId()).orElseThrow();
        d.setHousehold(h);

        d.setValidFrom(LocalDateTime.now());
        d.setValidTo(null);

        dimRepo.save(d);
    }

    /**
     * Used to transfer data from a {@link PersonDto} to a {@link Person} entity.
     * @param target the Person entity to transfer the data to
     * @param dto the PersonDto containing the data to transfer
     * @param household the household the person supposedly belongs to
     */
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

    /**
     * Creates a new person record and adds a corresponding record to the {@link DimPerson} table.
     * <p>
     * The person must not have an ID already set, since IDs are generated automatically.
     * The person must belong to a household.
     *
     * @param dto the PersonDto containing the data to create the record with
     * @throws IllegalArgumentException if the person already has an ID or if the person doesn't belong to a household
     */
    public void create(PersonDto dto) throws IllegalArgumentException{
        if(dto.getPersonId() != null)
            throw new IllegalArgumentException("Person IDs are automatically generated.");

        if(dto.getHouseholdId() == null)
            throw new IllegalArgumentException("Person must belong to a household.");

        Household household = householdRepo.findById(dto.getHouseholdId()).orElseThrow();

        Person p = new Person();
        applyDto(p, dto, household);

        Person saved = personRepo.saveAndFlush(p);
        createNewHistoryRecord(saved);
    }

    /**
     * Updates an existing person record with new data.
     * <p>
     * The person must already exist in the database.
     * The person must belong to a household.
     *
     * @param id the ID of the person record to update
     * @param dto the PersonDto containing the new data to update the record with
     * @throws IllegalArgumentException if the person doesn't exist or if the person doesn't belong to a household
     */
    public void update(Integer id, PersonDto dto) throws IllegalArgumentException {
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

    /**
     * Deletes a person record with the given ID and invalidates the corresponding historical record.
     * <p>
     * The person must already exist in the database.
     *
     * @param id the ID of the person record to delete
     * @throws IllegalArgumentException if the person doesn't exist
     */
    public void delete(Integer id) throws IllegalArgumentException {
        if(!personRepo.existsById(id))
            throw new IllegalArgumentException("Person with ID " + id + " doesn't exist");

        personRepo.deleteById(id);

        DimPerson last = dimRepo.findByPersonIdAndValidToIsNull(id).orElseThrow();
        last.setValidTo(LocalDateTime.now());
        dimRepo.save(last);
    }

    /**
     * Retrieves all people records from the database.
     * @return a list of all people records
     */
    public List<PersonDto> getAll(){
        return personRepo.findAll().stream().map(PersonDto::fromEntity).toList();
    }

    /**
     * Retrieves a person record with the given ID.
     * @param id the ID of the person record to retrieve
     * @return the person record with the given ID
     * @throws IllegalArgumentException if the person record with the given ID doesn't exist
     */
    public PersonDto getCurrent(Integer id) throws IllegalArgumentException {
        if(!personRepo.existsById(id))
            throw new IllegalArgumentException("Person with ID " + id + " doesn't exist");

        return PersonDto.fromEntity(personRepo.findById(id).orElseThrow());
    }

    /**
     * Retrieves all historical records for a person with the given ID.
     * <p>
     * The person must already exist in the database.
     *
     * @param id the ID of the person to retrieve historical records for
     * @return a list of historical records for the person
     * @throws IllegalArgumentException if the person with the given ID doesn't exist
     */
    public List<PersonHistoryDto> getHistory(Integer id) throws IllegalArgumentException {
        if(!personRepo.existsById(id))
            throw new IllegalArgumentException("Person with ID " + id + " doesn't exist");

        return dimRepo.findByPersonIdOrderByValidFromDesc(id).stream()
                .map(PersonHistoryDto::fromEntity)
                .toList();
    }
}
