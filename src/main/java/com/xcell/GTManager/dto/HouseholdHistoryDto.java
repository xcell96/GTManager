package com.xcell.GTManager.dto;

import com.xcell.GTManager.model.tables.DimHousehold;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * Data Transfer Object for the {@link DimHousehold} entity.
 */
public class HouseholdHistoryDto {
    public Integer householdSk;
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

    public LocalDateTime validFrom;
    public LocalDateTime validTo;

    /**
     * Creates a new HouseholdHistoryDto from the given {@link DimHousehold} entity.
     * Uses the {@link DtoMapper} to map the entity's fields to the DTO's.
     *
     * @param h the {@link DimHousehold} entity to map from
     * @return a new HouseholdHistoryDto populated with the entity's data
     */
    public static HouseholdHistoryDto fromEntity(DimHousehold h) {
        HouseholdHistoryDto dto = new HouseholdHistoryDto();

        DtoMapper.apply(
                () -> dto.householdSk = h.getHouseholdSk(),
                () -> dto.householdId = h.getHouseholdId(),
                () -> dto.address = h.getAddress(),
                () -> dto.surface = h.getSurface(),
                () -> dto.cattle = h.getCattle(),
                () -> dto.swine = h.getSwine(),
                () -> dto.sheep = h.getSheep(),
                () -> dto.goats = h.getGoats(),
                () -> dto.equines = h.getEquines(),
                () -> dto.poultry = h.getPoultry(),
                () -> dto.rabbits = h.getRabbits(),
                () -> dto.donkeys = h.getDonkeys(),
                () -> dto.beeFamilies = h.getBeeFamilies(),
                () -> dto.otherAnimals = h.getOtherAnimals(),
                () -> dto.validFrom = h.getValidFrom(),
                () -> dto.validTo = h.getValidTo()
        );

        return dto;
    }
}
