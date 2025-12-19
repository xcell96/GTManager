package com.xcell.GTManager.model.repositories;

import com.xcell.GTManager.enums.EEducationLevel;
import com.xcell.GTManager.enums.EKinship;
import com.xcell.GTManager.enums.ESex;
import com.xcell.GTManager.model.tables.*;
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
class DegreeRepositoryTest {

    @Autowired
    private DegreeRepository degreeRepo;

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

        DimPerson dp = new DimPerson();
        dp.copyFrom(p);
        dp.setHousehold(dh);
        dp.setValidFrom(LocalDateTime.now());
        dp.setValidTo(null);
        dimRepo.save(dp);

        Degree d = new Degree();
//        d.setPerson(dp);
        d.setTitle("Master - Mechanical Engineering");
        d.setInstitution("UP Timisoara");
        d.setGraduationYear(2020);
        degreeRepo.save(d);

        Optional<Degree> retrieved = degreeRepo.findById(d.getDegreeId());
        assertTrue(retrieved.isPresent());
        assertEquals(d.getDegreeId(), retrieved.get().getDegreeId());
        assertEquals(dp, retrieved.get().getPerson());
        assertEquals("Master - Mechanical Engineering", retrieved.get().getTitle());
        assertEquals("UP Timisoara", retrieved.get().getInstitution());
        assertEquals(2020, retrieved.get().getGraduationYear());
    }
}