package ru.UShApp2.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import ru.UShApp2.model.ShUrl;
import ru.UShApp2.model.Tag;
import ru.UShApp2.model.User;
import ru.UShApp2.service.SecurityService;
import ru.UShApp2.service.ShUrlService;
import ru.UShApp2.service.UserService;
import ru.UShApp2.validator.UserValidator;

import java.util.List;


@Controller
public class UserController {

    private ShUrlService shUrlService;

    private UserService userService;

    private SecurityService securityService;

    private UserValidator userValidator;

    @Autowired
    public UserController(ShUrlService shUrlService, UserService userService, SecurityService securityService, UserValidator userValidator) {
        this.shUrlService = shUrlService;
        this.userService = userService;
        this.securityService = securityService;
        this.userValidator = userValidator;

    }


    @RequestMapping(value = "/registration", method = RequestMethod.GET)
    public String registration(Model model) {
        model.addAttribute("userForm", new User());

        return "registration";
    }

    @RequestMapping(value = "/registration", method = RequestMethod.POST)
    public String registration(@ModelAttribute("userForm") User userForm, BindingResult bindingResult) {
        userValidator.validate(userForm, bindingResult);

        if (bindingResult.hasErrors()) {
            return "registration";
        }

        userService.save(userForm);

        securityService.autoLogin(userForm.getUsername(), userForm.getConfirmPassword());

        return "redirect:/main";
    }


    @RequestMapping(value = {"/", "/main"}, method = RequestMethod.GET)
    public String main(@ModelAttribute("shorturl") ShUrl shUrl, Model model) {
        model.addAttribute("shUrl", new ShUrl());
        model.addAttribute("tag", new Tag());
        model.addAttribute("user", this.securityService.findLoggedInUsername());
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String Username = userDetails.getUsername();
        List<ShUrl> shUrls = this.shUrlService.getUrlByUsername(Username);
        model.addAttribute("shUrls", shUrls);
        return "/main";
    }


    @RequestMapping(value = "main/add", method = RequestMethod.POST)
    public String createUrl(@ModelAttribute("shUrl") ShUrl shUrl) {
        User user = shUrl.getUser();
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String Username1 = userDetails.getUsername();
        String Password1 = userDetails.getPassword();
        user.setUsername(Username1);
        user.setPassword(Password1);
        User user1 = this.userService.findByUsername(Username1);
        shUrl.setUser(user1);
        if (shUrl.getIdUrl() == 0) {
            this.shUrlService.createUrl(shUrl);
        } else {
            this.shUrlService.updateUrl(shUrl);
        }

        return "redirect:/main";
    }

}

