package com.zt.dr.dto;

import java.time.LocalDateTime;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

public interface InspectionScheduleDetails {
	
	String getInspectionScheduleId();
	String getInspectedEntityId();
	String getName();
	String getModel();
	String getStatus();
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	LocalDateTime getTripStartTime();
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	LocalDateTime getTripEndTime();
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	LocalDateTime getScheduledStartTime();
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	LocalDateTime getScheduledEndTime();
	String getEventType();
}
