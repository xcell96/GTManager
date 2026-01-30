package com.xcell.GTManager.model.services;

import com.xcell.GTManager.dto.PersonHistoryDto;
import com.xcell.GTManager.model.repositories.DimPersonRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Provides business logic for managing {@link com.xcell.GTManager.model.tables.DimPerson} entities.
 * <p>
 * This service handles only read operations.
 * It uses the {@link DimPersonRepository} to retrieve all historical records of people.
 * All data retrieved is packed and sent as a {@link PersonHistoryDto} object.
 */
@Service
@Transactional
public class DimPeopleService {

    private final DimPersonRepository dimRepo;

    public DimPeopleService(DimPersonRepository dimRepo) {
        this.dimRepo = dimRepo;
    }

    /**
     * Retrieves all historical records of people.
     * @return a list of all historical records of people
     */
    public List<PersonHistoryDto> getAll() {
        return dimRepo.findAll().stream().map(PersonHistoryDto::fromEntity).toList();
    }

    /**
     * Retrieves people based on search criteria including time intervals.
     * @param fromDate optional start date for filtering
     * @param toDate optional end date for filtering
     * @param personId optional person ID for filtering
     * @param householdId optional household ID for filtering
     * @return filtered list of person history records
     */
    public List<PersonHistoryDto> search(LocalDateTime fromDate, LocalDateTime toDate, Integer personId, Integer householdId) {
        List<com.xcell.GTManager.model.tables.DimPerson> results;
        
        if (personId != null && fromDate != null && toDate != null) {
            // Search by person ID and date range
            results = dimRepo.findByPersonIdAndDateRange(personId, fromDate, toDate);
        } else if (householdId != null && fromDate != null && toDate != null) {
            // Search by household ID and date range
            results = dimRepo.findByHouseholdIdAndDateRange(householdId, fromDate, toDate);
        } else if (fromDate != null && toDate != null) {
            // Search by date range only
            results = dimRepo.findByDateRange(fromDate, toDate);
        } else if (fromDate != null) {
            // Search from a specific date onwards
            results = dimRepo.findByPointInTime(fromDate);
        } else if (personId != null) {
            // Search by person ID only
            results = dimRepo.findByPersonIdOrderByValidFromDesc(personId);
        } else if (householdId != null) {
            // Search by household ID only
            results = dimRepo.findAll().stream()
                    .filter(p -> p.getHousehold().getHouseholdId().equals(householdId))
                    .toList();
        } else {
            // No filters - return all
            results = dimRepo.findAll();
        }
        
        return results.stream()
                .map(PersonHistoryDto::fromEntity)
                .toList();
    }

}
