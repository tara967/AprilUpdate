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

    boolean alreadySetup = false;
    
    @Autowired
    private UserRepository userRepository;
  
    @Autowired
    private RoleRepository roleRepository;
  
    @Autowired
    private TimeTableRepository timeTableRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;
	
	
	@Override
	public void onApplicationEvent(ContextRefreshedEvent event) {
		
		if (alreadySetup)
            return;
     
        createRoleIfNotFound("ROLE_ADMIN");
        createRoleIfNotFound("ROLE_STUDENT");//Arrays.asList(null));
         
        
        Role adminRole = roleRepository.findByName("ROLE_ADMIN");
        User user1=userRepository.findByUsername("admin");
        
        if(user1!=null && !user1.getUsername().equals("admin"))
        {
        
        User user = new User();
        user.setUsername("admin");
     
        user.setPassword(passwordEncoder.encode("admin"));
      
        user.setRoles(Arrays.asList(adminRole));
        
        userRepository.save(user);
        }
 
        alreadySetup = true;		
		
		loadTimeTableData();
	}

	private void loadTimeTableData()  {
		if(!timeTableRepository.findById((long) 1).isPresent())
		{
		try{
			
			
			
			
		   String sDate1 = "12/04/2020 10:00:00";  
		   String sDate2 = "12/04/2020 10:30:00";  
		   String sDate3 = "12/04/2020 11:00:00";  
		   String sDate4 = "12/04/2020 11:30:00";  
		   String sDate5 = "12/04/2020 12:00:00";  
		   String sDate6 = "12/04/2020 12:30:00";  
		   String sDate7 = "12/04/2020 13:00:00";  
		   String sDate8 = "12/04/2020 13:30:00";  
		   String sDate9 = "13/04/2020 11:00:00";  
		   String sDate10 = "13/04/2020 14:30:00";  
			
		   
		   
		   
		   SimpleDateFormat formatter=new SimpleDateFormat("dd/MM/yyyy HH:mm:ss", Locale.ENGLISH);  
		
		TimeTable timeTable=new TimeTable();
		timeTable.setselectedDay(formatter.parse(sDate1));
		Slot slot=new Slot();

		slot.setIsBooked(true);
		slot.setBookedBy("Tara");
		slot.setBookedTime(formatter.parse(sDate1));
		slot.setTimeTable(timeTable);
		
		Slot slot1=new Slot();

		slot1.setIsBooked(false);
		slot1.setBookedBy("sarwar");
		slot1.setBookedTime(formatter.parse(sDate2));
		slot1.setTimeTable(timeTable);
		
		Slot slot2=new Slot();

		slot2.setIsBooked(true);
		slot2.setBookedBy("nial");
		slot2.setBookedTime(formatter.parse(sDate3));
		slot2.setTimeTable(timeTable);
		

		Slot slot3=new Slot();

		slot3.setIsBooked(false);
		slot3.setBookedBy("colm");
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

		slot7.setIsBooked(true);
		slot7.setBookedBy("shane");
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
		
		
//
		List<Slot> slotList=Arrays.asList(slot,slot1,slot2,slot3,slot4,slot5,slot6,slot7,slot8,slot9);
		
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

	@Transactional
	private Role createRoleIfNotFound(String name) {
		// TODO Auto-generated method stub
		Role role=roleRepository.findByName(name);
		if(role==null)
		{
			role=new Role();
			role.setName(name);
			
			roleRepository.save(role);
		}
		return role;
	}

}
