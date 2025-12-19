package com.xcell.GTManager.model.tables;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * Represents a historical record for a {@link Household}.
 * <p>
 * The {@code householdId} column represents the household for which a change occured,
 * while the {@code householdSk} column is the primary key and counts all changes ever.
 */
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

    /**
     * Copies all fields from the given {@link Household} entity.
     * @param other the Household entity to copy from
     */
    public void copyFrom(Household other) {
        this.householdId = other.getHouseholdId();
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

    public Integer getHouseholdId() {
        return householdId;
    }
    public void setHouseholdId(Integer householdId) {
        this.householdId = householdId;
    }

    public String getAddress() { return address; }
    public BigDecimal getSurface() {
        return surface;
    }
    public Integer getCattle() {
        return cattle;
    }
    public Integer getSwine() {
        return swine;
    }
    public Integer getSheep() {
        return sheep;
    }
    public Integer getGoats() {
        return goats;
    }
    public Integer getEquines() {
        return equines;
    }
    public Integer getPoultry() {
        return poultry;
    }
    public Integer getRabbits() {
        return rabbits;
    }
    public Integer getDonkeys() {
        return donkeys;
    }
    public Integer getBeeFamilies() {
        return beeFamilies;
    }
    public Integer getOtherAnimals() {
        return otherAnimals;
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
