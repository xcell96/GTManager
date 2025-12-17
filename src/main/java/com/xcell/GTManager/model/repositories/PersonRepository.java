package com.xcell.GTManager.model.repositories;

import com.xcell.GTManager.model.tables.Person;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PersonRepository extends JpaRepository <Person, Integer> {
    long countByHouseholdHouseholdId(Integer householdId);
    List<Person> findByHouseholdHouseholdId(Integer householdId);
}
