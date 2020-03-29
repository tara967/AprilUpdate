package com.example.SwimApp.config;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.example.SwimApp.model.Role;
import com.example.SwimApp.model.User;
import com.example.SwimApp.repositories.RoleRepository;
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
