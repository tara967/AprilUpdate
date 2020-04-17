package com.example.SwimApp.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.example.SwimApp.model.User;
import com.example.SwimApp.service.UserService;


@Component
public class UserValidator implements Validator {
    @Autowired
    private UserService userService;

    @Override
    public boolean supports(Class<?> aClass) {
        return User.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        User user = (User) o;

        //this is a utility class offered to reject empty fields
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "username", "NotEmpty");
        
        //username must not be less than 6 or more than 32, reject based on size
        if (user.getUsername().length() < 6 || user.getUsername().length() > 32) {
            errors.rejectValue("username", "Size.userForm.username");
        }
        
        //Autowired
        if (userService.findByUsername(user.getUsername()) != null) {
            errors.rejectValue("username", "Duplicate.userForm.username");
        }

        //reject password with empty space, or size thats out of bounds
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "NotEmpty");
        if (user.getPassword().length() < 8 || user.getPassword().length() > 32) {
            errors.rejectValue("password", "Size.userForm.password");
        }

        //Confirms that it is the correct password, rejects it if the password is not right
        if (!user.getPasswordConfirm().equals(user.getPassword())) {
            errors.rejectValue("passwordConfirm", "Diff.userForm.passwordConfirm");
        }
        
        //reject if the user does not select a role when creating an account.
        //i.e they must select a role as either admin or student
        if ("NONE".equals(user.getSelectedRole()) ) {
            errors.rejectValue("selectedRole", "required.role");
          
        }
    }
}
