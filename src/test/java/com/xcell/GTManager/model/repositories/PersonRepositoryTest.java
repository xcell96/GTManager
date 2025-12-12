package com.xcell.GTManager.model.repositories;

import com.xcell.GTManager.enums.EEducationLevel;
import com.xcell.GTManager.enums.EKinship;
import com.xcell.GTManager.enums.ESex;
import com.xcell.GTManager.model.tables.Household;
import com.xcell.GTManager.model.tables.Person;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.ANY)
@Transactional
class PersonRepositoryTest {

    @Autowired
    private HouseholdRepository householdRepo;

    @Autowired
    private PersonRepository personRepo;

    @Test
    void testSaveAndFind(){
        Household h = new Household();
        h.setAddress("123 Farm Lane");
        h.setSurface(BigDecimal.valueOf(100.0));
        h.setCattle(5);
        householdRepo.save(h);

        LocalDate dob = LocalDate.now();

        Person p = new Person();
        p.setFirstName("John");
        p.setLastName("Doe");
        p.setSex(ESex.MALE);
        p.setDateOfBirth(dob);
        p.setCNP("5050609803922");
        p.setCitizenship("Romania");
        p.setHousehold(h);
        p.setKinship(EKinship.HEAD);
        p.setEducationLevel(EEducationLevel.SUPERIOR);
        p.setJob("Engineer");
        p.setPlaceOfWork("My Company");
        personRepo.save(p);

        Optional<Person> retrieved = personRepo.findById(p.getPersonId());
        assertTrue(retrieved.isPresent());
        assertEquals(p.getPersonId(), retrieved.get().getPersonId());
        assertEquals("John", retrieved.get().getFirstName());
        assertEquals("Doe", retrieved.get().getLastName());
        assertEquals(ESex.MALE, retrieved.get().getSex());
        assertEquals(dob, retrieved.get().getDateOfBirth());
        assertEquals("5050609803922", retrieved.get().getCNP());
        assertEquals("Romania", retrieved.get().getCitizenship());
        assertEquals(h.getHouseholdId(), retrieved.get().getHousehold().getHouseholdId());
        assertEquals(EKinship.HEAD, retrieved.get().getKinship());
        assertEquals(EEducationLevel.SUPERIOR, retrieved.get().getEducationLevel());
        assertEquals("Engineer", retrieved.get().getJob());
        assertEquals("My Company", retrieved.get().getPlaceOfWork());
    }
}