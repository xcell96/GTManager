package com.xcell.GTManager.model.repositories;

import com.xcell.GTManager.model.tables.DimHousehold;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

/**
 * JpaRepository for the {@link DimHousehold} entity.
 */
public interface DimHouseholdRepository extends JpaRepository<DimHousehold, Integer> {
    /**
     * Finds the latest historical record for a specific household.
     * @param id the household's ID
     * @return the latest historical record for the household
     */
    Optional<DimHousehold> findByHouseholdIdAndValidToIsNull(Integer id);

    /**
     * Finds all household records that were active during a specific time period.
     * @param fromDate the start of the time period
     * @param toDate the end of the time period
     * @return list of household records active during the specified period
     */
    @Query("SELECT h FROM DimHousehold h WHERE h.validFrom <= :toDate AND (h.validTo IS NULL OR h.validTo >= :fromDate)")
    List<DimHousehold> findByDateRange(@Param("fromDate") LocalDateTime fromDate, @Param("toDate") LocalDateTime toDate);

    /**
     * Finds all household records that were active at a specific point in time.
     * @param pointInTime the specific datetime
     * @return list of household records active at the specified time
     */
    @Query("SELECT h FROM DimHousehold h WHERE h.validFrom <= :pointInTime AND (h.validTo IS NULL OR h.validTo > :pointInTime)")
    List<DimHousehold> findByPointInTime(@Param("pointInTime") LocalDateTime pointInTime);

    /**
     * Finds all household records for a specific household that overlap with a time period.
     * @param householdId the household ID
     * @param fromDate the start of the time period
     * @param toDate the end of the time period
     * @return list of household records for the specific household during the period
     */
    @Query("""
    SELECT h FROM DimHousehold h
    WHERE h.householdId = :householdId
      AND (:fromDate IS NULL OR h.validTo   >= :fromDate)
      AND (:toDate   IS NULL OR h.validFrom <= :toDate)
    """)
    List<DimHousehold> findByHouseholdIdAndDateRange(@Param("householdId") Integer householdId,
                                                     @Param("fromDate") LocalDateTime fromDate,
                                                     @Param("toDate") LocalDateTime toDate);

    /**
     * Finds all historical records for a specific household ordered by valid from date descending.
     * @param householdId the household ID
     * @return list of household records for the specific household ordered by validFrom desc
     */
    List<DimHousehold> findByHouseholdIdOrderByValidFromDesc(Integer householdId);
}
