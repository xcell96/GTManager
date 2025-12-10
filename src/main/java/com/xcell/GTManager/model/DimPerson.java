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
}
