package be.iccbxl.pid.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {

    @GetMapping("/login")
    public String login(Model model)
    {

        return "login";
    }

    @GetMapping("/")
    public String home()
    {
        return "index";
    }




    /*@GetMapping("/rolse")
    public String role()
        {
        return "role/index";
        }


    @GetMapping("/artists")
    public String artist()
        {
        return "artist/index";
        }


        @GetMapping("/localities")
        public String localities()
        {
        return "locality/";
        }

    @GetMapping("/locations")
    public String location()
    {
        return "location/index" ;
    }
    @GetMapping("/representations")
    public String representation()
    {
        return "representation/index";
    }

    @GetMapping("/shows")
    public String show()
    {
        return "show/index";
    }

     */

}


