package com.fonseca.breweryapi.factory;

import com.fonseca.breweryapi.client.brewerydb.domain.Brewery;
import com.fonseca.breweryapi.dto.BreweryDTO;

public final class BreweryFactory {

    private BreweryFactory() {

    }

    public static Brewery aBrewery() {
        return Brewery.newBuilder()
                .withId(299)
                .withName("Almanac Beer Company")
                .withBreweryType("micro")
                .withStreet("651B W Tower Ave")
                .withAddress2("address2")
                .withAddress3("address3")
                .withCity("Alameda")
                .withState("California")
                .withCountyProvince("countyProvince")
                .withPostalCode("94501-5047")
                .withCountry("United States")
                .withLongitude("-122.306283180899")
                .withLatitude("37.7834497667258")
                .withPhone("4159326531")
                .withWebsiteUrl("http://almanacbeer.com")
                .withUpdatedAt("2018-08-23T23:24:11.758Z")
                .withCreatedAt("2018-08-23T23:24:11.758Z")
                .build();
    }

    public static BreweryDTO aBreweryDTO() {
        return BreweryDTO.newBuilder()
                .withId(299)
                .withName("Almanac Beer Company")
                .withBreweryType("micro")
                .withStreet("651B W Tower Ave")
                .withAddress2("address2")
                .withAddress3("address3")
                .withCity("Alameda")
                .withState("California")
                .withCountyProvince("countyProvince")
                .withPostalCode("94501-5047")
                .withCountry("United States")
                .withLongitude("-122.306283180899")
                .withLatitude("37.7834497667258")
                .withPhone("4159326531")
                .withWebsiteUrl("http://almanacbeer.com")
                .withUpdatedAt("2018-08-23T23:24:11.758Z")
                .withCreatedAt("2018-08-23T23:24:11.758Z")
                .build();
    }
}
