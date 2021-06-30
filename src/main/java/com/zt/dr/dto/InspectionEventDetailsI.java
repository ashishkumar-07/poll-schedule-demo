package com.zt.dr.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import com.fasterxml.jackson.annotation.JsonFormat;

public interface InspectionEventDetailsI {	
	String getInspectedEntityId();
	String getEventId();
	String getName();
	String getModel();
	String getInspectionTripId();	
	String getInspectionStatus();
	@JsonFormat(pattern = "yyyy-MM-dd")
	LocalDate getEventDate();
	String getInspectionScheduleId();
	@JsonFormat(pattern = "HH:mm:ss")
	LocalTime getEventTime();
	String getInspectionStatusProgress();
	String getInspectionReportId();
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	LocalDateTime getLastInspectionDateTime();
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	LocalDateTime getNextInspectionDateTime();
}
