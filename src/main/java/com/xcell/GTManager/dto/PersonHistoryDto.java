package com.xcell.GTManager.dto;

import com.xcell.GTManager.enums.EEducationLevel;
import com.xcell.GTManager.enums.EKinship;
import com.xcell.GTManager.enums.ESex;
import com.xcell.GTManager.model.tables.DimPerson;

import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * A Data Transfer Object for the {@link DimPerson} entity.
 */
public class PersonHistoryDto {

    public Integer personSk;
    public Integer personId;

    public String firstName;
    public String lastName;
    public ESex sex;
    public LocalDate dateOfBirth;

    public String CNP;
    public String citizenship;

    public EKinship kinship;
    public EEducationLevel educationLevel;

    public String job;
    public String placeOfWork;

    public Integer householdSk;

    public LocalDateTime validFrom;
    public LocalDateTime validTo;

    /**
     * Creates a new PersonHistoryDto from the given {@link DimPerson} entity.
     * Uses the {@link DtoMapper} to map the entity's fields to the DTO's.
     *
     * @param p the {@link DimPerson} entity to map from
     * @return a new PersonHistoryDto populated with the entity's data
     */
    public static PersonHistoryDto fromEntity(DimPerson p) {
        PersonHistoryDto dto = new PersonHistoryDto();

        DtoMapper.apply(
                () -> dto.personSk = p.getPersonSk(),
                () -> dto.personId = p.getPersonId(),
                () -> dto.firstName = p.getFirstName(),
                () -> dto.lastName = p.getLastName(),
                () -> dto.sex = p.getSex(),
                () -> dto.dateOfBirth = p.getDateOfBirth(),
                () -> dto.CNP = p.getCNP(),
                () -> dto.citizenship = p.getCitizenship(),
                () -> dto.kinship = p.getKinship(),
                () -> dto.educationLevel = p.getEducationLevel(),
                () -> dto.job = p.getJob(),
                () -> dto.placeOfWork = p.getPlaceOfWork(),
                () -> dto.householdSk = (p.getHousehold() != null ? p.getHousehold().getHouseholdSk() : null),
                () -> dto.validFrom = p.getValidFrom(),
                () -> dto.validTo = p.getValidTo()
        );

        return dto;
    }
}
