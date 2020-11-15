package com.fonseca.breweryapi.mapper.impl;

import com.fonseca.breweryapi.client.brewerydb.domain.Brewery;
import com.fonseca.breweryapi.dto.BreweryDTO;
import com.fonseca.breweryapi.mapper.MapperFunction;

public class BreweryToBreweryDBO implements MapperFunction<Brewery, BreweryDTO> {

    @Override
    public BreweryDTO map(Brewery brewery) {
        return BreweryDTO.newBuilder()
                .withId(brewery.getId())
                .withName(brewery.getName())
                .withBreweryType(brewery.getBreweryType())
                .withStreet(brewery.getStreet())
                .withAddress2(brewery.getAddress2())
                .withAddress3(brewery.getAddress3())
                .withCity(brewery.getCity())
                .withCountyProvince(brewery.getCountyProvince())
                .withState(brewery.getState())
                .withPostalCode(brewery.getPostalCode())
                .withCountry(brewery.getCountry())
                .withLongitude(brewery.getLongitude())
                .withLatitude(brewery.getLatitude())
                .withPhone(brewery.getPhone())
                .withWebsiteUrl(brewery.getWebsiteUrl())
                .withUpdatedAt(brewery.getUpdatedAt())
                .withCreatedAt(brewery.getCreatedAt())
                .build();
    }
}
