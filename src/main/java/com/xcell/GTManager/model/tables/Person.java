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
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "People")
public class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "person_id", unique = true)
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
    @JoinColumn(name = "household_id", nullable = false)
    private Household household;

    @Column(name = "kinship", nullable = false)
    private EKinship kinship;

    @Column(name = "education_level")
    private EEducationLevel educationLevel;

    @Column(name = "job")
    private String job;

    @Column(name = "place_of_work")
    private String placeOfWork;

    @OneToMany(
            mappedBy = "person",
            cascade = CascadeType.ALL,
            orphanRemoval = true,
            fetch = FetchType.LAZY
    )
    private Set<Degree> degrees = new HashSet<>();

    public void copyFrom(Person other) {
        this.firstName = other.firstName;
        this.lastName = other.lastName;
        this.sex = other.sex;
        this.dateOfBirth = other.dateOfBirth;
        this.CNP = other.CNP;
        this.citizenship = other.citizenship;
        this.household = other.household;
        this.kinship = other.kinship;
        this.educationLevel = other.educationLevel;
        this.job = other.job;
        this.placeOfWork = other.placeOfWork;
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

    public Set<Degree> getDegrees() { return degrees; }
    public void addDegree(Degree degree) {
        degrees.add(degree);
        degree.setPerson(this);
    }
    public void removeDegree(Degree degree) {
        degrees.remove(degree);
        degree.setPerson(null);
    }
}