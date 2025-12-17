package com.xcell.GTManager.dto;

import com.xcell.GTManager.enums.EEducationLevel;
import com.xcell.GTManager.enums.EKinship;
import com.xcell.GTManager.enums.ESex;
import com.xcell.GTManager.model.tables.Person;

import java.time.LocalDate;

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
    public void setPersonId(Integer personId) { this.personId = personId; }

    public String getFirstName() { return firstName; }
    public void setFirstName(String firstName) { this.firstName = firstName; }

    public String getLastName() { return lastName; }
    public void setLastName(String lastName) { this.lastName = lastName; }

    public ESex getSex() { return sex; }
    public void setSex(ESex sex) { this.sex = sex; }

    public LocalDate getDateOfBirth() { return dateOfBirth; }
    public void setDateOfBirth(LocalDate dateOfBirth) { this.dateOfBirth = dateOfBirth; }

    public String getCNP() { return CNP; }
    public void setCNP(String CNP) { this.CNP = CNP; }

    public String getCitizenship() { return citizenship; }
    public void setCitizenship(String citizenship) { this.citizenship = citizenship; }

    public Integer getHouseholdId() { return householdId; }
    public void setHouseholdId(Integer householdId) { this.householdId = householdId; }

    public EKinship getKinship() { return kinship; }
    public void setKinship(EKinship kinship) { this.kinship = kinship; }

    public EEducationLevel getEducationLevel() { return educationLevel; }
    public void setEducationLevel(EEducationLevel educationLevel) { this.educationLevel = educationLevel; }

    public String getJob() { return job; }
    public void setJob(String job) { this.job = job; }

    public String getPlaceOfWork() { return placeOfWork; }
    public void setPlaceOfWork(String placeOfWork) { this.placeOfWork = placeOfWork; }

    public Integer getDegreesCount() { return degreesCount; }
    public void setDegreesCount(Integer degreesCount) { this.degreesCount = degreesCount; }
}
