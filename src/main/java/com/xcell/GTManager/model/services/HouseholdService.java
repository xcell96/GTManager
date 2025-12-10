package com.xcell.GTManager.model.services;

import com.xcell.GTManager.model.tables.Household;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Service;

@Service
public class HouseholdService {

    @PersistenceContext
    private EntityManager em;

    @Transactional
    public void addHousehold(Household h) {
        em.persist(h);
    }
}
