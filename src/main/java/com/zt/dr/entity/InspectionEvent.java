package com.zt.dr.entity;

import java.time.LocalDate;
import java.time.LocalTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.hibernate.annotations.GenericGenerator;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class InspectionEvent {
	
	@Id
	@GeneratedValue(generator = "uuid")
	@GenericGenerator(name = "uuid", strategy = "uuid2")
    private String id;
	
	private String inspectedEntityId;
	
	private String inspectionTripId;
	
	private String inspectionStatus;
	
	private String inspectionScheduleId;
	
	private LocalDate eventDate;
	
	private LocalTime eventTime;
	
	private Integer inspectionStatusProgress;
	
	private String inspectionReportId;
	
	private String eventType;

}
