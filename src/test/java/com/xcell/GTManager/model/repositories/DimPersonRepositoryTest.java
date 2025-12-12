package com.xcell.GTManager.model.repositories;

import com.xcell.GTManager.enums.EEducationLevel;
import com.xcell.GTManager.enums.EKinship;
import com.xcell.GTManager.enums.ESex;
import com.xcell.GTManager.model.tables.DimHousehold;
import com.xcell.GTManager.model.tables.DimPerson;
import com.xcell.GTManager.model.tables.Household;
import com.xcell.GTManager.model.tables.Person;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.ANY)
@Transactional
class DimPersonRepositoryTest {

    @Autowired
    private DimPersonRepository dimRepo;

    @Autowired
    private PersonRepository personRepo;

    @Autowired
    private HouseholdRepository householdRepo;

    @Autowired
    private DimHouseholdRepository dimHouseholdRepo;

    @Test
    void testSaveAndFind(){
        Household h = new Household();
        h.setAddress("123 Farm Lane");
        h.setSurface(BigDecimal.valueOf(100.0));
        h.setCattle(5);
        householdRepo.save(h);

        DimHousehold dh = new DimHousehold();
        dh.copyFrom(h);
        dh.setHouseholdId(h.getHouseholdId());
        dh.setValidFrom(LocalDateTime.now());
        dh.setValidTo(null);
        dimHouseholdRepo.save(dh);

        Person p = new Person();
        p.setFirstName("John");
        p.setLastName("Doe");
        p.setSex(ESex.MALE);
        p.setDateOfBirth(LocalDate.now());
        p.setCNP("5050609803922");
        p.setCitizenship("Romania");
        p.setHousehold(h);
        p.setKinship(EKinship.HEAD);
        p.setEducationLevel(EEducationLevel.SUPERIOR);
        p.setJob("Engineer");
        p.setPlaceOfWork("My Company");
        personRepo.save(p);

        DimPerson d = new DimPerson();
        d.copyFrom(p);
        d.setHousehold(dh);
        d.setValidFrom(LocalDateTime.now());
        d.setValidTo(null);
        dimRepo.save(d);

        Optional<DimPerson> retrieved = dimRepo.findByPersonIdAndValidToIsNull(p.getPersonId());
        assertTrue(retrieved.isPresent());
        assertEquals(p.getPersonId(), retrieved.get().getPersonId());
        assertEquals(ESex.MALE, retrieved.get().getSex());
        assertEquals(EKinship.HEAD, retrieved.get().getKinship());
        assertEquals(EEducationLevel.SUPERIOR, retrieved.get().getEducationLevel());
        assertEquals(h.getHouseholdId(), retrieved.get().getHousehold().getHouseholdId());
    }

}