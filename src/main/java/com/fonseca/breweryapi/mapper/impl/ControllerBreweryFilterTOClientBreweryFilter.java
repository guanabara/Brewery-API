package com.fonseca.breweryapi.mapper.impl;

import com.fonseca.breweryapi.client.brewerydb.domain.BreweryListFilter;
import com.fonseca.breweryapi.controller.filter.BreweriesFilter;
import com.fonseca.breweryapi.mapper.MapperFunction;

public class ControllerBreweryFilterTOClientBreweryFilter implements MapperFunction<BreweriesFilter, BreweryListFilter> {

    @Override
    public BreweryListFilter map(BreweriesFilter from) {
        return BreweryListFilter.newBuilder()
                .withCity(from.getCity())
                .withName(from.getName())
                .withState(from.getState())
                .withPostal(from.getPostal())
                .withType(from.getType())
                .withPage(from.getPage())
                .withPageSize(from.getPageSize())
                .withSort(from.getSort())
                .build();
    }
}
