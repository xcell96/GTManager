package com.xcell.GTManager.model.tables;

/*
 * person_id
 * first_name
 * last_name
 * sex
 * DOB
 * CNP
 * citizenship
 *
 * household_id (FK)
 * is_head (T/F)
 * kinship
 *
 * education level
 * degree
 * job
 * place_of_work
 */

import com.xcell.GTManager.enums.EEducationLevel;
import com.xcell.GTManager.enums.EKinship;
import com.xcell.GTManager.enums.ESex;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "People")
public class Person {
    @Id @Column(name = "person_id")
    private Integer personId;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "sex")
    private ESex sex;

    @Column(name = "date_of_birth")
    private LocalDate dateOfBirth;

    @Column(name = "cnp")
    private String CNP;

    @Column(name = "citizenship")
    private String citizenship;

    @ManyToOne
    @JoinColumn(name = "household_id")
    private Household household;

    @Column(name = "kinship", nullable = false)
    private EKinship kinship;

    // TODO: education related stuff should be arrays, not singles
    @Column(name = "education_level")
    private EEducationLevel educationLevel;

    @OneToMany(mappedBy = "person", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Degree> degrees = new ArrayList<>();

    @Column(name = "job")
    private String job;

    @Column(name = "place_of_work")
    private String placeOfWork;

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

    public Household getHousehold() {
        return household;
    }

    public void setHousehold(Household household) {
        this.household = household;
    }

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

//    public List<Degree> getDegrees() {
//        return degrees;
//    }
//
//    public void setDegrees(List<Degree> degrees) {
//        this.degrees = degrees;
//    }

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
}
