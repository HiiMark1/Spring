package ru.kpfu.itis.vagaviev.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.kpfu.itis.vagaviev.model.User;

public interface UserRepository extends JpaRepository<User, Integer> {
}