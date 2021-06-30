package com.zt.dr.repository;

import java.time.LocalDateTime;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.zt.dr.entity.InspectionTrip;


@Repository
public interface InspectionTripRepository extends JpaRepository<InspectionTrip, String>{

	@Query(nativeQuery = true,value = "update inspection_trip set status=:newStatus where id=:id")
	void updateStatusById(@Param("newStatus") String newStatus, @Param("id") String Id);
	
	@Modifying(clearAutomatically = true)
	@Transactional
	@Query(nativeQuery = true,value = "update inspection_trip set trip_start_time=:startTime, status=:newStatus where id=:id")
	void updateTripStartById(@Param("startTime") LocalDateTime startTime, @Param("newStatus") String newStatus, @Param("id") String Id);

}
