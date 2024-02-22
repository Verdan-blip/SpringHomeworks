package ru.kpfu.itis.bagaviev.api.service;

import ru.kpfu.itis.bagaviev.api.util.IOUtils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;

public class CbrApiService {

    private static final String PROTOCOL = "https";
    private static final String HOST = "www.cbr-xml-daily.ru";

    public String getCurrentExchangeRate() {
        String path = "/latest.js";
        String result;
        try {
            URL url = new URI(PROTOCOL, HOST, path, null).toURL();
            try (InputStream in = url.openStream()) {
                result = IOUtils.readAll(in);
            }
            return result.isEmpty() ? "Empty result" : result;
        } catch (URISyntaxException | MalformedURLException exception) {
            throw new RuntimeException("Failed to access host");
        } catch (IOException exception) {
            throw new RuntimeException("Failed to connect to host");
        }
    }

}
