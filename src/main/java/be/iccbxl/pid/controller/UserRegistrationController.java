package be.iccbxl.pid.controller;

import be.iccbxl.pid.model.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import be.iccbxl.pid.controller.dto.UserRegistrationDto;
import org.springframework.web.bind.annotation.PostMapping;

@Controller


public class UserRegistrationController {


    @Autowired
    private UserService userService; //interface implementation

    public UserRegistrationController(UserService userService) {
        super();
        this.userService = userService;
    }


    @ModelAttribute("user")

    public UserRegistrationDto userRegistrationDto() {
        return new UserRegistrationDto();
    }



    @GetMapping("/registration")
    public String registrationForm() {
        return "registration";
    }
   @PostMapping("/registration")
   public String registerUserAccount(@ModelAttribute("user") UserRegistrationDto registrationDto) {
       userService.save(registrationDto);
       return "redirect:/registration?success";
   }



}
