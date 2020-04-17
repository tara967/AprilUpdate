package com.example.SwimApp.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.example.SwimApp.model.Slot;
import com.example.SwimApp.model.TimeTable;
import com.example.SwimApp.service.TimeTableService;


@Controller
public class TimeTableController {

	
	@Autowired
	TimeTableService timeTableService;
	
	//when post request its trying to open this function
	@RequestMapping(value = "/timeTable", method = RequestMethod.POST)
	@ResponseBody
    public String saveHosting(
    		@RequestBody Map<String, String> json
    ) throws ParseException {
		
	//passed from ajax request puts name of person and the slot they booked
		//just updates the slot with the person name entered and books slot to be booked
		String personName=json.get("personName");
		String slotId=json.get("slotId");
        
		Slot slot=timeTableService.findSlotById(Long.parseLong(slotId));

		System.out.println(slotId);
		//sets slot to true because it is booked
			slot.setBookedBy(personName);
			slot.setIsBooked(true);
			
			timeTableService.updateSlot(slot);
	
		return "Slot updated";
     
    }
	//when called, prints all slots by finsing timetable with id=1 because i only have one
	//to have multiple, will get all of them and loop through all of them again to get timetable
	@GetMapping("/timeTable")
	public ModelAndView registration() {
		ModelAndView model = new ModelAndView("/timeTable");

        TimeTable timeTable=timeTableService.findById((long) 1);
        
        List<Slot> slotList=new ArrayList<Slot>();
		for(Slot slots : timeTable.getSlot())
        {
			LocalDate localDate = slots.getBookedTime().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
			
			Calendar cal = Calendar.getInstance();
			cal.setTime(slots.getBookedTime());
			int month = cal.get(Calendar.MONTH);
			
			int year=localDate.getYear();
			slots.getBookedTime().setMonth(month);
			slots.getBookedTime().setYear(year);
			
    		slotList.add(slots);
        }
		model.addObject(slotList);
		return model;
	}

}
