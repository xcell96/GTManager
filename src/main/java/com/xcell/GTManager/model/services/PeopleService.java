package com.xcell.GTManager.model.services;

import com.xcell.GTManager.model.tables.Person;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class PeopleService {

    @PersistenceContext
    private EntityManager em;

    @Transactional
    public void addPerson(Person p) {
        em.persist(p);
    }

    @Transactional
    public void updatePerson(Integer id, Person newData){
        Person p = em.find(Person.class, id);
        if(!Objects.equals(p.getPersonId(), newData.getPersonId())) return;
        p.copyFrom(newData);
        em.persist(p);
    }

    @Transactional
    public void deletePerson(Integer id) {
        Person p = em.find(Person.class, id);
        em.remove(p);
    }
}
