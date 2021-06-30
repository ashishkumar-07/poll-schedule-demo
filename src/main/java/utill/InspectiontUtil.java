package utill;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import com.zt.dr.entity.InspectionSchedule;
import com.zt.dr.entity.InspectionTrip;
import com.zt.dr.entity.InspectionEvent;
import com.zt.dr.model.InspectionScheduleRequest;
import com.zt.dr.status.InpsectionScheduleStatus;
import com.zt.dr.status.InspectionEventStatus;

public class InspectiontUtil {

	public static InspectionSchedule convertInspectionScheduletDtoToEntity(InspectionScheduleRequest inputRequest) {
		
		System.out.println(inputRequest);
		return InspectionSchedule.builder()
						  .createTime(LocalDateTime.now())
						  .inspectedEntityIds(inputRequest.getInspectedEntityIds())
						  .inspectingEntitiesIds(inputRequest.getInspectingEntitiesIds())
						  .scheduledStartTime(inputRequest.getScheduledStartTime())
						  .scheduledEndTime(inputRequest.getScheduledEndTime())
						  .reportingTeamIds(inputRequest.getReportingTeamIds())
						  .status(InpsectionScheduleStatus.valueOf(inputRequest.getStatus().toUpperCase()))
						  .build();
		
	}


	public static InspectionTrip convertInspectionScheduletToTrip(InspectionSchedule inspectionSchedule) {
		return InspectionTrip.builder()
				.inspectionScheduleId(inspectionSchedule.getId().toString())
				.status("SCHEDULED")
				.build();
	}
   
	public static InspectionEvent convertInspectionScheduleToEvent(InspectionSchedule inspectionSchedule, String inspectionEntityId, InspectionEventStatus eventStatus, int progressPerc) {
		return InspectionEvent.builder()
		.inspectionScheduleId(inspectionSchedule.getId().toString())
		.inspectedEntityId(inspectionEntityId)
		.inspectionStatus(eventStatus.toString())
		.eventDate(LocalDate.now())
		.eventTime(LocalTime.now())
		.inspectionStatusProgress(progressPerc)
		.inspectionTripId(inspectionSchedule.getInspectionTripId())
		.build();
		
	}

}
