package com.xcell.GTManager.model.repositories;

import com.xcell.GTManager.model.tables.Person;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonRepository extends JpaRepository <Person, Integer> {
}
