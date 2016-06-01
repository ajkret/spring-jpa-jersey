package br.com.cinq.spring.data.sample.domain;

import java.util.LinkedList;
import java.util.List;

import javax.transaction.Transactional;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

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
public class CityDomain {
    static Logger logger = LoggerFactory.getLogger(CityDomain.class);

    @Autowired
    private CountryRepository countryDao;

    @Autowired
    private CityRepository dao;

    @Transactional(Transactional.TxType.SUPPORTS)
    public List<City> listCities(Country country) {

        if (country == null) {
            return dao.findAll();
        } else
            return dao.findByCountry(country);
    }

    @Transactional(Transactional.TxType.SUPPORTS)
    public List<City> findCitiesByCountry(String name) {
        List<City> result = new LinkedList<City>();
        logger.info("Retrieving cities for {}", name);

        List<Country> countries = null;
        if (name == null) {
            countries = countryDao.findAll();
        } else
            countries = countryDao.findLikeName(name);

        for (Country country : countries) {
            List<City> cities = listCities(country);

            result.addAll(cities);
        }

        return result;
    }
}
