package com.xcell.GTManager.model.tables;

import com.xcell.GTManager.enums.EEducationLevel;
import com.xcell.GTManager.enums.EKinship;
import com.xcell.GTManager.enums.ESex;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

/**
 * Represents a person.
 * <p>
 * The values {@code sex}, {@code educationLevel} and {@code kinship} are enumerated values.
 * Each person must be a member of one and only one {@link Household}.
 * {@code kinship} represents how two people relate to each other. Each household must have one head;
 * the other members will have their kinship according to their head (i.e. wife, child, parent, cousin, etc.)
 * The degrees of a person are stored in the {@link Degree} table.
 */
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
}