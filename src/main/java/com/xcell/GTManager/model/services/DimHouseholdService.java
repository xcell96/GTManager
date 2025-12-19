package com.xcell.GTManager.model.services;

import com.xcell.GTManager.dto.HouseholdHistoryDto;
import com.xcell.GTManager.dto.PersonHistoryDto;
import com.xcell.GTManager.model.repositories.DimHouseholdRepository;
import com.xcell.GTManager.model.repositories.DimPersonRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

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
