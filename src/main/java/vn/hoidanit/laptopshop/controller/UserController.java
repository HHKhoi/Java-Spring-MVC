package vn.hoidanit.laptopshop.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import vn.hoidanit.laptopshop.domain.User;
import vn.hoidanit.laptopshop.repository.UserRepository;
import vn.hoidanit.laptopshop.service.UserService;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;



@Controller
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping("/")
    public String getHomePage(Model model) {
        List<User> arrUsers = this.userService.getAllUsersByEmail("1@gmail.com");
        System.out.println(arrUsers);
        model.addAttribute("eric", "test");
        model.addAttribute("hoidanit", "from controller with model");
        return "hello";
    }

    @RequestMapping("/admin/user/create")
    public String getCreatePage(Model model) {
        model.addAttribute("newUser", new User());
        return "/admin/user/create";
    }

    @RequestMapping(value = "admin/user/create", method=RequestMethod.POST)
    public String createUserPage(Model model, @ModelAttribute("newUser") User hoidanit ) {
        this.userService.handleSaveUser(hoidanit);
        return "redirect:/admin/user";
    }

    @RequestMapping("/admin/user")
    public String getUserPage(Model model) {
        List<User> users = this.userService.getAllUsers();
        model.addAttribute("users1", users);
        return "/admin/user/table-user";
    }

    @RequestMapping("/admin/user/{hoidanit}")
    public String getUserDetailPage(Model model, @PathVariable long hoidanit) {
        User user = this.userService.getUserById(hoidanit);
        model.addAttribute("user", user);
        model.addAttribute("id", hoidanit);
        return "/admin/user/show";
    }
    
    @RequestMapping("/admin/user/update/{id}")
    public String getUserUpdatePage(Model model, @PathVariable long id) {
        model.addAttribute("newUser", new User());
        return "/admin/user/update";
    }
    
}