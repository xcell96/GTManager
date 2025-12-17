package com.xcell.GTManager.model.services;

import com.xcell.GTManager.model.repositories.DegreeRepository;
import com.xcell.GTManager.model.repositories.PersonRepository;
import com.xcell.GTManager.model.tables.Degree;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Component;


@Component
@Transactional
public class DegreeService {

    private final DegreeRepository degreeRepo;
    private final PersonRepository personRepo;

    public DegreeService(DegreeRepository degreeRepo, PersonRepository personRepo) {
        this.degreeRepo = degreeRepo;
        this.personRepo = personRepo;
    }

    public void create(Degree d) {
        if(degreeRepo.existsById(d.getDegreeId()))
            throw new IllegalArgumentException("Degree with ID " + d.getDegreeId() + " already exists");

        if(!personRepo.existsById(d.getPerson().getPersonId()))
            throw new IllegalArgumentException("Person with ID " + d.getPerson().getPersonId() + " doesn't exist");

        degreeRepo.saveAndFlush(d);
    }

    public void update(Integer id, Degree newData){
        if(!degreeRepo.existsById(id))
            throw new IllegalArgumentException("Degree with ID " + id + " doesn't exist");

        if(!personRepo.existsById(newData.getPerson().getPersonId()))
            throw new IllegalArgumentException("Person with ID " + newData.getPerson().getPersonId() + " doesn't exist");

        Degree d = degreeRepo.findById(id).orElseThrow();
        d.copyFrom(newData);
        degreeRepo.save(d);
    }

    public void delete(Integer id) {
        degreeRepo.deleteById(id);
    }
}
