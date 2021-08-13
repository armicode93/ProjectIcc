package be.iccbxl.pid.controller;

import be.iccbxl.pid.model.Artist;
import be.iccbxl.pid.model.Role;
import be.iccbxl.pid.model.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;


import javax.validation.Valid;
import java.util.List;

@Controller
public class RoleController {

    @Autowired
    RoleService service;

    @GetMapping("/roles")

    public String index2(Model model)
    {
        List<Role> roles = service.getAllRole();
        model.addAttribute("roles",roles);
        model.addAttribute("title","Liste de role");

        return "role/index";
    }

    @GetMapping("/roles/{id}")

    public String show(Model model, @PathVariable("id") String id)
    {
        Role role = service.getRole(id);

        model.addAttribute("role",role);
        model.addAttribute("Title","Ficher d'un role");

        return "role/show";
    }

    @GetMapping("roles/add/{id}")
    public String roleEditForm(Model model, @PathVariable("id") String id)
    {
        Role role = service.getRole(id);
        //get artist est utilie pour recuperer l'artiste dont l-id correspond,
        //nous l-ajoutons au modele avant de renvoyer le template show.html
        model.addAttribute("role",role);
        model.addAttribute("title", "Fiche d'un role");

        return "role/editRole";
    }

    @PostMapping("/roles/add/{id}")

    public String roleEditSubmit(@Valid Role role, BindingResult result, ModelMap model) {

        if (result.hasErrors()) {
            return "role/editRole";
        }

        service.add(role);



        return "role/show";

    }
    @PostMapping("/roles/delete/{id}")
    public String delete (@PathVariable("id") String id, Model model)
    {
        Role existing = service.getRole(id);
        if(existing!=null) {
            Long indice = (long) Integer.parseInt(id);

            service.delete(indice);
        }
        return "redirect:/roles";
    }
    }



