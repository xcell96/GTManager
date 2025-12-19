package com.xcell.GTManager.model.repositories;

import com.xcell.GTManager.model.tables.Degree;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * JpaRepository for the {@link Degree} entity.
 */
public interface DegreeRepository extends JpaRepository<Degree, Integer> {
    /**
     * Retrieves all the degrees for a specific person ordered by awarded date.
     * @param personId the person's ID
     * @return the list of degrees
     */
    List<Degree> findByPersonPersonIdOrderByAwardedAtDesc(Integer personId);
}
