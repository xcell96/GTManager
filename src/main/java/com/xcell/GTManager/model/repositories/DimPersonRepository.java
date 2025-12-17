package com.xcell.GTManager.model.repositories;

import com.xcell.GTManager.model.tables.DimPerson;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface DimPersonRepository extends JpaRepository<DimPerson, Integer> {
    Optional<DimPerson> findByPersonIdAndValidToIsNull(Integer id);
    List<DimPerson> findByPersonIdOrderByValidFromDesc(Integer id);

    long countByHouseholdHouseholdSk(Integer householdSk);
    List<DimPerson> findByHouseholdHouseholdSk(Integer householdSk);
}
