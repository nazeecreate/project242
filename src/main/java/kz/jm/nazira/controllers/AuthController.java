package kz.jm.nazira.controllers;

import kz.jm.nazira.dao.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.security.Principal;

@Controller
public class AuthController {
    @Autowired
    private UserDao userDao;

    @GetMapping("/user")
    public String getUserPage(Model model, Principal principal){
        model.addAttribute("user", userDao.getUserByName(principal.getName()));
        return "users/show";
    }

    @GetMapping("/admin")
    public String getAdminPage(){
        return "others/admin";
    }

}
