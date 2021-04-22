package application.demo.controller;
import java.util.List;

import application.demo.model.ArtistService;
import application.demo.model.Artist;
import org.springframework.web.bind.annotation.PathVariable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class ArtistController {
    @Autowired // permet d initialiser automatiquement l'attribut service par injection de depandence
    ArtistService service; //permet utiliser methodes metiers qui manipulent donnee

    @GetMapping("/artists")

    public String index(Model model )  {
        List<Artist> artists = service.getAllArtists();

        model.addAttribute("artists",artists);
        model.addAttribute("title","Liste des artistes");



    return "artist/index";
    }

    @GetMapping("/artists/{id}") //methode show pour recuperer un artiste avec son id e affiche dans show.html
    public String show(Model model, @PathVariable("id") String id){ // path serve per far corrispondere id del url con quello della methode how
        Artist artist = service.getArtist(id);
        //get artist est utilie pour recuperer l'artiste dont l-id correspond,
        //nous l-ajoutons au modele avant de renvoyer le template show.html
        model.addAttribute("artist",artist);
        model.addAttribute("title", "Fiche d'un artiste");

        return "artist/show";
    }
}
