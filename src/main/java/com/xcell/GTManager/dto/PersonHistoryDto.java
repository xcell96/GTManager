package com.xcell.GTManager.dto;

import com.xcell.GTManager.enums.EEducationLevel;
import com.xcell.GTManager.enums.EKinship;
import com.xcell.GTManager.enums.ESex;
import com.xcell.GTManager.model.tables.DimPerson;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class PersonHistoryDto {

    private Integer personId;

    private String firstName;
    private String lastName;
    private ESex sex;
    private LocalDate dateOfBirth;

    private String CNP;
    private String citizenship;

    private EKinship kinship;
    private EEducationLevel educationLevel;

    private String job;
    private String placeOfWork;

    private Integer householdSk;

    private LocalDateTime validFrom;
    private LocalDateTime validTo;

    public static PersonHistoryDto fromEntity(DimPerson p) {
        PersonHistoryDto dto = new PersonHistoryDto();

        dto.personId = p.getPersonId();
        dto.firstName = p.getFirstName();
        dto.lastName = p.getLastName();
        dto.sex = p.getSex();
        dto.dateOfBirth = p.getDateOfBirth();

        dto.CNP = p.getCNP();
        dto.citizenship = p.getCitizenship();

        dto.kinship = p.getKinship();
        dto.educationLevel = p.getEducationLevel();

        dto.job = p.getJob();
        dto.placeOfWork = p.getPlaceOfWork();

        dto.householdSk = (p.getHousehold() != null ? p.getHousehold().getHouseholdSk() : null);

        dto.validFrom = p.getValidFrom();
        dto.validTo = p.getValidTo();

        return dto;
    }

    public Integer getPersonId() { return personId; }
    public String getFirstName() { return firstName; }
    public String getLastName() { return lastName; }
    public ESex getSex() { return sex; }
    public LocalDate getDateOfBirth() { return dateOfBirth; }
    public String getCNP() { return CNP; }
    public String getCitizenship() { return citizenship; }
    public EKinship getKinship() { return kinship; }
    public EEducationLevel getEducationLevel() { return educationLevel; }
    public String getJob() { return job; }
    public String getPlaceOfWork() { return placeOfWork; }
    public Integer getHouseholdSk() { return householdSk; }
    public LocalDateTime getValidFrom() { return validFrom; }
    public LocalDateTime getValidTo() { return validTo; }
}
