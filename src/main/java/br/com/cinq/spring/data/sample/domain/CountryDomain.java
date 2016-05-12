package br.com.cinq.spring.data.sample.domain;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import br.com.cinq.spring.data.sample.entity.City;
import br.com.cinq.spring.data.sample.entity.Country;
import br.com.cinq.spring.data.sample.repository.CityRepository;
import br.com.cinq.spring.data.sample.repository.CountryRepository;

/**
 * "Domain" classes exist just to control the session / border for JPA with the
 * "Transactional" annotation
 * @author Adriano
 */
@Component
public class CountryDomain {

    @Autowired
    private CountryRepository dao;

    @Transactional(readOnly = true)
    public List<Country> listCountries(String name) {

        if (name == null) {
            return dao.findAll();
        } else
            return dao.findLikeName(name);
    }
}