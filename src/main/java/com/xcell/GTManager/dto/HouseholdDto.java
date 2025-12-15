package com.xcell.GTManager.dto;

import com.xcell.GTManager.model.tables.Household;

import java.math.BigDecimal;

public class HouseholdDto {
    private Integer householdId;
    private String address;
    private BigDecimal surface;

    private Integer cattle;
    private Integer swine;
    private Integer sheep;
    private Integer goats;
    private Integer equines;
    private Integer poultry;
    private Integer rabbits;
    private Integer donkeys;
    private Integer beeFamilies;
    private Integer otherAnimals;

    public Integer getHouseholdId() { return householdId; }

    public String getAddress() { return address; }

    public BigDecimal getSurface() { return surface; }

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
