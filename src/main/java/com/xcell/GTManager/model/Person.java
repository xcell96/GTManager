package com.xcell.GTManager.model;

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

import java.time.LocalDate;

public class Person {
    private Integer personId;
    private String firstName;
    private String lastName;
    private ESex sex;
    private LocalDate dateOfBirth;
    private String CNP;
    private String citizenship;

    private Integer householdId;
    private EKinship kinship;

    // TODO: education related stuff should be arrays, not singles
    private EEducationLevel educationLevel;
    private String degree;
    private String job;
    private String placeOfWork;

    private Person(Builder b) {
        this.personId = b.personId;
        this.firstName = b.firstName;
        this.lastName = b.lastName;
        this.sex = b.sex;
        this.dateOfBirth = b.dateOfBirth;
        this.CNP = b.CNP;
        this.citizenship = b.citizenship;

        this.householdId = b.householdId;
        this.kinship = b.kinship;

        this.educationLevel = b.educationLevel;
        this.degree = b.degree;
        this.job = b.job;
        this.placeOfWork = b.placeOfWork;
    }

    public String getCitizenship() {
        return citizenship;
    }

    public Integer getPersonId() {
        return personId;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public ESex getSex() {
        return sex;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public String getCNP() {
        return CNP;
    }

    public Integer getHouseholdId() {
        return householdId;
    }

    public EKinship getKinship() {
        return kinship;
    }

    public EEducationLevel getEducationLevel() {
        return educationLevel;
    }

    public String getDegree() {
        return degree;
    }

    public String getJob() {
        return job;
    }

    public String getPlaceOfWork() {
        return placeOfWork;
    }

    public static class Builder {
        private Integer personId;
        private String firstName;
        private String lastName;
        private ESex sex;
        private LocalDate dateOfBirth;
        private String CNP;
        private String citizenship;

        private Integer householdId;
        private boolean isHead;
        private EKinship kinship;

        private EEducationLevel educationLevel;
        private String degree;
        private String job;
        private String placeOfWork;

        public Builder personId(Integer personId) { this.personId = personId; return this; }
        public Builder firstName(String firstName) { this.firstName = firstName; return this; }
        public Builder lastName(String lastName) { this.lastName = lastName; return this; }
        public Builder sex(ESex sex) { this.sex = sex; return this; }
        public Builder dateOfBirth(LocalDate dateOfBirth) { this.dateOfBirth = dateOfBirth; return this; }
        public Builder CNP(String CNP) { this.CNP = CNP; return this; }
        public Builder citizenship(String citizenship) { this.citizenship = citizenship; return this; }

        public Builder householdId(Integer householdId) { this.householdId = householdId; return this; }
        public Builder isHead(boolean isHead) { this.isHead = isHead; return this; }
        public Builder kinship(EKinship kinship) { this.kinship = kinship; return this; }

        public Builder educationLevel(EEducationLevel educationLevel) { this.educationLevel = educationLevel; return this; }
        public Builder degree(String degree) { this.degree = degree; return this; }
        public Builder job(String job) { this.job = job; return this; }
        public Builder placeOfWork(String placeOfWork) { this.placeOfWork = placeOfWork; return this; }

        public Person build() { return new Person(this); }
    }
}
