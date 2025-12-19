package com.xcell.GTManager.model.tables;

import com.xcell.GTManager.enums.EEducationLevel;
import com.xcell.GTManager.enums.EKinship;
import com.xcell.GTManager.enums.ESex;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * Represents a historical record for a {@link Person}.
 * <p>
 * The {@code personId} column represents the person for which a change occured,
 * while the {@code personSk} column is the primary key and counts all changes ever.
 */
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
    @JoinColumn(name = "household_sk", nullable = false)
    private DimHousehold household;
    private EKinship kinship;

    @Column(name = "education_level")
    private EEducationLevel educationLevel;

    private String job;
    @Column(name = "place_of_work")
    private String placeOfWork;

    @Column(name = "valid_from", nullable = false)
    private LocalDateTime validFrom;
    @Column(name = "valid_to")
    private LocalDateTime validTo = null;

    public DimPerson() {}

    /**
     * Copies all fields from the given {@link Person} entity.
     * @param other the Person entity to copy from
     */
    public void copyFrom(Person other) {
        this.personId = other.getPersonId();
        this.firstName = other.getFirstName();
        this.lastName = other.getLastName();
        this.sex = other.getSex();
        this.dateOfBirth = other.getDateOfBirth();
        this.CNP = other.getCNP();
        this.citizenship = other.getCitizenship();
        this.kinship = other.getKinship();
        this.educationLevel = other.getEducationLevel();
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
    public String getCitizenship() {
        return citizenship;
    }
    public EKinship getKinship() {
        return kinship;
    }
    public EEducationLevel getEducationLevel() {
        return educationLevel;
    }
    public String getJob() {
        return job;
    }
    public String getPlaceOfWork() {
        return placeOfWork;
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
    public void setValidTo(LocalDateTime validTo) { this.validTo = validTo; }
}
