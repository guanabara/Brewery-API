package com.fonseca.breweryapi.mapper.impl;

import com.fonseca.breweryapi.client.brewerydb.domain.Brewery;
import com.fonseca.breweryapi.dto.BreweryDTO;
import com.fonseca.breweryapi.factory.BreweryFactory;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class BreweryToBreweryDBOTest {

    private final BreweryToBreweryDBO target = new BreweryToBreweryDBO();

    @Test
    void testMap() {
        // Given
        Brewery brewery = BreweryFactory.aBrewery();

        // When
        BreweryDTO actual = target.map(brewery);

        // Then
        assertThat(actual).isEqualTo(BreweryFactory.aBreweryDTO());
    }
}