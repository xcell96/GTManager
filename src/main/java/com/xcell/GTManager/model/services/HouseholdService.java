package com.xcell.GTManager.model.services;

import com.xcell.GTManager.model.tables.Household;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class HouseholdService {

    @PersistenceContext
    private EntityManager em;

    @Transactional
    public void addHousehold(Household h) {
        em.persist(h);
    }

    @Transactional
    public void updateHousehold(Integer id, Household newData){
        Household h = em.find(Household.class, id);
        if(!Objects.equals(h.getHouseholdId(), newData.getHouseholdId())) return;
        h.copyFrom(newData);
        em.persist(h);
    }

    @Transactional
    public void deleteHousehold(Integer id) {
        Household h = em.find(Household.class, id);
        em.remove(h);
    }
}
