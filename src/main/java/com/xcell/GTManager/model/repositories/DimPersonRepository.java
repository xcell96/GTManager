package com.xcell.GTManager.model.repositories;

import com.xcell.GTManager.model.tables.DimPerson;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

/**
 * JpaRepository for the {@link DimPerson} entity.
 */
public interface DimPersonRepository extends JpaRepository<DimPerson, Integer> {
    /**
     * Finds the latest historical record for a specific person.
     * @param id the person's ID
     * @return the latest historical record for the person
     */
    Optional<DimPerson> findByPersonIdAndValidToIsNull(Integer id);

    /**
     * Finds all historical records for a specific person ordered by valid from date.
     * @param id the person's ID
     * @return a list of historical records for the person
     */
    List<DimPerson> findByPersonIdOrderByValidFromDesc(Integer id);

    /**
     * Retrieves the number of people that belonged to a specific household at a specific point in time.
     * The point in time is determined by the surrogate key of the historical record of the household.
     * @param householdSk the surrogate key of the historical record of the household
     * @return the number of people that belonged to the household
     */
    long countByHouseholdHouseholdSk(Integer householdSk);

    /**
     * Retrieves all people that belonged to a specific household at a specific point in time.
     * The point in time is determined by the surrogate key of the historical record of the household.
     * @param householdSk the surrogate key of the historical record of the household
     * @return the list of people that belonged to the household
     */
    List<DimPerson> findByHouseholdHouseholdSk(Integer householdSk);

    /**
     * Finds all person records that were active during a specific time period.
     * @param fromDate the start of the time period
     * @param toDate the end of the time period
     * @return list of person records active during the specified period
     */
    @Query("SELECT p FROM DimPerson p WHERE p.validFrom <= :toDate AND (p.validTo IS NULL OR p.validTo >= :fromDate)")
    List<DimPerson> findByDateRange(@Param("fromDate") LocalDateTime fromDate, @Param("toDate") LocalDateTime toDate);

    /**
     * Finds all person records that were active at a specific point in time.
     * @param pointInTime the specific datetime
     * @return list of person records active at the specified time
     */
    @Query("SELECT p FROM DimPerson p WHERE p.validFrom <= :pointInTime AND (p.validTo IS NULL OR p.validTo > :pointInTime)")
    List<DimPerson> findByPointInTime(@Param("pointInTime") LocalDateTime pointInTime);

    /**
     * Finds all person records for a specific person that overlap with a time period.
     * @param personId the person ID
     * @param fromDate the start of the time period
     * @param toDate the end of the time period
     * @return list of person records for the specific person during the period
     */
    @Query("SELECT p FROM DimPerson p WHERE p.personId = :personId AND p.validFrom <= :toDate AND (p.validTo IS NULL OR p.validTo >= :fromDate)")
    List<DimPerson> findByPersonIdAndDateRange(@Param("personId") Integer personId,
                                               @Param("fromDate") LocalDateTime fromDate,
                                               @Param("toDate") LocalDateTime toDate);

    /**
     * Finds all person records for people in a specific household that overlap with a time period.
     * @param householdId the household ID
     * @param fromDate the start of the time period
     * @param toDate the end of the time period
     * @return list of person records for people in the specific household during the period
     */
    @Query("SELECT p FROM DimPerson p WHERE p.household.householdId = :householdId AND p.validFrom <= :toDate AND (p.validTo IS NULL OR p.validTo >= :fromDate)")
    List<DimPerson> findByHouseholdIdAndDateRange(@Param("householdId") Integer householdId,
                                                  @Param("fromDate") LocalDateTime fromDate,
                                                  @Param("toDate") LocalDateTime toDate);
}
