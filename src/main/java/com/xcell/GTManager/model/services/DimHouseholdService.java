package com.xcell.GTManager.model.services;

import com.xcell.GTManager.dto.HouseholdHistoryDto;
import com.xcell.GTManager.dto.PersonHistoryDto;
import com.xcell.GTManager.model.repositories.DimHouseholdRepository;
import com.xcell.GTManager.model.repositories.DimPersonRepository;
import com.xcell.GTManager.model.tables.DimHousehold;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

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

    public List<HouseholdHistoryDto> getAll() {
        return dimHouseholdRepo.findAll().stream().map(HouseholdHistoryDto::fromEntity).toList();
    }

    public HouseholdHistoryDto getBySk(Integer sk){
        DimHousehold d = dimHouseholdRepo.findById(sk).orElseThrow();
        return HouseholdHistoryDto.fromEntity(d);
    }

    public long getMemberCount(Integer householdSk) {
        return dimPersonRepo.countByHouseholdHouseholdSk(householdSk);
    }

    public List<PersonHistoryDto> getMembers(Integer householdSk) {
        return dimPersonRepo.findByHouseholdHouseholdSk(householdSk).stream()
                .map(PersonHistoryDto::fromEntity)
                .toList();
    }

}
