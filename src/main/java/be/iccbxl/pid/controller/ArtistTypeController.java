package be.iccbxl.pid.controller;

import be.iccbxl.pid.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;
import java.util.List;

@Controller
public class ArtistTypeController {

    @Autowired
    ArtistTypeService artistTypeService;
    @Autowired
    ArtistService artistService;
    @Autowired
    TypeService service ;


    @GetMapping("/artistType/add_new_artist_type/{id}")
    public String addArtistTypeForm(Model model, @PathVariable("id") String id)
    {
        Artist artist = artistService.getArtist(id);
        List<Type> types = service.getAllType();


        //get artist est utilie pour recuperer l'artiste dont l-id correspond,
        //nous l-ajoutons au modele avant de renvoyer le template show.html
        model.addAttribute("artist",artist);
        model.addAttribute("types",types);
        model.addAttribute("title", "Fiche d'un artiste");

        return "artistType/addArtistType";
    }
    @PostMapping("/artistType/add_new_artist_type")
    public String addArtistTypeSubmit(@Valid @ModelAttribute("artist") Artist artist,@ModelAttribute("artistType") ArtistType artistType, BindingResult result, @PathVariable("id") String id, Model model)
    {






    artistType.setArtist(artist.getId());
    artistType.setType(artistType.getType());

       // artistType.setArtist(artistType.getArtist());


       model.addAttribute(new ArtistType());


        model.addAttribute("artistType", artistType);
        artistTypeService.save(artistType);

        return "redirect:/artists";



    }
}
