package be.iccbxl.pid.controller;

import be.iccbxl.pid.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Conditional;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@Controller
public class ReservationController {

    @Autowired
    ReservationService reservationService;

    @Autowired
    RepresentationService representationService;

    @Autowired
    UserService userService;
    @Autowired
    LocationService service;

    @GetMapping("reservations/index")
    public String index (Model model)
    {
        List<Reservation> reservations = reservationService.getAll();

        model.addAttribute("reservations",reservations);
        model.addAttribute("reservations",reservations);
        model.addAttribute("title","Liste des artistes");

        return "reservation/index";

    }



   /* @GetMapping("reservations/")
    public String show ()
    {

    }

    */

   @GetMapping("reservations/add")
           public String addForm(Model model)
    {


        List<Reservation> reservations = reservationService.getAll();
        List<Representation> representations = representationService.getAll();
        model.addAttribute("reservations",reservations);
        model.addAttribute("representations",representations);

        model.addAttribute("title","Liste des artistes");

        return "reservation/add";
    }

   /* @PostMapping("reservations/add")
    public String addSubmit(@Valid @ModelAttribute("reservation") Reservation reservation, BindingResult result, Model model)
    {
        reservation.getUser();
        reservation.getRepresentation();
        reservation.getPlaces();


    }

    */





  /*  @PostMapping("reservations/edit/{id}")
    {

    }

    */




}
