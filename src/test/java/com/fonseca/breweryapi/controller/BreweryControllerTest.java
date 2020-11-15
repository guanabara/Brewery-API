package com.fonseca.breweryapi.controller;

import com.fonseca.breweryapi.BreweryApiApplication;
import com.github.tomakehurst.wiremock.WireMockServer;
import org.apache.commons.io.IOUtils;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.io.IOException;
import java.io.InputStream;
import java.io.StringWriter;

import static com.github.tomakehurst.wiremock.client.WireMock.aResponse;
import static com.github.tomakehurst.wiremock.client.WireMock.get;
import static com.github.tomakehurst.wiremock.client.WireMock.post;
import static com.github.tomakehurst.wiremock.client.WireMock.urlEqualTo;
import static org.hamcrest.core.Is.is;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(
        webEnvironment = SpringBootTest.WebEnvironment.MOCK,
        classes = BreweryApiApplication.class)
@ActiveProfiles("test")
@AutoConfigureMockMvc
class BreweryControllerTest {

    private WireMockServer wireMockServer;

    @Autowired
    private MockMvc mockMvc;

    @BeforeEach
    public void setup() {
        this.wireMockServer = new WireMockServer(8282);
        this.wireMockServer.stubFor(post(urlEqualTo("/v1/introspect"))
                .willReturn(aResponse()
                        .withHeader("content-type", "application/json")
                        .withBody(readFile("stubs/validateToken.json"))));
        this.wireMockServer.start();
    }

    @AfterEach
    public void afterEach() {
        this.wireMockServer.stop();
    }

    @Test
    void testRequestWithoutToken() throws Exception {
        // Given
        int id = 5494;
        wireMockServer.stubFor(get("/breweries/" + id)
                .willReturn(aResponse()
                        .withHeader("content-type", "application/json")
                        .withBody(readFile("stubs/getBrewery.json"))));

        // When
        mockMvc.perform(MockMvcRequestBuilders.get("/breweries/" + id))

        // Then
                .andExpect(status().isUnauthorized());
    }

    @Test
    void testGetBrewery() throws Exception {
        // Given
        int id = 5494;
        wireMockServer.stubFor(get("/breweries/" + id)
                .willReturn(aResponse()
                        .withHeader("content-type", "application/json")
                        .withBody(readFile("stubs/getBrewery.json"))));

        // When
        mockMvc.perform(MockMvcRequestBuilders.get("/breweries/" + id)
                .header("Authorization", "Bearer token"))

        // Then
                .andExpect(status().isOk())
                .andExpect(jsonPath("id", is(id)));
    }

    @Test
    void testGetBreweryThatDoesntExists() throws Exception {
        // Given
        int id = 1324;
        wireMockServer.stubFor(get("/breweries/" + id)
                .willReturn(aResponse()
                        .withStatus(404)
                        .withHeader("content-type", "application/json")));

        // When
        mockMvc.perform(MockMvcRequestBuilders.get("/breweries/" + id)
                .header("Authorization", "Bearer token"))

        // Then
                .andExpect(status().isNotFound());
    }

    @Test
    void testSearchBreweries() throws Exception {
        // Given
        String query = "United_States";
        wireMockServer.stubFor(get("/breweries/search?query=" + query)
                .willReturn(aResponse()
                        .withHeader("content-type", "application/json")
                        .withBody(readFile("stubs/searchBreweries.json"))));

        // When
        mockMvc.perform(MockMvcRequestBuilders.get("/breweries/search?query=" + query)
                .header("Authorization", "Bearer token"))

        // Then
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id", is(5494)))
                .andExpect(jsonPath("$[1].id", is(543)));
    }

    @Test
    void testListBreweries() throws Exception {
        // Given
        String query = "page=3";

        wireMockServer.stubFor(get("/breweries?" + query)
                .willReturn(aResponse()
                        .withHeader("content-type", "application/json")
                        .withBody(readFile("stubs/listBreweries.json"))));

        // When
        mockMvc.perform(MockMvcRequestBuilders.get("/breweries?" + query)
                .header("Authorization", "Bearer token"))

        // Then
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id", is(2)))
                .andExpect(jsonPath("$[1].id", is(44)))
                .andExpect(jsonPath("$[2].id", is(46)));
    }


    private String readFile(String resource) {
        try {
            InputStream inputStream = this.getClass().getClassLoader()
                    .getResourceAsStream(resource);
            StringWriter writer = new StringWriter();
            IOUtils.copy(inputStream, writer, "utf-8");
            return writer.toString();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}