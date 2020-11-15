package com.fonseca.breweryapi.client.brewerydb;

import com.fonseca.breweryapi.client.brewerydb.domain.Brewery;
import com.fonseca.breweryapi.client.brewerydb.domain.BreweryListFilter;
import com.fonseca.breweryapi.client.brewerydb.http.HttpClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.RequestEntity;
import org.springframework.stereotype.Component;

import java.net.URI;
import java.util.List;

@Component
public class BreweryDBClient {

    private static final String GET_BREWERY_PATH = "/breweries/";
    private static final String GET_BREWERIES_PATH = "/breweries?";
    private static final String SEARCH_BREWERY_PATH = "/breweries/search?query=";

    private final HttpClient client;

    @Autowired
    public BreweryDBClient(HttpClient client) {
        this.client = client;
    }

    public Brewery getBrewery(String id) {
        RequestEntity<Void> requestEntity = RequestEntity
                .get(URI.create(client.getTargetUrl(GET_BREWERY_PATH) + id))
                .build();

        return client.request(requestEntity, Brewery.class);
    }

    public List<Brewery> listBreweries(BreweryListFilter breweryListFilter) {
        RequestEntity<Void> requestEntity = RequestEntity
                .get(URI.create(client.getTargetUrl(GET_BREWERIES_PATH) + breweryListFilter.buildQueryParams()))
                .build();

        return client.request(requestEntity, new ParameterizedTypeReference<List<Brewery>>() {
        });
    }

    public List<Brewery> searchBrewery(String query) {
        RequestEntity<Void> requestEntity = RequestEntity
                .get(URI.create(client.getTargetUrl(SEARCH_BREWERY_PATH) + query))
                .build();

        return client.request(requestEntity, new ParameterizedTypeReference<List<Brewery>>() {
        });
    }
}

