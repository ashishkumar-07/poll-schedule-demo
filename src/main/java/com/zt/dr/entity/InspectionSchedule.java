package com.zt.dr.entity;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;
import org.springframework.data.annotation.CreatedDate;

import com.vladmihalcea.hibernate.type.array.ListArrayType;
import com.zt.dr.status.InpsectionScheduleStatus;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@Data
@Builder
@AllArgsConstructor
@TypeDef(
	    name = "list-array",
	    typeClass = ListArrayType.class
)
public class InspectionSchedule {
	
	@Id
	@GeneratedValue(generator = "uuid")
	@GenericGenerator(name = "uuid", strategy = "uuid2")
	private String id;
	
	@CreatedDate
	private LocalDateTime createTime;
	
	private LocalDateTime scheduledStartTime;
	
	private LocalDateTime scheduledEndTime;
	
    @Type(type = "list-array")
    @Column(
        name = "REPORTING_TEAMS",
        columnDefinition = "character varying[]",
        nullable = true
    )
	private List<String> reportingTeamIds;
    
    @Type(type = "list-array")
    @Column(
        name = "INSPECTING_ENTITIES",
        columnDefinition = "VARCHAR[]"        
    )    
	private List<String> inspectingEntitiesIds;
    
    @Type(type = "list-array")
    @Column(
        name = "INSPECTED_ENTITIES",
        columnDefinition = "VARCHAR[]"
    )
	private List<String> inspectedEntityIds;
	
    @Enumerated(EnumType.STRING)
	private InpsectionScheduleStatus status;
    
    @Column(name="inspection_trip_id")
    private String inspectionTripId;
}
