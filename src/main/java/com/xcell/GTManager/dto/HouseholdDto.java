package com.xcell.GTManager.dto;

import com.xcell.GTManager.model.tables.Household;

import java.math.BigDecimal;

public class HouseholdDto {
    public Integer householdId;
    public String address;
    public BigDecimal surface;

    public Integer cattle;
    public Integer swine;
    public Integer sheep;
    public Integer goats;
    public Integer equines;
    public Integer poultry;
    public Integer rabbits;
    public Integer donkeys;
    public Integer beeFamilies;
    public Integer otherAnimals;

    public Household toEntity() {
        Household h = new Household();
        h.setHouseholdId(householdId);
        h.setAddress(address);
        h.setSurface(surface);

        h.setCattle(cattle);
        h.setSwine(swine);
        h.setSheep(sheep);
        h.setGoats(goats);
        h.setEquines(equines);
        h.setPoultry(poultry);
        h.setRabbits(rabbits);
        h.setDonkeys(donkeys);
        h.setBeeFamilies(beeFamilies);
        h.setOtherAnimals(otherAnimals);

        return h;
    }

    public static HouseholdDto fromEntity(Household h) {
        HouseholdDto dto = new HouseholdDto();
        dto.householdId = h.getHouseholdId();
        dto.address = h.getAddress();
        dto.surface = h.getSurface();

        dto.cattle = h.getCattle();
        dto.swine = h.getSwine();
        dto.sheep = h.getSheep();
        dto.goats = h.getGoats();
        dto.equines = h.getEquines();
        dto.poultry = h.getPoultry();
        dto.rabbits = h.getRabbits();
        dto.donkeys = h.getDonkeys();
        dto.beeFamilies = h.getBeeFamilies();
        dto.otherAnimals = h.getOtherAnimals();

        return dto;
    }
}
