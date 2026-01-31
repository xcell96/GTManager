package com.xcell.GTManager.dto;

import com.xcell.GTManager.enums.EEducationLevel;
import com.xcell.GTManager.enums.EKinship;
import com.xcell.GTManager.enums.ESex;
import com.xcell.GTManager.model.tables.Person;

import java.time.LocalDate;
import java.util.Objects;

/**
 * A Data Transfer Object for the {@link Person} entity.
 */
public class PersonDto {
    private Integer personId;

    private String firstName;
    private String lastName;
    private ESex sex;

    private LocalDate dateOfBirth;

    private String CNP;
    private String citizenship;

    private Integer householdId;

    private EKinship kinship;
    private EEducationLevel educationLevel;

    private String job;
    private String placeOfWork;

    private Integer degreesCount;

    public PersonDto() {}

    /**
     * Creates a new PersonDto from the given {@link Person} entity.
     * Uses the {@link DtoMapper} to map the entity's fields to the DTO's.
     *
     * @param p the {@link Person} entity to map from
     * @return a new PersonDto populated with the entity's data
     */
    public static PersonDto fromEntity(Person p){
        PersonDto dto = new PersonDto();

        DtoMapper.apply(
                () -> dto.personId = p.getPersonId(),
                () -> dto.firstName = p.getFirstName(),
                () -> dto.lastName = p.getLastName(),
                () -> dto.sex = p.getSex(),
                () -> dto.dateOfBirth = p.getDateOfBirth(),
                () -> dto.CNP = p.getCNP(),
                () -> dto.citizenship = p.getCitizenship(),
                () -> dto.householdId = (p.getHousehold() != null ? p.getHousehold().getHouseholdId() : null),
                () -> dto.kinship = p.getKinship(),
                () -> dto.educationLevel = p.getEducationLevel(),
                () -> dto.job = p.getJob(),
                () -> dto.placeOfWork = p.getPlaceOfWork(),
                () -> dto.degreesCount = (p.getDegrees() != null ? p.getDegrees().size() : 0)
        );

        return dto;
    }

    public Integer getPersonId() { return personId; }
    public String getFirstName() { return firstName; }
    public String getLastName() { return lastName; }
    public ESex getSex() { return sex; }
    public LocalDate getDateOfBirth() { return dateOfBirth; }
    public String getCNP() { return CNP; }
    public String getCitizenship() { return citizenship; }
    public Integer getHouseholdId() { return householdId; }
    public EKinship getKinship() { return kinship; }
    public EEducationLevel getEducationLevel() { return educationLevel; }
    public String getJob() { return job; }
    public String getPlaceOfWork() { return placeOfWork; }
    public Integer getDegreesCount() { return degreesCount; }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        PersonDto personDto = (PersonDto) o;
        return Objects.equals(personId, personDto.personId) && Objects.equals(firstName, personDto.firstName) && Objects.equals(lastName, personDto.lastName) && sex == personDto.sex && Objects.equals(dateOfBirth, personDto.dateOfBirth) && Objects.equals(CNP, personDto.CNP) && Objects.equals(citizenship, personDto.citizenship) && Objects.equals(householdId, personDto.householdId) && kinship == personDto.kinship && educationLevel == personDto.educationLevel && Objects.equals(job, personDto.job) && Objects.equals(placeOfWork, personDto.placeOfWork) && Objects.equals(degreesCount, personDto.degreesCount);
    }

    @Override
    public int hashCode() {
        return Objects.hash(personId, firstName, lastName, sex, dateOfBirth, CNP, citizenship, householdId, kinship, educationLevel, job, placeOfWork, degreesCount);
    }

    public void setPersonId(Integer personId) {
        this.personId = personId;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setSex(ESex sex) {
        this.sex = sex;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public void setCNP(String CNP) {
        this.CNP = CNP;
    }

    public void setCitizenship(String citizenship) {
        this.citizenship = citizenship;
    }

    public void setHouseholdId(Integer householdId) {
        this.householdId = householdId;
    }

    public void setKinship(EKinship kinship) {
        this.kinship = kinship;
    }

    public void setEducationLevel(EEducationLevel educationLevel) {
        this.educationLevel = educationLevel;
    }

    public void setJob(String job) {
        this.job = job;
    }

    public void setPlaceOfWork(String placeOfWork) {
        this.placeOfWork = placeOfWork;
    }
}
