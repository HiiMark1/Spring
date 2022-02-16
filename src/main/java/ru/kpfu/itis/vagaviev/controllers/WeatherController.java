package ru.kpfu.itis.vagaviev.controllers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.kpfu.itis.vagaviev.dto.UserDto;
import ru.kpfu.itis.vagaviev.dto.WeatherDto;
import ru.kpfu.itis.vagaviev.model.Weather;
import ru.kpfu.itis.vagaviev.repository.UserRepository;
import ru.kpfu.itis.vagaviev.repository.WeatherRepository;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
public class WeatherController {
      private final WeatherRepository weatherRepository;

      @Autowired
      public WeatherController(WeatherRepository weatherRepository) {
            this.weatherRepository = weatherRepository;
      }

      @GetMapping("/weatherRequests")
      public Iterable<WeatherDto> getAll() {
            return weatherRepository.findAll().stream().map(WeatherDto::fromModel).collect(Collectors.toList());
      }

      @GetMapping("/weather")
      public String weatherWithLogs(@RequestParam Optional<String> city, @RequestParam Optional<String> email) {
            WeatherDto.fromModel(weatherRepository.save(new Weather(email.toString(), System.currentTimeMillis(), getWeather(city))));
            return getWeather(city);
      }

      @GetMapping("/moscow")
      public String weather(@RequestParam Optional<String> city) {
            return getWeather(city);
      }

      private String getWeather(Optional<String> city) {
            StringBuilder content = new StringBuilder();
            try {
                  URL getUrl = new URL(String.format("http://api.openweathermap.org/data/2.5/weather?q=%s&" +
                          "appid=50da205a9c76cfaf41a554bc57768910", city.orElse("Moscow")));
                  HttpURLConnection connection = (HttpURLConnection) getUrl.openConnection();
                  connection.setRequestProperty("Content-Type", "application/json");
                  connection.setRequestMethod("GET");
                  try (BufferedReader reader = new BufferedReader(
                          new InputStreamReader(connection.getInputStream())
                  )) {
                        String input;
                        while ((input = reader.readLine()) != null) {
                              content.append(input);
                        }
                  }
                  connection.disconnect();
            } catch (IOException e) {
                  e.printStackTrace();
            }
            return content.toString();
      }
}