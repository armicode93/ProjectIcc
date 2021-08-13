package be.iccbxl.pid.controller;
import java.util.List;

import be.iccbxl.pid.model.*;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

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
    public String artistSubmit(@Valid @ModelAttribute("artist") Artist artist, BindingResult result,@PathVariable("id") String id, Model model)
    {
        if (result.hasErrors()) {
            return "artist/edit";
        }

        Artist existing = artistService.getArtist(id);

        if(existing==null) {
            return "artist/index";
        }

        Long indice = (long) Integer.parseInt(id);

        artist.setId(indice);
        artistService.updateArtist(artist.getId(), artist);

        model.addAttribute("artist", artist);

        return "redirect:/artists/"+artist.getId();

        /*if (result.hasErrors()) {
            return "artist/edit";
        }

        artistService.addArtist(artist);



        return "artist/show";

         */

    }

    @GetMapping("/artists/add")

        public String artistsFormAdd(Model model)
        {
            model.addAttribute(new Artist());
            return "artist/add";
        }

        @PostMapping("/artists/add") //no funzionante errore
        public String artistSubmitAdd(@Valid @ModelAttribute("artist") Artist artist, BindingResult result,ModelMap model) //model attribute serve per recuperare gli input dei campi riempiti
        {

            if (result.hasErrors()) {
                return "artist/add";
            }




            artist.setFirstname(artist.getFirstname());
            artist.setLastname(artist.getLastname());

            model.addAttribute(new Artist());
            artistService.addArtist(artist);
           // model.addAttribute("artist", artistService.getAllArtists());
            return "redirect:/artists"; //redirection apres la sauvgarde




        }

    @DeleteMapping("/artists/delete/{id}")
    public String delete(@PathVariable("id") String id, Model model) {
        Artist existing = artistService.getArtist(id);
        if(existing!=null) {
            Long indice = (long) Integer.parseInt(id);
            artistService.deleteArtist(indice);
        }
        return "redirect:/artists";
    }


}
