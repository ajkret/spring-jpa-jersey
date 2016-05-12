package br.com.cinq.spring.data.resource;

import java.util.LinkedList;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import br.com.cinq.spring.data.sample.domain.CityDomain;
import br.com.cinq.spring.data.sample.domain.CountryDomain;
import br.com.cinq.spring.data.sample.entity.City;
import br.com.cinq.spring.data.sample.entity.Country;

/**
 * Greet Service
 *
 * @author Adriano Kretschmer
 */
@Path("/")
public class SampleResource {
    Logger logger = LoggerFactory.getLogger(SampleResource.class);

    @Autowired
    CityDomain cityDomain;

    @Autowired
    CountryDomain countryDomain;

    @Path("/cities")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response findCities(@QueryParam("country") String name) {
        List<City> result = new LinkedList<City>();
        try {
            logger.info("Retrieving cities for {}", name);
            
            List<Country> countries = countryDomain.listCountries(name);
            
            for(Country country : countries) {
                List<City> cities = cityDomain.listCities(country);
                
                result.addAll(cities);
            }

        } catch (Exception e) {
            logger.error("An exception occurred while retrieving cities", e);
            return Response.status(Status.INTERNAL_SERVER_ERROR).entity("exception").build();
        }

        return Response.ok().entity(result).build();
    }
}
