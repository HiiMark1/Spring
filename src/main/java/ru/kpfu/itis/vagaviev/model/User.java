package ru.kpfu.itis.vagaviev.model;

import javax.persistence.*;

@Entity
@Table(name = "users")
public class User {
      @Id
      @GeneratedValue(strategy = GenerationType.AUTO)
      private Integer id;

      private String name;

      private String email;

      private String password;

      public String getPassword() {
            return password;
      }

      public void setPassword(String password) {
            this.password = password;
      }

      public User() {
      }

      public void setId(Integer id) {
            this.id = id;
      }

      public Integer getId() {
            return id;
      }

      public String getName() {
            return name;
      }

      public void setName(String name) {
            this.name = name;
      }

      public String getEmail() {
            return email;
      }

      public void setEmail(String email) {
            this.email = email;
      }

      public User(String name, String email, String password) {
            this.name = name;
            this.email = email;
            this.password = password;
      }
}