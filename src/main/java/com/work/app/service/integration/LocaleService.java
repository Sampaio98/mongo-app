package com.work.app.service.integration;

import com.fasterxml.jackson.databind.JsonNode;
import com.work.app.domain.City;
import com.work.app.domain.State;
import com.work.app.exception.LocaleIntegrationException;
import com.work.app.prop.Properties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class LocaleService {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private Properties properties;

    public City findByName(String name) {
        String url = properties.getCityFindUrl();
        JsonNode jsonNode = restTemplate.getForObject(url, JsonNode.class, name);
        if (jsonNode.isNull() || jsonNode.isEmpty()) {
            throw new LocaleIntegrationException("City not localized to validate on external API");
        }
        String cityName = jsonNode.get("nome").textValue();
        String initials = jsonNode.get("microrregiao").get("mesorregiao").get("UF").get("sigla").textValue();
        return new City(cityName, new State(initials));
    }

}
