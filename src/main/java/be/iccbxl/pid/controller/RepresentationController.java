package be.iccbxl.pid.controller;

import be.iccbxl.pid.model.Representation;
import be.iccbxl.pid.model.RepresentationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Controller
public class RepresentationController {
    @Autowired
    RepresentationService service;
    private final Logger log = LoggerFactory.getLogger(ShowController.class);

    @GetMapping("/representations")

    public String index(Model model)
    {
        List<Representation> representations = service.getAll();

        model.addAttribute("representations",representations);
        model.addAttribute("title","Liste des representations");

        return "representation/index";
    }

    @GetMapping("/representations/{id}")

    public String Show(Model model , @PathVariable("id") String id)
    {
        Representation representation =service.get(id);

        model.addAttribute("representation",representation);
        model.addAttribute("date", representation.getWhen().toLocalDate()); //recuperation Date et Heure, pour eviter
        model.addAttribute("heure", representation.getWhen().toLocalTime());//de le faire dans le template
        model.addAttribute("title","Fiche d'une representation");

        return "representation/show";
    }

}
