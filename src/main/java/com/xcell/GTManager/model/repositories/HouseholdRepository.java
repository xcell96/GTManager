package com.xcell.GTManager.model.repositories;

import com.xcell.GTManager.model.tables.Household;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * JpaRepository for the {@link Household} entity.
 */
public interface HouseholdRepository extends JpaRepository<Household, Integer> {

}
