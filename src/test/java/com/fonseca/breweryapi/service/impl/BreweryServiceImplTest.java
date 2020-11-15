package com.fonseca.breweryapi.service.impl;

import com.fonseca.breweryapi.client.brewerydb.BreweryDBClient;
import com.fonseca.breweryapi.client.brewerydb.domain.Brewery;
import com.fonseca.breweryapi.client.brewerydb.domain.BreweryListFilter;
import com.fonseca.breweryapi.controller.filter.BreweriesFilter;
import com.fonseca.breweryapi.dto.BreweryDTO;
import com.fonseca.breweryapi.factory.BreweryFactory;
import com.fonseca.breweryapi.factory.BreweryFiltersFactory;
import com.fonseca.breweryapi.mapper.Mapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class BreweryServiceImplTest {

    @Mock
    private BreweryDBClient breweryDBClientMock;

    @Mock
    private Mapper mapperMock;

    private BreweryServiceImpl target;

    @BeforeEach
    void Setup() {
        target = new BreweryServiceImpl(breweryDBClientMock, mapperMock);
    }

    @Test
    void testGetBrewery() {
        // Given
        String id = "299";
        Brewery aBrewery = BreweryFactory.aBrewery();
        BreweryDTO aBreweryDTO = BreweryFactory.aBreweryDTO();

        when(breweryDBClientMock.getBrewery(id))
                .thenReturn(aBrewery);
        when(mapperMock.map(aBrewery, Mapper.BREWERY_TO_BREWERY_DTO))
                .thenReturn(aBreweryDTO);

        // When
        BreweryDTO actual = target.getBrewery(id);

        // Then
        assertThat(actual).isEqualTo(aBreweryDTO);
    }

    @Test
    void testSearchBrewery() {
        // Given
        String query = "Almanac";
        List<Brewery> breweryList = List.of(BreweryFactory.aBrewery(),
                BreweryFactory.aBrewery(),
                BreweryFactory.aBrewery());
        List<BreweryDTO> breweryDTOList = List.of(BreweryFactory.aBreweryDTO(),
                BreweryFactory.aBreweryDTO(),
                BreweryFactory.aBreweryDTO());

        when(breweryDBClientMock.searchBrewery(query))
                .thenReturn(breweryList);
        when(mapperMock.map(breweryList, Mapper.BREWERY_TO_BREWERY_DTO))
                .thenReturn(breweryDTOList);

        // When
        List<BreweryDTO> actual = target.searchBrewery(query);

        // Then
        assertThat(actual).isEqualTo(breweryDTOList);
    }

    @Test
    void testListBrewery() {
        // Given
        BreweriesFilter filter = BreweryFiltersFactory.aBreweriesFilter();
        BreweryListFilter breweryListFilter = BreweryFiltersFactory.aBreweryListFilter();

        List<Brewery> breweryList = List.of(BreweryFactory.aBrewery(),
                BreweryFactory.aBrewery(),
                BreweryFactory.aBrewery());
        List<BreweryDTO> breweryDTOList = List.of(BreweryFactory.aBreweryDTO(),
                BreweryFactory.aBreweryDTO(),
                BreweryFactory.aBreweryDTO());

        when(breweryDBClientMock.listBreweries(breweryListFilter))
                .thenReturn(breweryList);
        when(mapperMock.map(breweryList, Mapper.BREWERY_TO_BREWERY_DTO))
                .thenReturn(breweryDTOList);
        when(mapperMock.map(filter, Mapper.CONTROLLER_BREWERY_FILTER_TO_CLIENT_BREWERY_FILTER))
                .thenReturn(breweryListFilter);

        // When
        List<BreweryDTO> actual = target.listBreweries(filter);

        // Then
        assertThat(actual).isEqualTo(breweryDTOList);
    }

}