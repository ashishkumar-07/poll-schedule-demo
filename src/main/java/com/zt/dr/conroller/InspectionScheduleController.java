package com.zt.dr.conroller;
import java.time.LocalDateTime;
import java.util.List;

import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.zt.dr.dto.InspectionEventDetails;
import com.zt.dr.dto.InspectionEventDetailsI;
import com.zt.dr.dto.InspectionScheduleDetails;
import com.zt.dr.model.InspectionScheduleRequest;
import com.zt.dr.service.InspectionService;

@RestController
@RequestMapping("/api/iaas/v1")
public class InspectionScheduleController {
	
	@Autowired
	private InspectionService inspectionService;
	
	@GetMapping("/get-all-schedule-by-date")
	public List<InspectionEventDetailsI> getAllScheduleByDate(@NotNull @RequestParam LocalDateTime startTime, @RequestParam LocalDateTime endTime) {
		return inspectionService.findInspectionScheduleDetailsByDateRange(startTime, endTime);
		//Pagination might be required
	}
	
	@PostMapping("/")
	public void createSchedule(@RequestBody InspectionScheduleRequest inputRequest) {		
		inspectionService.save(inputRequest);		
	}

}
