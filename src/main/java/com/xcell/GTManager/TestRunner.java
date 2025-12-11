package com.xcell.GTManager;

import com.xcell.GTManager.model.services.HouseholdService;
import com.xcell.GTManager.model.tables.Household;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class TestRunner implements CommandLineRunner {

    private final HouseholdService hs;

    public TestRunner(HouseholdService hs) {
        this.hs = hs;
    }

    @Override
    public void run(String... args) {
        Household h = new Household();

        h.setHouseholdId(1);
        h.setAddress("123 Main St");
        h.setSurface(BigDecimal.valueOf(100));
        h.setCattle(10);
        h.setSwine(20);

//        hs.addHousehold(h);
//        System.out.println("Added household with id: " + h.getHouseholdId());

//        hs.updateHousehold(1, h);
//        System.out.println("Updated household with id: " + h.getHouseholdId());

        hs.deleteHousehold(1);
        System.out.println("Deleted household with id: " + 1);
    }
}
