package com.fonseca.breweryapi.mapper.impl;

import com.fonseca.breweryapi.client.brewerydb.domain.BreweryListFilter;
import com.fonseca.breweryapi.controller.filter.BreweriesFilter;
import com.fonseca.breweryapi.factory.BreweryFiltersFactory;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class ControllerBreweryFilterTOClientBreweryFilterTest {

    private final ControllerBreweryFilterTOClientBreweryFilter target =
            new ControllerBreweryFilterTOClientBreweryFilter();

    @Test
    void testMap() {
        // Given
        BreweriesFilter breweriesFilter = BreweryFiltersFactory.aBreweriesFilter();

        // When
        BreweryListFilter actual = target.map(breweriesFilter);

        // Then
        assertThat(actual).isEqualTo(BreweryFiltersFactory.aBreweryListFilter());
    }
}