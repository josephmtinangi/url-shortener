package io.github.josephmtinangi.urlshortener.controllers.api;

import io.github.josephmtinangi.urlshortener.models.Url;
import io.github.josephmtinangi.urlshortener.repositories.UrlRepository;
import io.github.josephmtinangi.urlshortener.utilities.Base62;
import io.github.josephmtinangi.urlshortener.utilities.Helper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping(path = "/api/urls")
public class UrlController {

    @Autowired
    private UrlRepository urlRepository;

    @Value("${app.url}")
    private String appUrl;

    @RequestMapping(path = "", method = RequestMethod.GET)
    public ResponseEntity<?> index() {

        List<Url> urls = urlRepository.findAll();

        return Helper.createResponse(urls, HttpStatus.OK);
    }

    @RequestMapping(path = "/shorten", method = RequestMethod.POST)
    public ResponseEntity<?> store(@RequestParam String link) {

        if (!Helper.validateUrl(link)) {

            HashMap<String, Object> errorMap = new HashMap<>();
            errorMap.put("message", "Url not valid");

            return Helper.createResponse(errorMap, HttpStatus.BAD_REQUEST);
        }

        link = Helper.sanitizeURL(link);

        Url existingUrl = urlRepository.findFirstByOriginalUrl(link);

        Url finalUrl = null;

        if (existingUrl == null) {
            System.out.println("URL does not exist");

            Url url = new Url();
            url.setOriginalUrl(link);

            Url savedUrl = urlRepository.save(url);

            String shortenedUrl = appUrl + "/" + Base62.toBase62(savedUrl.getId());

            savedUrl.setShortenedUrl(shortenedUrl);

            finalUrl = urlRepository.save(savedUrl);
        } else {
            System.out.println("URL exists");
            finalUrl = existingUrl;
        }

        return Helper.createResponse(finalUrl, HttpStatus.OK);
    }

    @RequestMapping(path = "/reverse", method = RequestMethod.POST)
    public ResponseEntity<?> reverse(@RequestParam String link) {

        System.out.println("Link = " + link);

        if (!Helper.validateUrl(link)) {

            HashMap<String, Object> errorMap = new HashMap<>();
            errorMap.put("message", "Url not valid");

            return Helper.createResponse(errorMap, HttpStatus.BAD_REQUEST);
        }

        link = Helper.sanitizeURL(link);

        String str = link.replace(Helper.sanitizeURL(appUrl) + "/", "");
        System.out.println("Str = " + str);

        Long id = Base62.toBase10(str);
        System.out.println("Id = " + id);

        Url url = urlRepository.findOne(id);

        if (url == null) {
            HashMap<String, Object> output = new HashMap<>();
            output.put("message", "Link not found");

            return Helper.createResponse(output, HttpStatus.BAD_REQUEST);
        }

        return Helper.createResponse(url, HttpStatus.OK);
    }
}
