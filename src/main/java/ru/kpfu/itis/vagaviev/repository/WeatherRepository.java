package ru.kpfu.itis.vagaviev.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.kpfu.itis.vagaviev.model.Weather;

public interface WeatherRepository extends JpaRepository<Weather,Integer> {
}
