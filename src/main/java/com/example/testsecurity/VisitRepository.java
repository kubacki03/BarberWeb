package com.example.testsecurity;

import com.example.testsecurity.DTO.VisitWithDateAndTimeDTO;
import com.example.testsecurity.Models.Visit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface VisitRepository extends JpaRepository<Visit, Long> {

    List<Visit> findAllByUserApp_Email(String email);

    @Query("Select u.dateStart AS visitDate, u.timeOfVisit AS visitTime from Visit u")
    List<VisitWithDateAndTimeDTO> getVisitWithDateAndTime();


    @Query("SELECT new com.example.testsecurity.DTO.VisitWithDateAndTimeDTO(v.dateStart, v.timeOfVisit) " +
            "FROM Visit v WHERE v.dateStart BETWEEN :startOfDay AND :endOfDay")
    List<VisitWithDateAndTimeDTO> findVisitsWithinDateRange(
            @Param("startOfDay") LocalDateTime startOfDay,
            @Param("endOfDay") LocalDateTime endOfDay);


}
