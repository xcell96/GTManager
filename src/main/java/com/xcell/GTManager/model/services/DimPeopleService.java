package com.xcell.GTManager.model.services;

import com.xcell.GTManager.dto.PersonHistoryDto;
import com.xcell.GTManager.model.repositories.DimPersonRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class DimPeopleService {

    private final DimPersonRepository dimRepo;

    public DimPeopleService(DimPersonRepository dimRepo) {
        this.dimRepo = dimRepo;
    }

    public List<PersonHistoryDto> getAll() {
        return dimRepo.findAll().stream().map(PersonHistoryDto::fromEntity).toList();
    }

}
