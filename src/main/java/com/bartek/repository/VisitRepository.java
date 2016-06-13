package com.bartek.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.bartek.entity.Visit;

@Repository
public interface VisitRepository extends JpaRepository<Visit, Long> {

	
	@Query(value = "SELECT * FROM visit WHERE year(date_of_visit) = :year", nativeQuery = true)
	List<Visit> findVisitsByYearOfVisit(@Param("year") String year); 

	@Query(value = "SELECT * FROM visit WHERE year(date_of_visit) = :year AND month(date_of_visit) = :month", nativeQuery = true)
	List<Visit> findVisitsByYearAndMonthOfVisit(@Param("year")String year, 
												@Param("month") String month);

	
}

