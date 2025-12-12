package com.xcell.GTManager.model.repositories;

import com.xcell.GTManager.model.tables.Degree;
import com.xcell.GTManager.model.tables.DimHousehold;
import com.xcell.GTManager.model.tables.DimPerson;
import com.xcell.GTManager.model.tables.Household;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.time.LocalDateTime;

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
    private DimHouseholdRepository dimHouseholdRepository;

    @Autowired
    private HouseholdRepository householdRepo;

    @Test
    void testSaveAndFind(){
        Household h = new Household();
        h.setAddress("123 Farm Lane");
        h.setSurface(BigDecimal.valueOf(100.0));
        h.setCattle(5);
        householdRepo.save(h);

        DimHousehold d = new DimHousehold();
        d.copyFrom(h);
        d.setHouseholdId(h.getHouseholdId());
        d.setValidFrom(LocalDateTime.now());
        d.setValidTo(null);
        dimHouseholdRepository.save(d);

//        DimPerson p = new DimPerson();
//        p.

//        Degree d = new Degree();
//        d.setPerson();
    }
}