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

    public Integer getHouseholdId() {
        return householdId;
    }
    public void setHouseholdId(Integer householdId) {
        this.householdId = householdId;
    }

    public String getAddress() {
        return address;
    }
    public void setAddress(String address) {
        this.address = address;
    }

    public BigDecimal getSurface() {
        return surface;
    }
    public void setSurface(BigDecimal surface) {
        this.surface = surface;
    }

    public Integer getCattle() {
        return cattle;
    }
    public void setCattle(Integer cattle) {
        this.cattle = cattle;
    }

    public Integer getSwine() {
        return swine;
    }
    public void setSwine(Integer swine) {
        this.swine = swine;
    }

    public Integer getSheep() {
        return sheep;
    }
    public void setSheep(Integer sheep) {
        this.sheep = sheep;
    }

    public Integer getGoats() {
        return goats;
    }
    public void setGoats(Integer goats) {
        this.goats = goats;
    }

    public Integer getEquines() {
        return equines;
    }
    public void setEquines(Integer equines) {
        this.equines = equines;
    }

    public Integer getPoultry() {
        return poultry;
    }
    public void setPoultry(Integer poultry) {
        this.poultry = poultry;
    }

    public Integer getRabbits() {
        return rabbits;
    }
    public void setRabbits(Integer rabbits) {
        this.rabbits = rabbits;
    }

    public Integer getDonkeys() {
        return donkeys;
    }
    public void setDonkeys(Integer donkeys) {
        this.donkeys = donkeys;
    }

    public Integer getBeeFamilies() {
        return beeFamilies;
    }
    public void setBeeFamilies(Integer beeFamilies) {
        this.beeFamilies = beeFamilies;
    }

    public Integer getOtherAnimals() {
        return otherAnimals;
    }
    public void setOtherAnimals(Integer otherAnimals) {
        this.otherAnimals = otherAnimals;
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
