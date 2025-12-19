package com.xcell.GTManager.model.repositories;

import com.xcell.GTManager.model.tables.DimHousehold;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * JpaRepository for the {@link DimHousehold} entity.
 */
public interface DimHouseholdRepository extends JpaRepository<DimHousehold, Integer> {
    /**
     * Finds the latest historical record for a specific household.
     * @param id the household's ID
     * @return the latest historical record for the household
     */
    Optional<DimHousehold> findByHouseholdIdAndValidToIsNull(Integer id);
}
