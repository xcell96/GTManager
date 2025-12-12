package com.xcell.GTManager.model.repositories;

import com.xcell.GTManager.model.tables.DimHousehold;
import com.xcell.GTManager.model.tables.Household;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.ANY)
@Transactional
class DimHouseholdRepositoryTest {

    @Autowired
    private DimHouseholdRepository dimRepo;

    @Autowired
    private HouseholdRepository householdRepo;

    @Test
    void testSaveAndFind() {
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
        dimRepo.save(d);

        Optional<DimHousehold> retrieved = dimRepo.findByHouseholdIdAndValidToIsNull(h.getHouseholdId());
        assertTrue(retrieved.isPresent());
        assertEquals(h.getHouseholdId(), retrieved.get().getHouseholdId());
        assertEquals("123 Farm Lane", retrieved.get().getAddress());
    }

}