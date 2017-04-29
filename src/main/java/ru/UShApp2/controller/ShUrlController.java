package ru.UShApp2.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import ru.UShApp2.model.ShUrl;
import ru.UShApp2.model.Tag;
import ru.UShApp2.service.ShUrlService;
import ru.UShApp2.service.TagService;

import java.util.List;

@Controller
public class ShUrlController {

    private TagService tagService;

    private ShUrlService shUrlService;

    @Autowired
    public ShUrlController(TagService tagService, ShUrlService shUrlService) {
        this.tagService = tagService;
        this.shUrlService = shUrlService;
    }


    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login(@ModelAttribute("shorturl") ShUrl shUrl, Model model, String error, String logout) {
        model.addAttribute("shUrl", new ShUrl());
        List<ShUrl> shUrls = this.shUrlService.getUrls();
        model.addAttribute("shUrls", shUrls);
        if (error != null) {
            model.addAttribute("error", "Неверное имя пользователя или пароль");
        }

        if (logout != null) {
            model.addAttribute("message", "Выход осуществлен!");
        }

        return "login";
    }


    @RequestMapping(value = "/shortu{idUrl}", method = RequestMethod.GET)
    public String shortu(@PathVariable("idUrl") Integer idUrl, Model model) {
        model.addAttribute("shUrl", this.shUrlService.getUrlById(idUrl));
        return "shortu";
    }

    @RequestMapping(value = "remove/{idUrl}")
    public String removeUrl(@PathVariable("idUrl") int idUrl) {
        this.shUrlService.removeUrl(idUrl);
        return "redirect:/main";
    }

    @RequestMapping(value = "edit/{idUrl}")
    public String editUrl(@PathVariable("idUrl") int idUrl, Model model) {
        model.addAttribute("shUrl", this.shUrlService.getUrlById(idUrl));
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String Username = userDetails.getUsername();
        model.addAttribute("shUrls", this.shUrlService.getUrlByUsername(Username));
        return "main";
    }

    @RequestMapping(value = "byTag/{tagname}", method = RequestMethod.GET)
    public String byTag(@PathVariable("tagname") String tagname, Model model) {
        List<ShUrl> shUrls = this.shUrlService.getUrlByTagname(tagname);
        model.addAttribute("shUrls", shUrls);

        return "byTag";
    }

    @RequestMapping(value = "tags", method = RequestMethod.GET)
    public String getTags(@ModelAttribute("tags") Tag tag, Model model) {
        List<Tag> tags = this.tagService.getTags();
        model.addAttribute("tags", tags);

        return "tags";
    }
}
