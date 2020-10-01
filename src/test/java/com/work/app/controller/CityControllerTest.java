package com.work.app.controller;

import com.work.app.exception.IntegrityViolationException;
import com.work.app.exception.LocaleIntegrationException;
import com.work.app.exception.ObjectNotFoundException;
import com.work.app.service.CityService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Collections;

import static com.work.app.mock.MockFactory.mockCity;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
public class CityControllerTest extends AbstractMVCController {

    @MockBean
    private CityService service;

    @Before
    public void setup() {
        super.setup();
    }

    @Test
    public void shouldSaveCity() throws Exception {
        when(service.saveCity(any())).thenReturn(mockCity());

        mockMvc.perform(post("/city")
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsBytes(mockCity())))
                .andExpect(status().isCreated());
    }

    @Test
    public void shouldNotSaveCityWithoutBody() throws Exception {
        when(service.saveCity(any())).thenReturn(mockCity());

        mockMvc.perform(post("/city")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{}"))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void shouldNotSaveCityWithSameName() throws Exception {
        when(service.saveCity(any())).thenThrow(new IntegrityViolationException("Londrina is already registered on our database"));

        mockMvc.perform(post("/city")
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsBytes(mockCity())))
                .andExpect(status().isUnprocessableEntity())
                .andExpect(jsonPath("$.message").value("Londrina is already registered on our database"));
    }

    @Test
    public void shouldNotSaveCityThatIsNotFromState() throws Exception {
        when(service.saveCity(any())).thenThrow(new LocaleIntegrationException("City is not from that state!"));

        mockMvc.perform(post("/city")
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsBytes(mockCity())))
                .andExpect(status().isUnprocessableEntity())
                .andExpect(jsonPath("$.message").value("City is not from that state!"));
    }

    @Test
    public void shouldFindCityByName() throws Exception {
        when(service.findByName(any())).thenReturn(mockCity());

        mockMvc.perform(get("/city/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void shouldNotFindCityByName() throws Exception {
        when(service.findByName(any())).thenThrow(new ObjectNotFoundException("city"));

        mockMvc.perform(get("/city/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.message").value("city not found!"));
    }

    @Test
    public void shouldFindCityByStateName() throws Exception {
        when(service.findByStateInitials(any())).thenReturn(Collections.singletonList(mockCity()));

        mockMvc.perform(get("/city/state/PR")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray());
    }



}