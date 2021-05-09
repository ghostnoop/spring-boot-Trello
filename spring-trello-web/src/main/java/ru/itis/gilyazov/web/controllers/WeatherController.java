package ru.itis.gilyazov.web.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.itis.gilyazov.api.dto.Weather;
import ru.itis.gilyazov.impl.services.WeatherServiceApi;

@RestController
public class WeatherController {

    private final WeatherServiceApi weatherServiceApi;

    @Autowired
    public WeatherController(WeatherServiceApi weatherServiceApi) {
        this.weatherServiceApi = weatherServiceApi;
    }

    @GetMapping(value = "/api/weather", produces = MediaType.APPLICATION_JSON_VALUE)
    public Weather weatherNow() {
        return weatherServiceApi.getWeatherNow();
    }
}
