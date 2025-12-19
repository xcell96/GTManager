package com.xcell.GTManager.model.repositories;

import com.xcell.GTManager.model.tables.DimPerson;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

/**
 * JpaRepository for the {@link DimPerson} entity.
 */
public interface DimPersonRepository extends JpaRepository<DimPerson, Integer> {
    /**
     * Finds the latest historical record for a specific person.
     * @param id the person's ID
     * @return the latest historical record for the person
     */
    Optional<DimPerson> findByPersonIdAndValidToIsNull(Integer id);

    /**
     * Finds all historical records for a specific person ordered by valid from date.
     * @param id the person's ID
     * @return a list of historical records for the person
     */
    List<DimPerson> findByPersonIdOrderByValidFromDesc(Integer id);

    /**
     * Retrieves the number of people that belonged to a specific household at a specific point in time.
     * The point in time is determined by the surrogate key of the historical record of the household.
     * @param householdSk the surrogate key of the historical record of the household
     * @return the number of people that belonged to the household
     */
    long countByHouseholdHouseholdSk(Integer householdSk);

    /**
     * Retrieves all people that belonged to a specific household at a specific point in time.
     * The point in time is determined by the surrogate key of the historical record of the household.
     * @param householdSk the surrogate key of the historical record of the household
     * @return the list of people that belonged to the household
     */
    List<DimPerson> findByHouseholdHouseholdSk(Integer householdSk);
}
