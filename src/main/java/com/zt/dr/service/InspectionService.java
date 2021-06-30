package com.zt.dr.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zt.dr.dto.InspectionEventDetailsI;
import com.zt.dr.entity.InspectionEvent;
import com.zt.dr.entity.InspectionSchedule;
import com.zt.dr.entity.InspectionTrip;
import com.zt.dr.model.InspectionScheduleRequest;
import com.zt.dr.repository.InspectionEventRepository;
import com.zt.dr.repository.InspectionScheduleRepository;
import com.zt.dr.repository.InspectionTripRepository;
import com.zt.dr.status.InpsectionScheduleStatus;
import com.zt.dr.status.InspectionEventStatus;

import lombok.RequiredArgsConstructor;
import utill.InspectiontUtil;

@Service
@RequiredArgsConstructor
public class InspectionService {
	
    private final InspectionScheduleRepository inspectionScheduleRepository;
    private final InspectionEventRepository inspectionEventRepository;
    private final InspectionTripRepository inspectionTripRepository;
 
	@Transactional(rollbackFor = Throwable.class)
	public void save(InspectionScheduleRequest inputRequest) {
		
		InspectionSchedule savedInspectionSchedule = inspectionScheduleRepository.save(InspectiontUtil.convertInspectionScheduletDtoToEntity(inputRequest));
		
		InspectionTrip inspectionTrip = inspectionTripRepository.save(InspectiontUtil.convertInspectionScheduletToTrip(savedInspectionSchedule));
		
		savedInspectionSchedule.setInspectionTripId(inspectionTrip.getId().toString());
		
		inspectionScheduleRepository.save(savedInspectionSchedule);
		
		List<InspectionEvent> collect = savedInspectionSchedule
											.getInspectedEntityIds()
											.stream()
											.map(e->InspectiontUtil.convertInspectionScheduleToEvent(savedInspectionSchedule,e, InspectionEventStatus.SCHEDULED, 0))
											.collect(Collectors.toList());
		
		inspectionEventRepository.saveAll(collect);
	}
	
	public List<InspectionEventDetailsI> findInspectionScheduleDetailsByDateRange(LocalDateTime startTime,
			LocalDateTime endTime) {

		List<InspectionSchedule> fetchedSchedule = inspectionScheduleRepository.findSchedulesByDateRange(startTime,	endTime);

		triggerTheTripStart(fetchedSchedule);

		//Fetch the latest inspection status(or incident) of each device
		
		List<InspectionEventDetailsI> inspectionEvents = inspectionEventRepository
				.findEventsByInspectionDate(startTime.toLocalDate(), endTime.toLocalDate());

		// Pending fetch last and next time of each inspected entity

		return inspectionEvents;
	}


	private void triggerTheTripStart(List<InspectionSchedule> fetchedSchedule) {
		fetchedSchedule
				.stream()
				.filter(e -> e.getScheduledStartTime().isBefore(LocalDateTime.now()))
				.filter(e -> e.getStatus().equals(InpsectionScheduleStatus.SCHEDULED)).map(e -> e.getId())
				.forEach(this::updateScheduleAndTrip);

	}
	
	@Transactional(rollbackFor = Throwable.class)
	private void updateScheduleAndTrip(String inspectionScheduleId) {
		Optional<InspectionSchedule> inspectionScheduleOptional = inspectionScheduleRepository.findById(inspectionScheduleId);
		if(!inspectionScheduleOptional.isPresent()) {
			return;
		}
		
		InspectionSchedule inspectionSchedule = inspectionScheduleOptional.get();
		inspectionSchedule.setStatus(InpsectionScheduleStatus.IN_PROGRESS);
		
		inspectionScheduleRepository.save(inspectionSchedule);
		
		inspectionTripRepository.updateTripStartById(LocalDateTime.now(), InspectionEventStatus.IN_PROGRESS.toString(),inspectionSchedule.getInspectionTripId());
		
		inspectionSchedule
					.getInspectedEntityIds()
					.stream()
					.map(e->InspectiontUtil.convertInspectionScheduleToEvent(inspectionSchedule,e, InspectionEventStatus.IN_PROGRESS, 5))
					.forEach(inspectionEventRepository::save);
	}

}
