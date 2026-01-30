package com.xcell.GTManager.model.services;

import com.xcell.GTManager.dto.HouseholdDto;
import com.xcell.GTManager.dto.HouseholdHistoryDto;
import com.xcell.GTManager.dto.PersonDto;
import com.xcell.GTManager.model.repositories.DimHouseholdRepository;
import com.xcell.GTManager.model.repositories.HouseholdRepository;
import com.xcell.GTManager.model.repositories.PersonRepository;
import com.xcell.GTManager.model.tables.DimHousehold;
import com.xcell.GTManager.model.tables.Household;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Provides business logic for managing {@link Household} entities.
 * <p>
 * This service ensures that, for each CRUD operation performed on a Household entity,
 * a corresponding record is added to the {@link DimHousehold} table via the {@link DimHouseholdRepository}.
 * This service uses the {@link HouseholdRepository} to perform CRUD operations on household records
 * and the {@link PersonRepository} to retrieve household members.
 */
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

    /**
     * Creates a new record in the {@link DimHousehold} table for the given household.
     * <p>
     * It invalidates the most recent historical record for the household by setting its {@code validTo} field to {@code now()}
     * and creates a new record with {@code validFrom} set to {@code now()}.
     * @param h the household to create a record for
     */
    private void createNewHistoryRecord(Household h) {
        DimHousehold d = new DimHousehold();
        d.copyFrom(h);
        d.setValidFrom(LocalDateTime.now());
        d.setValidTo(null);

        dimRepo.save(d);
    }

    /**
     * Used to transfer data from a {@link HouseholdDto} to a {@link Household} entity.
     * @param target the Household entity to transfer the data to
     * @param dto the HouseholdDto containing the data to transfer
     */
    private void applyDto(Household target, HouseholdDto dto) {
        target.setAddress(dto.getAddress());
        target.setSurface(dto.getSurface());
        target.setCattle(dto.getCattle());
        target.setSwine(dto.getSwine());
        target.setSheep(dto.getSheep());
        target.setGoats(dto.getGoats());
        target.setEquines(dto.getEquines());
        target.setPoultry(dto.getPoultry());
        target.setRabbits(dto.getRabbits());
        target.setDonkeys(dto.getDonkeys());
        target.setBeeFamilies(dto.getBeeFamilies());
        target.setOtherAnimals(dto.getOtherAnimals());
    }

    /**
     * Creates a new household record and adds a corresponding record to the {@link DimHousehold} table.
     * <p>
     * The household must not have an ID already set, since IDs are generated automatically.
     * @param dto the HouseholdDto containing the data to create the record with
     * @throws IllegalArgumentException if the household already has an ID
     */
    public void create(HouseholdDto dto) throws IllegalArgumentException {
        if(dto.getHouseholdId() != null)
            throw new IllegalArgumentException("Household IDs are automatically generated.");

        Household h = new Household();
        applyDto(h, dto);

        Household saved = householdRepo.saveAndFlush(h);
        createNewHistoryRecord(saved);
    }

    /**
     * Updates an already existing household record with new data.
     * <p>
     * The given household data must already exist in the database.
     *
     * @param id the ID of the household record to update
     * @param dto the HouseholdDto containing the new data to update the record with
     * @throws IllegalArgumentException if the household record with the given ID doesn't exist
     */
    public void update(Integer id, HouseholdDto dto) throws IllegalArgumentException {
        if(!householdRepo.existsById(id))
            throw new IllegalArgumentException("Household with ID " + id + " doesn't exist");

        Household h = householdRepo.findById(id).orElseThrow();
        applyDto(h, dto);
        householdRepo.save(h);

        DimHousehold prev = dimRepo.findByHouseholdIdAndValidToIsNull(id).orElseThrow();
        prev.setValidTo(LocalDateTime.now());
        dimRepo.save(prev);

        createNewHistoryRecord(h);
    }

    /**
     * Deletes a household record with the given ID and invalidates the corresponding historical record.
     * @param id the ID of the household record to delete
     */
    public void delete(Integer id) {
        householdRepo.deleteById(id);

        DimHousehold last = dimRepo.findByHouseholdIdAndValidToIsNull(id).orElseThrow();
        last.setValidTo(LocalDateTime.now());
        dimRepo.save(last);
    }

    /**
     * Retrieves the current data for a household with the given ID.
     * @param id the ID of the household to retrieve data for
     * @return the current data for the household
     * @throws IllegalArgumentException if the household with the given ID doesn't exist
     */
    public HouseholdDto getCurrent(Integer id) throws IllegalArgumentException {
        if(!householdRepo.existsById(id))
            throw new IllegalArgumentException("Household with ID " + id + " doesn't exist");

        Household h = householdRepo.findById(id).orElseThrow();
        return HouseholdDto.fromEntity(h);
    }

    /**
     * Retrieves historical records for a household with the given ID filtered by time interval.
     * @param id the ID of the household to retrieve historical records for
     * @param fromDate optional start date for filtering
     * @param toDate optional end date for filtering
     * @return a filtered list of historical records for the household
     * @throws IllegalArgumentException if the household with the given ID doesn't exist
     */
    public List<HouseholdHistoryDto> getHistoryByDateRange(Integer id, LocalDateTime fromDate, LocalDateTime toDate) throws IllegalArgumentException {
        if (!householdRepo.existsById(id))
            throw new IllegalArgumentException("Household with ID " + id + " does not exist.");

        List<DimHousehold> results;

        if (fromDate != null && toDate != null) {
            // Search by date range
            results = dimRepo.findByHouseholdIdAndDateRange(id, fromDate, toDate);
        } else if (fromDate != null) {
            // Search from a specific date onwards
            results = dimRepo.findByHouseholdIdOrderByValidFromDesc(id).stream()
                    .filter(h -> !h.getValidFrom().isBefore(fromDate))
                    .toList();
        } else if (toDate != null) {
            // Search up to a specific date
            results = dimRepo.findByHouseholdIdOrderByValidFromDesc(id).stream()
                    .filter(h -> h.getValidFrom().isBefore(toDate.plusSeconds(1)))
                    .toList();
        } else {
            // No filters - return all
            results = dimRepo.findByHouseholdIdOrderByValidFromDesc(id);
        }

        return results.stream()
                .map(HouseholdHistoryDto::fromEntity)
                .sorted((h1, h2) -> h2.validFrom.compareTo(h1.validFrom))
                .toList();
    }

    /**
     * Retrieves all household records from the database.
     * @return a list of all household records
     */
    public List<HouseholdDto> getAll(){
        return householdRepo.findAll().stream().map(HouseholdDto::fromEntity).toList();
    }

    /**
     * Retrieves the number of members in a household with the given ID.
     * @param householdId the ID of the household to retrieve the member count for
     * @return the number of members in the household
     * @throws IllegalArgumentException if the household with the given ID doesn't exist
     */
    public long getMemberCount(Integer householdId) throws IllegalArgumentException{
        if (!householdRepo.existsById(householdId))
            throw new IllegalArgumentException("Household with ID " + householdId + " doesn't exist");

        return personRepo.countByHouseholdHouseholdId(householdId);
    }

    /**
     * Retrieves all members of a household with the given ID.
     * @param householdId the ID of the household to retrieve members for
     * @return a list of all members in the household
     * @throws IllegalArgumentException if the household with the given ID doesn't exist
     */
    public List<PersonDto> getMembers(Integer householdId) throws IllegalArgumentException {
        if (!householdRepo.existsById(householdId))
            throw new IllegalArgumentException("Household with ID " + householdId + " doesn't exist");

        return personRepo.findByHouseholdHouseholdId(householdId).stream()
                .map(PersonDto::fromEntity)
                .toList();
    }
}
