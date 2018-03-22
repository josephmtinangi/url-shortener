package io.github.josephmtinangi.urlshortener.utilities;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class Helper {

    public static ResponseEntity<?> createResponse(Object data, HttpStatus code) {

        return new ResponseEntity<>(data, code);
    }

    public static boolean validateUrl(String url) {

        return true;
    }

    public static String sanitizeURL(String url) {

        if (url.substring(0, 7).equals("http://"))
            url = url.substring(7);

        if (url.substring(0, 8).equals("https://"))
            url = url.substring(8);

        if (url.charAt(url.length() - 1) == '/')
            url = url.substring(0, url.length() - 1);
        return url;
    }
}
