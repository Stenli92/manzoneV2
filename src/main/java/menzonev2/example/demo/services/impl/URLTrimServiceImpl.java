package menzonev2.example.demo.services.impl;

import menzonev2.example.demo.services.URLTrimService;

public class URLTrimServiceImpl implements URLTrimService {
    @Override
    public String trimURL(String link) {

        String [] tokens = link.split("=");

        String address = tokens[1];
        return address;
    }
}
