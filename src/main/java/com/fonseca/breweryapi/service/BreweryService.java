package com.fonseca.breweryapi.service;

import com.fonseca.breweryapi.controller.filter.BreweriesFilter;
import com.fonseca.breweryapi.dto.BreweryDTO;

import java.util.List;

public interface BreweryService {

    BreweryDTO getBrewery(String id);

    List<BreweryDTO> listBreweries(BreweriesFilter breweriesFilter);

    List<BreweryDTO> searchBrewery(String id);
}
