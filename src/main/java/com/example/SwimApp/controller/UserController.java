package com.example.SwimApp.controller;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.example.SwimApp.config.UserValidator;
import com.example.SwimApp.model.Role;
import com.example.SwimApp.model.User;
import com.example.SwimApp.repositories.RoleRepository;
import com.example.SwimApp.service.SecurityService;
import com.example.SwimApp.service.UserService;


@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private SecurityService securityService;
    
    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private UserValidator userValidator;

    //when open /registration it comes to this function
    //trying to retrieve a page tries to make a 
    //model view of this, view page is the jsp page registration
    
    @GetMapping("/registration")
    public ModelAndView registration() {
    	ModelAndView model = new ModelAndView("/registration");
    	
    	//create new userForm assigned to new user
    	//first time open a page userForm has name password confirm password
    	//trying to map model
        model.addObject("userForm", new User());
        return model;
    }
    // <form:options items="${roles}" /> in registration.jsp
    //this function is returning an array list that has all roles in it
    @ModelAttribute("roles")
    public List<String> getRoles() {
    	
      //from DB its getting all the roles from the table roles i.e admin and student
    	List<Role> roles = roleRepository.findAll();
    	
    	//getting name of role and returning/ putting to list
    	//stream iterates array looking for the name and collected to array
    	return roles.stream().map(Role::getName).collect(Collectors.toList());
    }
    
    //Post of registration. trying to send a request, and save information
    //binding result is binding all information that is present, spring syntax
    //says if I have errors or not if binding is correct etc
    @PostMapping("/registration")
    public String registration(@ModelAttribute("userForm") User userForm, BindingResult bindingResult) {
        userValidator.validate(userForm, bindingResult);

        if (bindingResult.hasErrors()) {
            return "/registration";
        }
        
        //sets the userForm roles and saves to userService
        Role selectedrole=userService.findRoleByName(userForm.getSelectedRole());
        userForm.setRoles(Arrays.asList(selectedrole));
        userService.save(userForm);

        securityService.autoLogin(userForm.getUsername(), userForm.getPasswordConfirm());

        return "redirect:/welcome";
    }
	
    //try find error in login.jsp has <span>${error}</span> creating model attribute
    //mapping model to the view i.e login.jsp
    @GetMapping("/login")
    public String login(Model model, String error, String logout) {
    	//when you press submit, you pass error and logout (they are null to start with)
        if (error != null)
            model.addAttribute("error", "Your username and password is invalid.");

        if (logout != null)
            model.addAttribute("message", "You have been logged out successfully.");
        //tells view to go back to login page
        return "/login";
    }
    // must pass model attribute due to Spring
    @GetMapping("/welcome")
    public String welcome(Model model) {
        return "/welcome";
    }
    
    @GetMapping("/")
    public String mainPortal(Model model) {
        return "/welcome";
    }

}
