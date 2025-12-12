package com.xcell.GTManager.model.repositories;

import com.xcell.GTManager.model.tables.Household;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.ANY)
@Transactional
class HouseholdRepositoryTest {

    @Autowired
    private HouseholdRepository repo;

    @Test
    void testSaveAndFind(){
        Household h = new Household();
        h.setAddress("123 Farm Lane");
        h.setSurface(BigDecimal.valueOf(100.0));
        h.setCattle(5);
        repo.save(h);

        Optional<Household> retrieved = repo.findById(h.getHouseholdId());
        assertTrue(retrieved.isPresent());
        assertEquals(h.getHouseholdId(), retrieved.get().getHouseholdId());
        assertEquals("123 Farm Lane", retrieved.get().getAddress());
        assertEquals(BigDecimal.valueOf(100.0), retrieved.get().getSurface());
        assertEquals(5, retrieved.get().getCattle());
    }
}