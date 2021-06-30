package com.zt.dr.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class InspectionEventDetails {
	
	String inspectedEntityId;
	String eventId;
	String name;
	String model;
	String inspectionTripId;	
	String inspectionStatus;
	@JsonFormat(pattern = "yyyy-MM-dd")
	LocalDate eventDate;
	String inspectionScheduleId;
	@JsonFormat(pattern = "HH:mm:ss")
	LocalTime eventTime;
	String inspectionStatusProgress;
	String inspectionReportId;
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	LocalDateTime lastInspectionDateTime;
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	LocalDateTime nextInspectionDateTime;

}
