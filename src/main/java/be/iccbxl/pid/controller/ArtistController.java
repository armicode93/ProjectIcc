package be.iccbxl.pid.controller;
import java.util.List;

import be.iccbxl.pid.model.*;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;


@Controller
public class ArtistController {
    // permet d initialiser automatiquement l'attribut service par injection de depandence
    @Autowired
    ArtistService artistService; //permet utiliser methodes metiers qui manipulent donnee

    @Autowired
    private TypeRepository typeRepository;



    @GetMapping("/artists")

    public String index(Model model)  {
        List<Artist> artists = artistService.getAllArtists();

        model.addAttribute("artists",artists);
        model.addAttribute("title","Liste des artistes");



    return "artist/index";
    }

    @GetMapping("/artists/{id}") //methode show pour recuperer un artiste avec son id e affiche dans show.html
    public String show(Model model, @PathVariable("id") String id){ // path serve per far corrispondere id del url con quello della methode show
        Artist artist = artistService.getArtist(id);
        //get artist est utilie pour recuperer l'artiste dont l-id correspond,
        //nous l-ajoutons au modele avant de renvoyer le template show.html
        model.addAttribute("artist",artist);
        model.addAttribute("title", "Fiche d'un artiste");

        return "artist/show";
    }
    @GetMapping("/artists/edit/{id}")
    public String artistForm(Model model, @PathVariable("id") String id)
    {
        Artist artist = artistService.getArtist(id);
        //get artist est utilie pour recuperer l'artiste dont l-id correspond,
        //nous l-ajoutons au modele avant de renvoyer le template show.html
        model.addAttribute("artist",artist);
        model.addAttribute("title", "Fiche d'un artiste");




        return "artist/edit";
    } //ok
    @PostMapping("/artists/edit/{id}")
    public String artistSubmit(@Valid Artist artist, BindingResult result, ModelMap model)
    {
        if (result.hasErrors()) {
            return "artist/edit";
        }

        artistService.addArtist(artist);



        return "artist/show";

    }

    @GetMapping("/artists/add")

        public String artistsFormAdd(Model model)
        {
            model.addAttribute(new Artist());
            return "artist/add";
        }

        @PostMapping("/artists/add") //no funzionante
        public String artistSubmitAdd(@Valid Artist artist, BindingResult result, ModelMap model) //model attribute serve per recuperare gli input dei campi riempiti
        {
            if (result.hasErrors()) {
                return "artist/add";
            }


            /*artist.setFirstname(artist.getFirstname());
            artist.setLastname(artist.getLastname()); */

            model.addAttribute(new Artist());
            artistService.addArtist(artist);
           // model.addAttribute("artist", artistService.getAllArtists());
            return "artist/index";

        }


}
