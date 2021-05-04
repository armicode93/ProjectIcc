package application.demo.controller;

import application.demo.model.Role;
import application.demo.model.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Controller
public class RoleController {

    @Autowired
    RoleService service;

    @GetMapping("/roles")

    public String index2(Model model)
    {
        List <Role> roles = service.getAllRole();
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
}
