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

    @GetMapping("/registration")
    public ModelAndView registration() {
    	ModelAndView model = new ModelAndView("/registration");
    	
        model.addObject("userForm", new User());
        //model.addObject("roles", roles.stream().map(Role::getName).collect(Collectors.toList()));
        return model;
    }

    @ModelAttribute("roles")
    public List<String> getRoles() {
      
    	List<Role> roles = roleRepository.findAll();
    	
    	return roles.stream().map(Role::getName).collect(Collectors.toList());
    }
    
    @PostMapping("/registration")
    public String registration(@ModelAttribute("userForm") User userForm, BindingResult bindingResult) {
        userValidator.validate(userForm, bindingResult);

        if (bindingResult.hasErrors()) {
            return "/registration";
        }
        
        Role selectedrole=userService.findRoleByName(userForm.getSelectedRole());
        userForm.setRoles(Arrays.asList(selectedrole));
        userService.save(userForm);

        securityService.autoLogin(userForm.getUsername(), userForm.getPasswordConfirm());

        return "redirect:/welcome";
    }
	
    @GetMapping("/login")
    public String login(Model model, String error, String logout) {
        if (error != null)
            model.addAttribute("error", "Your username and password is invalid.");

        if (logout != null)
            model.addAttribute("message", "You have been logged out successfully.");

        return "/login";
    }

    @GetMapping("/welcome")
    public String welcome(Model model) {
        return "/welcome";
    }
    
    @GetMapping("/")
    public String mainPortal(Model model) {
        return "/welcome";
    }
}
