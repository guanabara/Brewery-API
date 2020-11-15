package com.fonseca.breweryapi.factory;

import com.fonseca.breweryapi.client.brewerydb.domain.BreweryListFilter;
import com.fonseca.breweryapi.controller.filter.BreweriesFilter;

import java.util.List;

public final class BreweryFiltersFactory {

    private BreweryFiltersFactory() {

    }

    public static BreweryListFilter aBreweryListFilter() {
        return BreweryListFilter.newBuilder()
                .withCity("porto")
                .withPage(2)
                .withName("Brewery 2")
                .withPageSize(5)
                .withPostal("12345")
                .withSort(List.of("+2"))
                .withState("Porto")
                .withType("bar")
                .build();
    }

    public static BreweriesFilter aBreweriesFilter() {
        return BreweriesFilter.newBuilder()
                .withCity("porto")
                .withPage(2)
                .withName("Brewery 2")
                .withPageSize(5)
                .withPostal("12345")
                .withSort(List.of("+2"))
                .withState("Porto")
                .withType("bar")
                .build();
    }
}
