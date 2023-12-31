package com.katherine.rosyrelaxationbackend.admin.Controller;

import com.katherine.rosyrelaxationbackend.admin.service.UserService;
import com.katherine.rosyrelaxationbackend.admin.exception.UserNotFoundException;
import com.katherine.rosyrelaxationcommon.entity.Role;
import com.katherine.rosyrelaxationcommon.entity.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
public class UserController {

    private final UserService service;

    public UserController(UserService service) {
        this.service = service;
    }

    @GetMapping("/users")
    public String listAll(Model model) {
        List<User> listUsers = service.listAll();
        model.addAttribute("listUsers", listUsers);
        return "users";
    }

    @GetMapping("/users/new")
    public  String newUser(Model model) {
        List<Role> listRoles = service.listRoles();
        User user = new User();
        user.setEnabled(true);
        model.addAttribute("user", user);
        model.addAttribute("listRoles", listRoles);
        model.addAttribute("pageTitle", "Create New User");
        return "user_form";
    }

    @PostMapping("/users/save")
    public String saveUser(User user, RedirectAttributes redirectAttributes) {
        service.save(user);
        redirectAttributes.addFlashAttribute("message", "The user has been saved successfully.");
        redirectAttributes.addFlashAttribute("messageType", "success");
        return "redirect:/users";
    }

    @GetMapping("/users/edit/{id}")
    public String editUser(@PathVariable(name = "id") Integer id, RedirectAttributes redirectAttributes, Model model) {
        List<Role> listRoles = service.listRoles();
        try {
            User user = service.get(id);
            model.addAttribute("user", user);
            model.addAttribute("pageTitle", "Edit User (ID: " + id + ")");
            model.addAttribute("listRoles", listRoles);
            return "user_form";
        } catch (UserNotFoundException ex) {
            redirectAttributes.addFlashAttribute("message", ex.getMessage());
            redirectAttributes.addFlashAttribute("messageType", "error");
            return "redirect:/users";
        }

    }

    @GetMapping("/users/delete/{id}")
    public String deleteUser(@PathVariable(name = "id") Integer id,
                        RedirectAttributes redirectAttributes, Model model) {
        try {
            service.delete(id);
            redirectAttributes.addFlashAttribute("message",
                    "The user ID " + id + " has been deleted successfully.");
            redirectAttributes.addFlashAttribute("messageType", "success");
        } catch (UserNotFoundException ex) {
            redirectAttributes.addFlashAttribute("message", ex.getMessage());
            redirectAttributes.addFlashAttribute("messageType", "error");

        }
        return "redirect:/users";
    }

    @GetMapping("/users/{id}/enabled/{status}")
    public  String enableUser(@PathVariable(name = "id") Integer id,
                              @PathVariable(name = "status") boolean enabled,
                              RedirectAttributes redirectAttributes) {
        service.updateUserEnabledStatus(id, enabled);
        String status = enabled ? "enabled" : "disabled";
        String message = "The user ID " + id + " has been " + status;
        redirectAttributes.addFlashAttribute("message", message);
        if(enabled) {
            redirectAttributes.addFlashAttribute("messageType", "success");
        } else {
            redirectAttributes.addFlashAttribute("messageType", "error");
        }
        return "redirect:/users";

    }
}
