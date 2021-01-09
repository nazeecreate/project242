package kz.jm.nazira.controllers;

import kz.jm.nazira.dao.UserDao;
import kz.jm.nazira.model.Role;
import kz.jm.nazira.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

@Controller
@RequestMapping("admin/users")
public class UsersController {
    @Autowired
    private UserDao userDao;

    @GetMapping()
    public String index(Model model){
        model.addAttribute("users", userDao.index());
        return "users/index";
    }

    @GetMapping("/new")
    public String index(@ModelAttribute("user") User user){
        return "users/new";
    }

    @PostMapping()
    public String create(@ModelAttribute("user") User user, @RequestParam(value = "makeAdmin", required = false) String makeAdmin) {
        Set<Role> roles = new HashSet<>();
        roles.add(new Role(1L, "ROLE_USER"));
        if(makeAdmin != null)
        {
            roles.add(new Role(2L, "ROLE_ADMIN"));
        }
        user.setRoles(roles);

        userDao.save(user);
        return "redirect:/admin/users";
    }

    @GetMapping("/{id}/edit")
    public String edit(@PathVariable("id") Long id, Model model){
        model.addAttribute("user", userDao.show(id));
        return "users/edit";
    }

    @GetMapping("/{id}")
    public String show(@PathVariable("id") Long id, Model model){
        model.addAttribute("user", userDao.show(id));
        return "users/show";
    }

    @PatchMapping("/{id}")
    public String update(@PathVariable("id") Long id, @ModelAttribute("user") User user){
        userDao.update(id, user);
        return "redirect:/admin/users";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") Long id){
        userDao.delete(id);
        return "redirect:/admin/users";
    }
}
