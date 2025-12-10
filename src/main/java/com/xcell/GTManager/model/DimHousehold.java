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

}
