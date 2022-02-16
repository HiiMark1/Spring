package ru.kpfu.itis.vagaviev.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.kpfu.itis.vagaviev.dto.CreateUserDto;
import ru.kpfu.itis.vagaviev.dto.UserDto;
import ru.kpfu.itis.vagaviev.model.User;
import ru.kpfu.itis.vagaviev.repository.UserRepository;

import javax.validation.Valid;
import javax.xml.bind.DatatypeConverter;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.stream.Collectors;

@RestController
public class UserController {

      private final UserRepository userRepository;

      @Autowired
      public UserController(UserRepository userRepository) {
            this.userRepository = userRepository;
      }

      @GetMapping("/user")
      public Iterable<UserDto> getAll() {
            return userRepository.findAll().stream().map(UserDto::fromModel).collect(Collectors.toList());
      }

      @GetMapping("/user/{id}")
      public UserDto get(@PathVariable Integer id) {
            User user = userRepository.findById(id).orElse(null);
            if (user.equals(null))
                  return null;
            return UserDto.fromModel(user);
      }

      @PostMapping("/user")
      public UserDto createUser(@Valid @RequestBody CreateUserDto user) {
            String password;
            MessageDigest md;
            try {
                  md = MessageDigest.getInstance("MD5");
                  md.update(user.getPassword().getBytes());
                  byte[] digest = md.digest();
                  password = DatatypeConverter.printHexBinary(digest).toUpperCase();
            } catch (NoSuchAlgorithmException e) {
                  e.printStackTrace();
                  password = null;
            }
            return UserDto.fromModel(userRepository.save(new User(user.getName(), user.getEmail(), password)));
      }
}