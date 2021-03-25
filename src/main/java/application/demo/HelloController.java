package application.demo;


import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @RequestMapping("/")
    public String index(){
        return "Greetings from String Boot!";   // con l'annotazione REQUESTMAPPING permette di definire la strada , cio[ quale url deve corrispondere a la methode index()
    }
}
