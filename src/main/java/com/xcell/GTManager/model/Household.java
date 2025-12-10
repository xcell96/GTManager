package com.xcell.GTManager.model;

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

public class Household {
    private Integer householdId;
    private String address;
    private Double surface;

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

    private Household(Builder b){
        this.householdId = b.householdId;
        this.address = b.address;
        this.surface = b.surface;

        this.cattle = b.cattle;
        this.swine = b.swine;
        this.sheep = b.sheep;
        this.goats = b.goats;
        this.equines = b.equines;
        this.poultry = b.poultry;
        this.rabbits = b.rabbits;
        this.donkeys = b.donkeys;
        this.beeFamilies = b.beeFamilies;
        this.otherAnimals = b.otherAnimals;
    }

    public Integer getHouseholdId() {
        return householdId;
    }

    public String getAddress() {
        return address;
    }

    public Double getSurface() {
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

    public static class Builder {
        private Integer householdId;
        private String address;
        private Double surface;

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

        public Builder householdId(Integer householdId){ this.householdId = householdId; return this; }
        public Builder address(String address){ this.address = address; return this; }
        public Builder surface(Double surface){ this.surface = surface; return this; }

        public Builder cattle(Integer cattle){ this.cattle = cattle; return this; }
        public Builder swine(Integer swine){ this.swine = swine; return this; }
        public Builder sheep(Integer sheep){ this.sheep = sheep; return this; }
        public Builder goats(Integer goats){ this.goats = goats; return this; }
        public Builder equines(Integer equines){ this.equines = equines; return this; }
        public Builder poultry(Integer poultry){ this.poultry = poultry; return this; }
        public Builder rabbits(Integer rabbits){ this.rabbits = rabbits; return this; }
        public Builder donkeys(Integer donkeys){ this.donkeys = donkeys; return this; }
        public Builder beeFamilies(Integer beeFamilies){ this.beeFamilies = beeFamilies; return this; }
        public Builder otherAnimals(Integer otherAnimals){ this.otherAnimals = otherAnimals; return this; }

        public Household build(){ return new Household(this); }
    }
}
