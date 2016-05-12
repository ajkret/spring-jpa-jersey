package br.com.cinq.spring.data.sample.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.com.cinq.spring.data.sample.entity.Country;

/**
 * Eye candy: implements a sample in using JpaRespositories
 */
public interface CountryRepository extends JpaRepository<Country, Integer> {

    @Query("SELECT e FROM Country e where name LIKE CONCAT('%',?1,'%')")
    List<Country> findLikeName(String name);
}
