package be.iccbxl.pid.controller;

import be.iccbxl.pid.model.Artist;
import be.iccbxl.pid.model.Type;

import be.iccbxl.pid.model.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;
import java.util.List;

@Controller
public class TypeController {
    @Autowired
    TypeService service;

    @GetMapping("/types")


    public String index2(Model model) {
        List<Type> types = service.getAllType();
        model.addAttribute("types", types);
        model.addAttribute("title", "Liste des types");

        return "type/index";
    }

    @GetMapping("/types/{id}")

    public String show(Model model, @PathVariable("id") String id) {
        Type type = service.get(id);

        model.addAttribute("type", type);
        model.addAttribute("title", "Fiche d'un type");

        return "type/show";
    }

    @GetMapping("/types/edit/{id}")

    public String typeForm(Model model, @PathVariable("id") String id) {
        Type type = service.get(id);

        model.addAttribute("type",type);
        model.addAttribute("title","Fiche d'un type");

        return "type/edit";

    }

    @PostMapping("/types/edit/{id}")

    public String typeSubmit(@Valid String id , Type type, BindingResult result, ModelMap model) {

        if (result.hasErrors()) {
            return "type/edit";
        }

        service.update(id,type);



        return "type/show";

    }
}