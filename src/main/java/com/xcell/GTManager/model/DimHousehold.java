package com.xcell.GTManager.model;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "DimHouseholds")
public class DimHousehold {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer householdSk;

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

    @Column(nullable = false)
    private LocalDateTime validFrom;
    private LocalDateTime validTo;

    public DimHousehold() {}

    public DimHousehold(Integer householdId,
                        String address,
                        BigDecimal surface,
                        Integer cattle,
                        Integer swine,
                        Integer sheep,
                        Integer goats,
                        Integer equines,
                        Integer poultry,
                        Integer rabbits,
                        Integer donkeys,
                        Integer beeFamilies,
                        Integer otherAnimals,
                        LocalDateTime validFrom) {
        this.householdId = householdId;
        this.address = address;
        this.surface = surface;
        this.cattle = cattle;
        this.swine = swine;
        this.sheep = sheep;
        this.goats = goats;
        this.equines = equines;
        this.poultry = poultry;
        this.rabbits = rabbits;
        this.donkeys = donkeys;
        this.beeFamilies = beeFamilies;
        this.otherAnimals = otherAnimals;
        this.validFrom = validFrom;
    }

    @PrePersist
    public void beforeInsert(){
        if(validFrom == null) validFrom = LocalDateTime.now();
    }

    @PreUpdate
    public void beforeUpdate(){
        throw new UnsupportedOperationException("Do not update DimHousehold; insert new version instead.");
    }

    @PreRemove
    public void beforeDelete(){
        System.out.println("Deleting DimHousehold with id: " + householdSk);
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
