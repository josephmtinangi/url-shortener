package io.github.josephmtinangi.urlshortener.controllers;

import io.github.josephmtinangi.urlshortener.models.Url;
import io.github.josephmtinangi.urlshortener.repositories.UrlRepository;
import io.github.josephmtinangi.urlshortener.utilities.Base62;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping(path = "/")
public class WelcomeController {

    @Autowired
    private UrlRepository urlRepository;

    @RequestMapping(path = "", method = RequestMethod.GET)
    public String index() {

        return "welcome/index";
    }

    @RequestMapping(path = "/{suffix}", method = RequestMethod.GET)
    public String redirect(Model model, @PathVariable String suffix) {

        Long id = Base62.toBase10(suffix);
        System.out.println("Id = " + id);

        Url url = urlRepository.findOne(id);

        model.addAttribute("url", url);

        return "welcome/redirect";
    }
}
