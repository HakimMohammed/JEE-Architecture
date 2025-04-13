package jee.mvc.tp3.web.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class SecurityController {

    @GetMapping("/403")
    public String notAuthorized() {
        return "security/notAuthorized";
    }

    @GetMapping("login")
    public String login() {
        return "security/login";
    }
}
