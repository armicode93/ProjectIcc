package be.iccbxl.pid.controller;

import be.iccbxl.pid.model.Locality;
import be.iccbxl.pid.model.LocalityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


import java.util.List;

@Controller

public class LocalityController {
    @Autowired
    LocalityService service;

    @GetMapping("/localities")

    public String index2(Model model){
        List<Locality> localities = service.getAllLocality();

        model.addAttribute("localities",localities);
        model.addAttribute("title","Liste de localite");

        return "locality/index";
    }

    @GetMapping("/localities/{id}")
    public String show(Model model, @PathVariable("id") String id)
    {
        Locality locality = service.getLocality(id);

        model.addAttribute("locality",locality);
        model.addAttribute("title","Fiche d'une localite");

        return "locality/show";

    }
}



