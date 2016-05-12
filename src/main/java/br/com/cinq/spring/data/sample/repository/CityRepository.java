package br.com.cinq.spring.data.sample.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.cinq.spring.data.sample.entity.City;
import br.com.cinq.spring.data.sample.entity.Country;

public interface CityRepository extends JpaRepository<City, Integer> {

    List<City> findByCountry(Country country);

}
