package be.iccbxl.pid.controller;

import be.iccbxl.pid.model.ArtistType;
import be.iccbxl.pid.model.Show;
import be.iccbxl.pid.model.ShowService;
import be.iccbxl.pid.model.Type;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

@Controller
public class ShowController {
    @Autowired
    ShowService service;
    private final Logger log = LoggerFactory.getLogger(ShowController.class); // affiche message que je veut en bas

    @GetMapping("/shows")
    public String index2(Model model) {
        List<Show> shows = service.getAll();


        model.addAttribute("shows", shows);
        model.addAttribute("title", "Liste des spectacles");

        return "show/index";
    }

    @GetMapping("/shows/{id}")
    public String show(Model model, @PathVariable("id") String id) {
        Show show = service.get(id);

        //Récupérer les artistes du spectacle et les grouper par type
        //exemple autore chi è, metteur en scene chi ecc ecc
        //treeMaps serve per creare una mappa ordinata,cioe nomi in ordine
        /* Map<Type, ArrayList<ArtistType>> collaborateurs = new TreeMap<>();
        for (ArtistType at : show.getArtistTypes()) {

            if (collaborateurs.get(at.getType()) == null ) {


                collaborateurs.put(at.getType(), new ArrayList<>());

            }

                collaborateurs.get(at.getType());
        }


                model.addAttribute("collaborateurs", collaborateurs); */

                model.addAttribute("show", show);
                model.addAttribute("title", "Fiche d'un show");

                return "show/show";
            }
        }