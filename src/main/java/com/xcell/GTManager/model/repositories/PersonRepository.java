package com.xcell.GTManager.model.repositories;

import com.xcell.GTManager.model.tables.Person;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * JpaRepository for the {@link Person} entity.
 */
public interface PersonRepository extends JpaRepository <Person, Integer> {
    /**
     * Retrieves the number of people that are currently in a specific household.
     * @param householdId the ID of the household
     * @return the number of people in the household
     */
    long countByHouseholdHouseholdId(Integer householdId);

    /**
     * Retrieves a list of people that are currently in a specific household.
     * @param householdId the ID of the household
     * @return a list of people in the household
     */
    List<Person> findByHouseholdHouseholdId(Integer householdId);
}
