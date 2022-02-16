package ru.kpfu.itis.vagaviev.dto;

import ru.kpfu.itis.vagaviev.model.User;
import ru.kpfu.itis.vagaviev.model.Weather;

public class WeatherDto {
      private int id;

      public int getId() {
            return id;
      }

      public void setId(int id) {
            this.id = id;
      }

      private long time;

      private String email;

      private String weather;

      public long getTime() {
            return time;
      }

      public void setTime(long time) {
            this.time = time;
      }

      public String getEmail() {
            return email;
      }

      public void setEmail(String email) {
            this.email = email;
      }

      public String getWeather() {
            return weather;
      }

      public void setWeather(String weather) {
            this.weather = weather;
      }

      public WeatherDto(Integer id, long time, String email, String weather) {
            this.id = id;
            this.time = time;
            this.email = email;
            this.weather = weather;
      }

      public static WeatherDto fromModel(Weather weather) {
            return new WeatherDto(weather.getId(), weather.getTime(), weather.getEmail(), weather.getWeather());
      }
}
