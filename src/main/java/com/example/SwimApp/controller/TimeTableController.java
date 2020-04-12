package com.example.SwimApp.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
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
	
	@RequestMapping(value = "/timeTable", method = RequestMethod.POST)
	@ResponseBody
    public String saveHosting(
    		@RequestBody Map<String, String> json
    ) throws ParseException {
		
		String bookedTime=json.get("bookedTime");
		String personName=json.get("personName");
System.out.println(bookedTime);
		
		System.out.println(personName);
		Slot slot=new Slot();
		
		SimpleDateFormat sdf3 = new SimpleDateFormat("EEE MMM dd HH:mm:ss zzz yyyy", Locale.ENGLISH);

	    Date date = null;
		try {
			date = sdf3.parse(bookedTime);

			slot.setBookedTime(date);
			slot.setBookedBy(personName);
			slot.setIsBooked(true);
			
			TimeTable timeTable=new TimeTable();
			
			timeTable.setSlot(Arrays.asList(slot));
			timeTable.setselectedDay(date);
			
			timeTableService.save(timeTable);
		} catch (ParseException ex) {
		    System.out.println(ex.toString());
		}
		
		
		return personName;
     
    }
	

	@GetMapping("/timeTable")
	public ModelAndView registration() {
		ModelAndView model = new ModelAndView("/timeTable");

		Slot slot=new Slot();

		slot.setIsBooked(true);
		slot.setBookedBy("Tara");
		slot.setBookedTime(new Date(""));



		Slot slot1=new Slot();

		slot1.setIsBooked(false);
		slot1.setBookedBy("sarwar");
		slot1.setBookedTime(new Date());

		Slot slot2=new Slot();

		slot2.setIsBooked(true);
		slot2.setBookedBy("nial");
		slot2.setBookedTime(new Date());


		Slot slot3=new Slot();

		slot3.setIsBooked(false);
		slot3.setBookedBy("colm");
		slot3.setBookedTime(new Date());




		List<Slot> slotList=Arrays.asList(slot,slot1,slot2,slot3);

		model.addObject(slotList);
		//model.addObject("userForm", new User());
		//model.addObject("roles", roles.stream().map(Role::getName).collect(Collectors.toList()));
		return model;
	}

}
