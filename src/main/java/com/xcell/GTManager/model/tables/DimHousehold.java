package com.xcell.GTManager.model.tables;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "DimHouseholds")
public class DimHousehold {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "household_sk", unique = true)
    private Integer householdSk;

    @Column(name = "household_id", nullable = false)
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
    @Column(name = "bee_families")
    private Integer beeFamilies;
    @Column(name = "other_animals")
    private Integer otherAnimals;

    @Column(name = "valid_from", nullable = false)
    private LocalDateTime validFrom;
    @Column(name = "valid_to")
    private LocalDateTime validTo = null;

    public DimHousehold() {}

    public void copyFrom(Household other) {
        this.address = other.getAddress();
        this.surface = other.getSurface();
        this.cattle = other.getCattle();
        this.swine = other.getSwine();
        this.sheep = other.getSheep();
        this.goats = other.getGoats();
        this.equines = other.getEquines();
        this.poultry = other.getPoultry();
        this.rabbits = other.getRabbits();
        this.donkeys = other.getDonkeys();
        this.beeFamilies = other.getBeeFamilies();
        this.otherAnimals = other.getOtherAnimals();
    }

    public Integer getHouseholdSk() {
        return householdSk;
    }
    public void setHouseholdSk(Integer householdSk) {
        this.householdSk = householdSk;
    }

    public Integer getHouseholdId() {
        return householdId;
    }
    public void setHouseholdId(Integer householdId) {
        this.householdId = householdId;
    }

    public String getAddress() { return address; }
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

    public LocalDateTime getValidFrom() {
        return validFrom;
    }
    public void setValidFrom(LocalDateTime validFrom) {
        this.validFrom = validFrom;
    }

    public LocalDateTime getValidTo() {
        return validTo;
    }
    public void setValidTo(LocalDateTime validTo) {
        this.validTo = validTo;
    }
}
