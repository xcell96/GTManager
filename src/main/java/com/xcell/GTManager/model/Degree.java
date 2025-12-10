package com.xcell.GTManager.model;

import jakarta.persistence.*;

@Entity
@Table(name = "Degrees")
public class Degree {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "degree_id")
    private Integer degreeId;

    @Column(name = "person_id", nullable = false)
    private Integer personId;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "institution")
    private String institution;

    @Column(name = "graduation_year")
    private Integer graduationYear;

    public Integer getDegreeId() {
        return degreeId;
    }

    public void setDegreeId(Integer degreeId) {
        this.degreeId = degreeId;
    }

    public Integer getPersonId() {
        return personId;
    }

    public void setPersonId(Integer personId) {
        this.personId = personId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getInstitution() {
        return institution;
    }

    public void setInstitution(String institution) {
        this.institution = institution;
    }

    public Integer getGraduationYear() {
        return graduationYear;
    }

    public void setGraduationYear(Integer graduationYear) {
        this.graduationYear = graduationYear;
    }
}
