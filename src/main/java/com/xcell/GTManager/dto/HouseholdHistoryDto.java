package com.xcell.GTManager.dto;

import com.xcell.GTManager.model.tables.DimHousehold;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class HouseholdHistoryDto {
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

    public static HouseholdHistoryDto fromEntity(DimHousehold h) {
        HouseholdHistoryDto dto = new HouseholdHistoryDto();

        DtoMapper.apply(
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
