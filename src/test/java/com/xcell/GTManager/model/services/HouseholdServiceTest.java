package com.xcell.GTManager.model.services;

import com.xcell.GTManager.dto.HouseholdDto;
import com.xcell.GTManager.model.tables.Household;
import jakarta.transaction.Transactional;
import jakarta.validation.ConstraintViolationException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
@ActiveProfiles("test")
class HouseholdServiceTest {

    @Autowired
    private HouseholdService householdService;

    // TODO: also confirm the creation of historical records
    @Test
    void testCreateCheckID() {
        Household h = new Household();
        h.setAddress("123 Farm Lane");
        h.setSurface(BigDecimal.valueOf(100.0));
        h.setCattle(5);

        householdService.create(HouseholdDto.fromEntity(h));
        assertNotNull(h.getHouseholdId());
    }

    @Test
    void testCreateWithNonPositiveSurface() {
        Household h = new Household();
        h.setAddress("123 Farm Lane");
        h.setSurface(BigDecimal.valueOf(-100.0));
        h.setCattle(5);

        assertThrows(ConstraintViolationException.class, () -> householdService.create(HouseholdDto.fromEntity(h)));
    }

    @Test
    void testCreateWithNegativeCattle() {
        Household h = new Household();
        h.setAddress("123 Farm Lane");
        h.setSurface(BigDecimal.valueOf(100.0));
        h.setCattle(-5);

        assertThrows(ConstraintViolationException.class, () -> householdService.create(HouseholdDto.fromEntity(h)));
    }

    @Test
    void update() {
    }

    @Test
    void delete() {
    }
}