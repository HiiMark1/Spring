package ru.kpfu.itis.vagaviev.model;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

public class Weather {
      @Id
      @GeneratedValue(strategy = GenerationType.AUTO)
      private Integer id;

      public Integer getId() {
            return id;
      }

      public void setId(Integer id) {
            this.id = id;
      }

      private long time;

      private String email;

      private String weather;

      public String getEmail() {
            return email;
      }

      public void setEmail(String email) {
            this.email = email;
      }

      public long getTime() {
            return time;
      }

      public void setTime(long time) {
            this.time = time;
      }

      public String getWeather() {
            return weather;
      }

      public void setWeather(String weather) {
            this.weather = weather;
      }

      public Weather(String email, long time, String weather) {
            this.email = email;
            this.time = time;
            this.weather = weather;
      }
}
