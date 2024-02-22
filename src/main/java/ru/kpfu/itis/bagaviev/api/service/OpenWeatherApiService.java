package ru.kpfu.itis.bagaviev.api.service;

import ru.kpfu.itis.bagaviev.api.util.IOUtils;

import java.io.*;
import java.net.*;

public class OpenWeatherApiService {

    private static final String PROTOCOL = "https";
    private static final String HOST = "api.openweathermap.org";


    private final String apiKey;

    public OpenWeatherApiService(String apiKey) {
        this.apiKey = apiKey;
    }

    public String getCurrentWeather(String city) {
        String path = "/data/2.5/weather";
        String query = String.format("q=%s&appid=%s", city, apiKey);
        String result;
        try {
            URL url = new URI(PROTOCOL, HOST, path, query, null).toURL();
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
