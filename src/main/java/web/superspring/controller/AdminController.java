package web.superspring.controller;

import org.springframework.validation.BindingResult;
import web.superspring.model.Role;
import web.superspring.model.User;
import web.superspring.service.RoleService;
import web.superspring.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.security.Principal;
import java.util.HashSet;
import java.util.Set;


@Controller
@RequestMapping("/admin")
public class AdminController {
    private final UserService userService;
    private final RoleService roleService;
    @Autowired
    public AdminController(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }
    @GetMapping
    public String userPage(Principal principal, Model model) {
        model.addAttribute("newUser", new User());
        Set<Role> roles = new HashSet<>(roleService.getAllRoles());
        model.addAttribute("allRoles", roles);
        User user = userService.findUsersByEmail(principal.getName());
        model.addAttribute("all_users", user);
        model.addAttribute("users", userService.listUsers());
        return "users";
    }
    @GetMapping("/new")
    public String newUser(Model model) {
        model.addAttribute("user", new User());
        Set<Role> roles = new HashSet<>(roleService.getAllRoles());
        model.addAttribute("all_roles", roles);
        return "/new";
    }
    @PostMapping()
    public String create(@ModelAttribute("user")@Valid User user, BindingResult bindingResult) {
        if (bindingResult.hasErrors())
            return "redirect:/admin";
        userService.add(user);
        return "redirect:/admin";
    }
    @PatchMapping("/edit/{id}")
    public String update(@ModelAttribute("user")@Valid User user, BindingResult bindingResult) {
        if (bindingResult.hasErrors())
            return "redirect:/admin";
        userService.update(user);
        return "redirect:/admin";
    }
    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") int id) {
        userService.delete(id);
        return "redirect:/admin";
    }
}
