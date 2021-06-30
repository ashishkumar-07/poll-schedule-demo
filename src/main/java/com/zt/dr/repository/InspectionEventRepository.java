package com.zt.dr.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.QueryHints;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.zt.dr.dto.InspectionEventDetailsI;
import com.zt.dr.entity.InspectionEvent;

@Repository
public interface InspectionEventRepository extends JpaRepository<InspectionEvent, String>{
	
	
	@Query(nativeQuery = true) //Query is defined under src/main/resource/META-INF

	List <InspectionEventDetailsI> findEventsByInspectionDate(@Param("startDate") LocalDate startDate, @Param("endDate") LocalDate endDate);

}
