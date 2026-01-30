package com.xcell.GTManager.model.services;

import com.xcell.GTManager.dto.HouseholdHistoryDto;
import com.xcell.GTManager.dto.PersonHistoryDto;
import com.xcell.GTManager.model.repositories.DimHouseholdRepository;
import com.xcell.GTManager.model.repositories.DimPersonRepository;
import com.xcell.GTManager.model.tables.DimHousehold;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Provides business logic for managing {@link com.xcell.GTManager.model.tables.DimHousehold} entities.
 * <p>
 * This service handles only read operations.
 * This service uses the {@link DimHouseholdRepository} to retrieve households and the {@link DimPersonRepository} to retrieve household members.
 * All data retrieved is packed and sent as a {@link HouseholdHistoryDto} object.
 */
@Service
@Transactional
public class DimHouseholdService {

    private final DimHouseholdRepository dimHouseholdRepo;
    private final DimPersonRepository dimPersonRepo;

    public DimHouseholdService(
            DimHouseholdRepository dimHouseholdRepo,
            DimPersonRepository dimPersonRepo
    ) {
        this.dimHouseholdRepo = dimHouseholdRepo;
        this.dimPersonRepo = dimPersonRepo;
    }

    /**
     * Retrieves all historical records of all households.
     * @return a list of all historical records of all households
     */
    public List<HouseholdHistoryDto> getAll() {
        return dimHouseholdRepo.findAll().stream().map(HouseholdHistoryDto::fromEntity).toList();
    }

    /**
     * Retrieves households based on search criteria including time intervals.
     * @param fromDate optional start date for filtering
     * @param toDate optional end date for filtering
     * @param householdId optional household ID for filtering
     * @return filtered list of household history records
     */
    public List<HouseholdHistoryDto> search(LocalDateTime fromDate, LocalDateTime toDate, Integer householdId) {
        List<DimHousehold> results;

        if (householdId != null && fromDate != null && toDate != null) {
            // Search by household ID and date range
            results = dimHouseholdRepo.findByHouseholdIdAndDateRange(householdId, fromDate, toDate);
        } else if (fromDate != null && toDate != null) {
            // Search by date range only
            results = dimHouseholdRepo.findByDateRange(fromDate, toDate);
        } else if (fromDate != null) {
            // Search from a specific date onwards
            results = dimHouseholdRepo.findByPointInTime(fromDate);
        } else if (householdId != null) {
            // Search by household ID only
            results = dimHouseholdRepo.findAll().stream()
                    .filter(h -> h.getHouseholdId().equals(householdId))
                    .toList();
        } else {
            // No filters - return all
            results = dimHouseholdRepo.findAll();
        }

        return results.stream()
                .map(HouseholdHistoryDto::fromEntity)
                .toList();
    }

    /**
     * Retrieves households that were active at a specific point in time.
     * @param pointInTime the specific datetime
     * @return list of households active at the specified time
     */
    public List<HouseholdHistoryDto> getByPointInTime(LocalDateTime pointInTime) {
        return dimHouseholdRepo.findByPointInTime(pointInTime).stream()
                .map(HouseholdHistoryDto::fromEntity)
                .toList();
    }

    /**
     * Retrieves a specific historical record by its surrogate key.
     * @param sk the surrogate key of the historical record to retrieve
     * @return the historical record
     */
    public HouseholdHistoryDto getBySk(Integer sk){
        return HouseholdHistoryDto.fromEntity(dimHouseholdRepo.findById(sk).orElseThrow());
    }

    /**
     * Retrieves the number of members of a specific household at the time of the record.
     * @param householdSk the surrogate key of the historical record of the household
     * @return the number of members in the household at the time of the record
     */
    public long getMemberCount(Integer householdSk) {
        return dimPersonRepo.countByHouseholdHouseholdSk(householdSk);
    }

    /**
     * Retrieves all members of a specific household at the time of the record.
     * @param householdSk the surrogate key of the historical record of the household
     * @return a list of all members in the household at the time of the record
     */
    public List<PersonHistoryDto> getMembers(Integer householdSk) {
        return dimPersonRepo.findByHouseholdHouseholdSk(householdSk).stream()
                .map(PersonHistoryDto::fromEntity)
                .toList();
    }

}
