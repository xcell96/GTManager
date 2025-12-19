package com.xcell.GTManager.model.services;

import com.xcell.GTManager.dto.PersonHistoryDto;
import com.xcell.GTManager.model.repositories.DimPersonRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

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

}
