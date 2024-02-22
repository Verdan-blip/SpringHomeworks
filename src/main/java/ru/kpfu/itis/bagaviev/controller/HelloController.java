package ru.kpfu.itis.bagaviev.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.kpfu.itis.bagaviev.api.key.ApiKeyProvider;
import ru.kpfu.itis.bagaviev.api.service.CbrApiService;
import ru.kpfu.itis.bagaviev.api.service.OpenWeatherApiService;

@RestController
public class HelloController {

    private final OpenWeatherApiService openWeatherApiService = new OpenWeatherApiService(
            ApiKeyProvider.OPEN_WEATHER_API_KEY
    );

    private final CbrApiService cbrApiService = new CbrApiService();


    @GetMapping("/weather/kazan")
    public String weatherInKazan() {
        String city = "Kazan";
        return openWeatherApiService.getCurrentWeather(city);
    }

    @GetMapping("/exchange_rate")
    public String currentExchangeRate() {
        return cbrApiService.getCurrentExchangeRate();
    }

}
