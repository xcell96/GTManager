package com.xcell.GTManager.model.tables;

import jakarta.persistence.*;

@Entity
@Table(name = "Degrees")
public class Degree {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "degree_id", unique = true)
    private Integer degreeId;

    @ManyToOne
    @JoinColumn(name = "person_id", nullable = false)
    private DimPerson person;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "institution")
    private String institution;

    @Column(name = "graduation_year")
    private Integer graduationYear;

    public void copyFrom(Degree other) {
        this.person = other.person;
        this.title = other.title;
        this.institution = other.institution;
        this.graduationYear = other.graduationYear;
    }

    public Integer getDegreeId() {
        return degreeId;
    }

    public DimPerson getPerson() {
        return person;
    }
    public void setPerson(DimPerson person) {
        this.person = person;
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
