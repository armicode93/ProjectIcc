package be.iccbxl.pid.controller;

import be.iccbxl.pid.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class ArtistTypeController {

    @Autowired
    ArtistTypeService artistTypeService;
    @Autowired
    ArtistService artistService;


    @GetMapping("/artistType/add_new_artist_type/{id}")
    public String addArtistTypeForm(Model model, @PathVariable("id") Long id)
    {
        ArtistType artistType = artistTypeService.findOneById(id);
        //get artist est utilie pour recuperer l'artiste dont l-id correspond,
        //nous l-ajoutons au modele avant de renvoyer le template show.html
        model.addAttribute("artistType",artistType);
        model.addAttribute("title", "Fiche d'un artiste");

        return "artistType/addArtistType";
    }


}
