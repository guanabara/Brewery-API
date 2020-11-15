package com.fonseca.breweryapi.client.brewerydb;

import com.fonseca.breweryapi.client.brewerydb.domain.Brewery;
import com.fonseca.breweryapi.client.brewerydb.domain.BreweryListFilter;
import com.fonseca.breweryapi.client.brewerydb.http.HttpClient;
import com.fonseca.breweryapi.factory.BreweryFactory;
import com.fonseca.breweryapi.factory.BreweryFiltersFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.RequestEntity;

import java.util.List;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class BreweryDBClientTest {

    @Mock
    private HttpClient client;

    private BreweryDBClient target;

    @BeforeEach
    void Setup() {
        target = new BreweryDBClient(client);
    }

    @Test
    void testGetBrewery() {
        // Then
        String id = "299";
        Brewery brewery = BreweryFactory.aBrewery();
        when(client.getTargetUrl(anyString()))
                .thenReturn("http://brewery.com/299");
        when(client.request(any(RequestEntity.class), eq(Brewery.class)))
                .thenReturn(brewery);
        // When
        Brewery actual = target.getBrewery(id);

        // Then
        assertThat(actual).isEqualTo(brewery);
    }

    @Test
    void testSearchBrewery() {
        // Then
        BreweryListFilter breweryListFilter = BreweryFiltersFactory.aBreweryListFilter();
        List<Brewery> breweryList = List.of(BreweryFactory.aBrewery(),
                BreweryFactory.aBrewery(),
                BreweryFactory.aBrewery());
        when(client.getTargetUrl(anyString()))
                .thenReturn("http://brewery.com/search?query=Almanac");
        when(client.request(any(RequestEntity.class), eq(new ParameterizedTypeReference<List<Brewery>>() {
        })))
                .thenReturn(breweryList);

        // When
        List<Brewery> actual = target.listBreweries(breweryListFilter);

        // Then
        assertThat(actual).isEqualTo(breweryList);
    }

    @Test
    void testListBreweries() {
        // Then
        String query = "Almanac";
        List<Brewery> breweryList = List.of(BreweryFactory.aBrewery(),
                BreweryFactory.aBrewery(),
                BreweryFactory.aBrewery());
        when(client.getTargetUrl(anyString()))
                .thenReturn("http://brewery.com/search?query=Almanac");
        when(client.request(any(RequestEntity.class), eq(new ParameterizedTypeReference<List<Brewery>>() {
        })))
                .thenReturn(breweryList);

        // When
        List<Brewery> actual = target.searchBrewery(query);

        // Then
        assertThat(actual).isEqualTo(breweryList);
    }
}