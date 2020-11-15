package com.fonseca.breweryapi.service.impl;

import com.fonseca.breweryapi.client.brewerydb.BreweryDBClient;
import com.fonseca.breweryapi.client.brewerydb.domain.BreweryListFilter;
import com.fonseca.breweryapi.controller.filter.BreweriesFilter;
import com.fonseca.breweryapi.dto.BreweryDTO;
import com.fonseca.breweryapi.mapper.Mapper;
import com.fonseca.breweryapi.service.BreweryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BreweryServiceImpl implements BreweryService {

    private final BreweryDBClient breweryDBClient;
    private final Mapper mapper;

    @Autowired
    public BreweryServiceImpl(BreweryDBClient breweryDBClient,
                              Mapper mapper) {
        this.breweryDBClient = breweryDBClient;
        this.mapper = mapper;
    }

    @Override
    public BreweryDTO getBrewery(String id) {
        return mapper.map(breweryDBClient.getBrewery(id), Mapper.BREWERY_TO_BREWERY_DTO);
    }

    @Override
    public List<BreweryDTO> listBreweries(BreweriesFilter breweriesFilter) {
        BreweryListFilter filter = mapper.map(breweriesFilter, Mapper.CONTROLLER_BREWERY_FILTER_TO_CLIENT_BREWERY_FILTER);

        return mapper.map(breweryDBClient.listBreweries(filter), Mapper.BREWERY_TO_BREWERY_DTO);
    }

    @Override
    public List<BreweryDTO> searchBrewery(String query) {
        return mapper.map(breweryDBClient.searchBrewery(query), Mapper.BREWERY_TO_BREWERY_DTO);
    }
}
