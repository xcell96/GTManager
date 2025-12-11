package com.xcell.GTManager.model.repositories;

import com.xcell.GTManager.model.tables.DimHousehold;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface DimHouseholdRepository extends JpaRepository<DimHousehold, Integer> {
    Optional<DimHousehold> findByHouseholdIdAndValidToIsNull(Integer id);
}
