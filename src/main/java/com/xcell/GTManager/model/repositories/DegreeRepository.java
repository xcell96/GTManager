package com.xcell.GTManager.model.repositories;

import com.xcell.GTManager.model.tables.Degree;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface DegreeRepository extends JpaRepository<Degree, Integer> {
    List<Degree> findByPersonPersonIdAndAwardedAtLessThanEqual(Integer personId, LocalDateTime asOf);
}
