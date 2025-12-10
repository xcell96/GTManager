package com.xcell.GTManager.model;

import com.xcell.GTManager.enums.EKinship;
import com.xcell.GTManager.enums.ESex;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "DimPeople")
public class DimPerson {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer personSk;

    private Integer personId;
    private String firstName;
    private String lastName;

    private ESex sex;
    private LocalDate dateOfBirth;
    private String CNP;
    private String citizenship;

    @ManyToOne
    @JoinColumn(name = "household_sk")
    private DimHousehold household;
    private EKinship kinship;

    private String educationLevel;

    @OneToMany(mappedBy = "person", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Degree> degrees = new ArrayList<>();

    private String job;
    private String placeOfWork;

    @Column(nullable = false)
    private LocalDateTime validFrom;
    private LocalDateTime validTo;

    protected DimPerson() {}

    public DimPerson(Integer personId,
                     String firstName,
                     String lastName,
                     ESex sex,
                     LocalDate dateOfBirth,
                     String CNP,
                     String citizenship,
                     DimHousehold household,
                     EKinship kinship,
                     String educationLevel,
                     String job,
                     String placeOfWork,
                     LocalDateTime validFrom) {
        this.personId = personId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.sex = sex;
        this.dateOfBirth = dateOfBirth;
        this.CNP = CNP;
        this.citizenship = citizenship;
        this.household = household;
        this.kinship = kinship;
        this.educationLevel = educationLevel;
        this.job = job;
        this.placeOfWork = placeOfWork;
        this.validFrom = validFrom;
    }

    public DimHousehold getHousehold() {
        return household;
    }

    public void setHousehold(DimHousehold household) {
        this.household = household;
    }

    public Integer getPersonSk() {
        return personSk;
    }

    public void setPersonSk(Integer personSk) {
        this.personSk = personSk;
    }

    public Integer getPersonId() {
        return personId;
    }

    public void setPersonId(Integer personId) {
        this.personId = personId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public ESex getSex() {
        return sex;
    }

    public void setSex(ESex sex) {
        this.sex = sex;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getCNP() {
        return CNP;
    }

    public void setCNP(String CNP) {
        this.CNP = CNP;
    }

    public String getCitizenship() {
        return citizenship;
    }

    public void setCitizenship(String citizenship) {
        this.citizenship = citizenship;
    }

    public EKinship getKinship() {
        return kinship;
    }

    public void setKinship(EKinship kinship) {
        this.kinship = kinship;
    }

    public String getEducationLevel() {
        return educationLevel;
    }

    public void setEducationLevel(String educationLevel) {
        this.educationLevel = educationLevel;
    }

    public List<Degree> getDegrees() {
        return degrees;
    }

    public void setDegrees(List<Degree> degrees) {
        this.degrees = degrees;
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }

    public String getPlaceOfWork() {
        return placeOfWork;
    }

    public void setPlaceOfWork(String placeOfWork) {
        this.placeOfWork = placeOfWork;
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
