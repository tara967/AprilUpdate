package com.example.SwimApp.config;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.example.SwimApp.model.Role;
import com.example.SwimApp.model.Slot;
import com.example.SwimApp.model.TimeTable;
import com.example.SwimApp.model.User;
import com.example.SwimApp.repositories.RoleRepository;
import com.example.SwimApp.repositories.TimeTableRepository;
import com.example.SwimApp.repositories.UserRepository;

@Component
public class SetupDataLoader implements
	ApplicationListener<ContextRefreshedEvent>{
	
	//this class will allow us to control the time table information further
	//this class tries to create new role
    boolean alreadySetup = false;
    
    @Autowired
    private UserRepository userRepository;
  
    @Autowired
    private RoleRepository roleRepository;
  
    @Autowired
    private TimeTableRepository timeTableRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder; //variable passwordEncoder
	
	
	@Override
	public void onApplicationEvent(ContextRefreshedEvent event) {
		
		if (alreadySetup)
            return;
     //make sure admin and student roles are present
        createRoleIfNotFound("ROLE_ADMIN");
        createRoleIfNotFound("ROLE_STUDENT");//Arrays.asList(null));
         
        //define roles for admin and user to their repositories using find by name
        //first time run application, tries to create a user called admin
        Role adminRole = roleRepository.findByName("ROLE_ADMIN");
        User user1=userRepository.findByUsername("admin"); //tries to call admin from user DB
        
        //checks if username is there or not, if not creates new user, if there not created
        if(user1!=null && !user1.getUsername().equals("admin"))
        {
        
        User user = new User();
        user.setUsername("admin");
     //saving the password  with encryption, its a dummy user for first run
        user.setPassword(passwordEncoder.encode("admin"));
      
        user.setRoles(Arrays.asList(adminRole));
        
        userRepository.save(user);
        }
 
        alreadySetup = true;		
		//calls loadTimetableData function
		loadTimeTableData();
	}

	private void loadTimeTableData()  {
		if(!timeTableRepository.findById((long) 1).isPresent())
		{
		try{
			
			//create 26 dates for the 26 slots over Saturday and Sunday
			//dates are set individually in case I want to schedule a break I can book a slot for myself
			String sDate1 = "12/04/2020 09:00:00";
			String sDate2 = "12/04/2020 09:30:00";  
			String sDate3 = "12/04/2020 10:00:00";  
			String sDate4 = "12/04/2020 10:30:00";  
			String sDate5 = "12/04/2020 11:00:00";  
			String sDate6 = "12/04/2020 11:30:00";  
			String sDate7 = "12/04/2020 12:00:00";  
			String sDate8 = "12/04/2020 12:30:00";  
			String sDate9 = "12/04/2020 13:00:00";  
			String sDate10 = "12/04/2020 13:30:00";  
			String sDate11 = "12/04/2020 14:00:00";
			String sDate12 = "12/04/2020 14:30:00";
			String sDate13 = "12/04/2020 15:00:00";
			
			String sDate14 = "13/04/2020 09:00:00";
			String sDate15 = "13/04/2020 09:30:00";
			String sDate16 = "13/04/2020 10:00:00";
			String sDate17 = "13/04/2020 10:30:00";
			String sDate18 = "13/04/2020 11:00:00";
			String sDate19 = "13/04/2020 11:30:00";
			String sDate20 = "13/04/2020 12:00:00";
			String sDate21 = "13/04/2020 12:30:00";
			String sDate22 = "13/04/2020 13:00:00";
			String sDate23 = "13/04/2020 13:30:00";
			String sDate24 = "13/04/2020 14:00:00";
			String sDate25 = "13/04/2020 14:30:00";
			String sDate26 = "13/04/2020 15:00:00";
			
		   
		   SimpleDateFormat formatter=new SimpleDateFormat("dd/MM/yyyy HH:mm:ss", Locale.ENGLISH);  
		
		TimeTable timeTable=new TimeTable();
		timeTable.setselectedDay(formatter.parse(sDate1));
		
		//set slots by creating a new Slot, a boolean if slot is booked or not
		//get the name of person that booked the slot from user input
		//set the booked time by parsing in the slot date eg sDate1 into date/time formatter
		
		Slot slot=new Slot();

		slot.setIsBooked(false);
		slot.setBookedBy("");
		slot.setBookedTime(formatter.parse(sDate1));
		slot.setTimeTable(timeTable);
		
		Slot slot1=new Slot();

		slot1.setIsBooked(false);
		slot1.setBookedBy("");
		slot1.setBookedTime(formatter.parse(sDate2));
		slot1.setTimeTable(timeTable);
		
		Slot slot2=new Slot();

		slot2.setIsBooked(false);
		slot2.setBookedBy("");
		slot2.setBookedTime(formatter.parse(sDate3));
		slot2.setTimeTable(timeTable);
		

		Slot slot3=new Slot();

		slot3.setIsBooked(false);
		slot3.setBookedBy("");
		slot3.setBookedTime(formatter.parse(sDate4));
		slot3.setTimeTable(timeTable);
		
		
		Slot slot4=new Slot();

		slot4.setIsBooked(false);
		slot4.setBookedBy("");
		slot4.setBookedTime(formatter.parse(sDate5));
		slot4.setTimeTable(timeTable);
		
		Slot slot5=new Slot();
		
		slot5.setIsBooked(false);
		slot5.setBookedBy("");
		slot5.setBookedTime(formatter.parse(sDate6));
		slot5.setTimeTable(timeTable);
		
		Slot slot6=new Slot();

		slot6.setIsBooked(false);
		slot6.setBookedBy("");
		slot6.setBookedTime(formatter.parse(sDate7));
		slot6.setTimeTable(timeTable);
		
		Slot slot7=new Slot();

		slot7.setIsBooked(false);
		slot7.setBookedBy("");
		slot7.setBookedTime(formatter.parse(sDate8));
		slot7.setTimeTable(timeTable);
		
		Slot slot8=new Slot();

		slot8.setIsBooked(false);
		slot8.setBookedBy("");
		slot8.setBookedTime(formatter.parse(sDate9));
		slot8.setTimeTable(timeTable);
		
		Slot slot9=new Slot();

		slot9.setIsBooked(false);
		slot9.setBookedBy("");
		slot9.setBookedTime(formatter.parse(sDate10));
		slot9.setTimeTable(timeTable);
		
		Slot slot10=new Slot();

		slot10.setIsBooked(false);
		slot10.setBookedBy("");
		slot10.setBookedTime(formatter.parse(sDate11));
		slot10.setTimeTable(timeTable);
		
		Slot slot11=new Slot();

		slot11.setIsBooked(false);
		slot11.setBookedBy("");
		slot11.setBookedTime(formatter.parse(sDate12));
		slot11.setTimeTable(timeTable);
		
		Slot slot12=new Slot();

		slot12.setIsBooked(false);
		slot12.setBookedBy("");
		slot12.setBookedTime(formatter.parse(sDate13));
		slot12.setTimeTable(timeTable);
		
		Slot slot13=new Slot();

		slot13.setIsBooked(false);
		slot13.setBookedBy("");
		slot13.setBookedTime(formatter.parse(sDate14));
		slot13.setTimeTable(timeTable);
		
		Slot slot14=new Slot();

		slot14.setIsBooked(false);
		slot14.setBookedBy("");
		slot14.setBookedTime(formatter.parse(sDate15));
		slot14.setTimeTable(timeTable);
		
		Slot slot15=new Slot();

		slot15.setIsBooked(false);
		slot15.setBookedBy("");
		slot15.setBookedTime(formatter.parse(sDate16));
		slot15.setTimeTable(timeTable);
		
		Slot slot16=new Slot();

		slot16.setIsBooked(false);
		slot16.setBookedBy("");
		slot16.setBookedTime(formatter.parse(sDate17));
		slot16.setTimeTable(timeTable);
		
		Slot slot17=new Slot();

		slot17.setIsBooked(false);
		slot17.setBookedBy("");
		slot17.setBookedTime(formatter.parse(sDate18));
		slot17.setTimeTable(timeTable);
		
		Slot slot18=new Slot();

		slot18.setIsBooked(false);
		slot18.setBookedBy("");
		slot18.setBookedTime(formatter.parse(sDate19));
		slot18.setTimeTable(timeTable);
		
		Slot slot19=new Slot();

		slot19.setIsBooked(false);
		slot19.setBookedBy("");
		slot19.setBookedTime(formatter.parse(sDate20));
		slot19.setTimeTable(timeTable);
		
		Slot slot20=new Slot();

		slot20.setIsBooked(false);
		slot20.setBookedBy("");
		slot20.setBookedTime(formatter.parse(sDate21));
		slot20.setTimeTable(timeTable);
		
		Slot slot21=new Slot();

		slot21.setIsBooked(false);
		slot21.setBookedBy("");
		slot21.setBookedTime(formatter.parse(sDate22));
		slot21.setTimeTable(timeTable);
		
		Slot slot22=new Slot();

		slot22.setIsBooked(false);
		slot22.setBookedBy("");
		slot22.setBookedTime(formatter.parse(sDate23));
		slot22.setTimeTable(timeTable);
		
		Slot slot23=new Slot();

		slot23.setIsBooked(false);
		slot23.setBookedBy("");
		slot23.setBookedTime(formatter.parse(sDate24));
		slot23.setTimeTable(timeTable);
		
		Slot slot24=new Slot();

		slot24.setIsBooked(false);
		slot24.setBookedBy("");
		slot24.setBookedTime(formatter.parse(sDate25));
		slot24.setTimeTable(timeTable);
		
		Slot slot25=new Slot();

		slot25.setIsBooked(false);
		slot25.setBookedBy("");
		slot25.setBookedTime(formatter.parse(sDate26));
		slot25.setTimeTable(timeTable);
		
		
		List<Slot> slotList=Arrays.asList(slot,slot1,slot2,slot3,slot4,slot5,slot6,slot7,slot8,slot9,slot10,
				slot11,slot12,slot13,slot14,slot15,slot16,slot17,slot18,slot19,slot20,slot21,slot22,slot23,
				slot24,slot25);
		
		//timetable.setSlot method with new List of type Slot 'slotList' passing in all the slots created
		//this sets the slots in the timetable
		timeTable.setSlot(slotList);
		
		timeTableRepository.save(timeTable);
		
		System.out.println("here1");
		}
		catch(Exception e)
		{
			System.out.println(e.getMessage());
		}
		}
	}
	
	//treats like spring
	//transactional is soring annotation, when trying to save to DB it tries to ensure that if somethign goes wrong
	//it can reverse to previous state i.e if server is stopped etc
	@Transactional
	private Role createRoleIfNotFound(String name) {
		
		Role role=roleRepository.findByName(name);
		//if role is null then a new role is established and a new name set. role is saved
		if(role==null)
		{
			role=new Role();
			role.setName(name);
			
			roleRepository.save(role);
		}
		return role;
	}

}