package com.xcell.GTManager.model.services;

import com.xcell.GTManager.model.repositories.DegreeRepository;
import com.xcell.GTManager.model.repositories.PersonRepository;
import com.xcell.GTManager.model.tables.Degree;
import com.xcell.GTManager.model.tables.Person;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Provides business logic for managing {@link Degree} entities.
 * <p>
 * This service ensures that a degree is always associated with one and only one person.
 * This service uses the {@link DegreeRepository} to create, delete and retrieve degrees for a specific person
 * and the {@link PersonRepository} to retrieve the person associated with a specific degree.
 */
@Component
@Transactional
public class DegreeService {

    private final DegreeRepository degreeRepo;
    private final PersonRepository personRepo;

    public DegreeService(DegreeRepository degreeRepo, PersonRepository personRepo) {
        this.degreeRepo = degreeRepo;
        this.personRepo = personRepo;
    }

    /**
     * Creates a new degree for a specific person.
     * <p>
     * The degree must not already have an ID, as IDs are generated automatically.
     * The degree is associated with the person identified by {@code personId}.
     *
     * @param personId the ID of the person to associate the degree with
     * @param d the degree to create
     * @throws IllegalArgumentException if the degree already has an ID
     */
    public void createForPerson(Integer personId, Degree d) throws IllegalArgumentException{
        if (d.getDegreeId() != null)
            throw new IllegalArgumentException("Degree IDs are automatically generated.");

        Person p = personRepo.findById(personId).orElseThrow();
        d.setPerson(p);

        degreeRepo.saveAndFlush(d);
    }

    /**
     * Deletes a degree with the given ID.
     * @param id the ID of the degree to delete
     */
    public void delete(Integer id) {
        degreeRepo.deleteById(id);
    }

    /**
     * Retrieves all degrees for a specific person ordered by award date.
     * @param personId the ID of the person to retrieve degrees for
     * @return a list of degrees for the person
     */
    public List<Degree> getForPerson(Integer personId) {
        return degreeRepo.findByPersonPersonIdOrderByAwardedAtDesc(personId);
    }
}
