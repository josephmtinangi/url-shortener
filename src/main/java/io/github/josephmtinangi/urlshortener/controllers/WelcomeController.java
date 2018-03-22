package io.github.josephmtinangi.urlshortener.controllers;

import io.github.josephmtinangi.urlshortener.utilities.Helper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.HashMap;

@Controller
@RequestMapping(path = "/")
public class WelcomeController {

    @RequestMapping(path = "", method = RequestMethod.GET)
    public ResponseEntity<?> index() {

        HashMap<String, Object> output = new HashMap<>();
        output.put("message", "Welcome");

        return Helper.createResponse(output, HttpStatus.OK);
    }

}
