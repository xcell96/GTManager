package com.xcell.GTManager.model.services;

import com.xcell.GTManager.model.tables.Degree;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class DegreeService {

    @PersistenceContext
    private EntityManager em;

    @Transactional
    public void addDegree(Degree d) {
        em.persist(d);
    }

    @Transactional
    public void updateDegree(Integer id, Degree newData){
        Degree d = em.find(Degree.class, id);
        if(!Objects.equals(d.getDegreeId(), newData.getDegreeId())) return;
        d.copyFrom(newData);
        em.persist(d);
    }

    @Transactional
    public void deleteDegree(Integer id) {
        Degree d = em.find(Degree.class, id);
        em.remove(d);
    }
}
