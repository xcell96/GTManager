package com.xcell.GTManager.model.tables;

/*
 * household_id
 * address
 * surface
 *
 * cattle
 * swine
 * sheep
 * goats
 * equines
 * poultry
 * rabbits
 * donkeys
 * bee_families
 * other_animals
 */

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.math.BigDecimal;

@Entity
@Table(name = "Households")
public class Household {
    @Id @Column(name = "household_id", unique = true)
    private Integer householdId;

    @Column(name = "address")
    private String address;

    @Column(name = "surface")
    private BigDecimal surface;


    @Column(name = "cattle", nullable = false)
    private Integer cattle = 0;

    @Column(name = "swine", nullable = false)
    private Integer swine = 0;

    @Column(name = "sheep", nullable = false)
    private Integer sheep = 0;

    @Column(name = "goats", nullable = false)
    private Integer goats = 0;

    @Column(name = "equines", nullable = false)
    private Integer equines = 0;

    @Column(name = "poultry", nullable = false)
    private Integer poultry = 0;

    @Column(name = "rabbits", nullable = false)
    private Integer rabbits = 0;

    @Column(name = "donkeys", nullable = false)
    private Integer donkeys = 0;

    @Column(name = "bee_families", nullable = false)
    private Integer beeFamilies = 0;

    @Column(name = "other_animals", nullable = false)
    private Integer otherAnimals = 0;

    public Household() {}

    public void copyFrom(Household other) {
        this.address = other.address;
        this.surface = other.surface;
        this.cattle = other.cattle;
        this.swine = other.swine;
        this.sheep = other.sheep;
        this.goats = other.goats;
        this.equines = other.equines;
        this.poultry = other.poultry;
        this.rabbits = other.rabbits;
        this.donkeys = other.donkeys;
        this.beeFamilies = other.beeFamilies;
        this.otherAnimals = other.otherAnimals;
    }

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
}
