package com.xcell.GTManager.model.tables;

import com.xcell.GTManager.enums.EEducationLevel;
import com.xcell.GTManager.enums.EKinship;
import com.xcell.GTManager.enums.ESex;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "DimPeople")
public class DimPerson {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "person_sk", unique = true)
    private Integer personSk;

    @Column(name = "person_id", nullable = false)
    private Integer personId;
    @Column(name = "first_name")
    private String firstName;
    @Column(name = "last_name")
    private String lastName;

    private ESex sex;
    @Column(name = "date_of_birth")
    private LocalDate dateOfBirth;
    private String CNP;
    private String citizenship;

    @ManyToOne
    @JoinColumn(name = "household_sk")
    private DimHousehold household;
    private EKinship kinship;

    @Column(name = "education_level")
    private EEducationLevel educationLevel;

    @OneToMany(mappedBy = "person", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Degree> degrees;

    private String job;
    @Column(name = "place_of_work")
    private String placeOfWork;

    @Column(name = "valid_from", nullable = false)
    private LocalDateTime validFrom;
    @Column(name = "valid_to")
    private LocalDateTime validTo = null;

    public DimPerson() {}

    public void copyFrom(Person other) {
        this.firstName = other.getFirstName();
        this.lastName = other.getLastName();
        this.sex = other.getSex();
        this.dateOfBirth = other.getDateOfBirth();
        this.CNP = other.getCNP();
        this.citizenship = other.getCitizenship();
        this.kinship = other.getKinship();
        this.educationLevel = other.getEducationLevel();
        this.degrees = other.getDegrees();
        this.placeOfWork = other.getPlaceOfWork();
        this.job = other.getJob();
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
    public void setCitizenship(String citizenship) { this.citizenship = citizenship; }

    public EKinship getKinship() {
        return kinship;
    }
    public void setKinship(EKinship kinship) {
        this.kinship = kinship;
    }

    public EEducationLevel getEducationLevel() {
        return educationLevel;
    }
    public void setEducationLevel(EEducationLevel educationLevel) {
        this.educationLevel = educationLevel;
    }

    public List<Degree> getDegrees() { return degrees; }
    public void setDegrees(List<Degree> degrees) { this.degrees = degrees; }

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
