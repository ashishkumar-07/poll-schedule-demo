package com.zt.dr.repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.zt.dr.dto.InspectionScheduleDetails;
import com.zt.dr.entity.InspectionSchedule;

@Repository
public interface InspectionScheduleRepository extends JpaRepository<InspectionSchedule, String>{

   /*
	@Query(nativeQuery=true) // Query is defined under src/main/resources/META-INF
	List<InspectionScheduleDetails> findScheduledInspectionsByDateRange(@Param("startTime") LocalDateTime startTime, @Param("endTime") LocalDateTime endTime);
     */
	
	@Query(nativeQuery=true, value="select  * from inspection_schedule sch\r\n" + 
			"where \r\n" + 
			"	sch.scheduled_start_time >= :startTime\r\n" + 
			"and sch.scheduled_end_time <= COALESCE(:endTime, scheduled_end_time)")
	List<InspectionSchedule> findSchedulesByDateRange(@Param("startTime") LocalDateTime startTime, @Param("endTime") LocalDateTime endTime);
	

}
