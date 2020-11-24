package com.fonseca.breweryapi.controller;

import com.fonseca.breweryapi.controller.filter.BreweriesFilter;
import com.fonseca.breweryapi.dto.BreweryDTO;
import com.fonseca.breweryapi.service.BreweryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RestController
public class BreweryController {

    private final BreweryService breweryService;

    @Autowired
    public BreweryController(BreweryService breweryService) {
        this.breweryService = breweryService;
    }

    @GetMapping("/breweries/{id}")
    @PreAuthorize("#oauth2.hasScope('read')")
    public ResponseEntity<BreweryDTO> getBrewery(@PathVariable String id) {
        return ResponseEntity.ok(breweryService.getBrewery(id));
    }

    @GetMapping("/breweries/search")
    @PreAuthorize("#oauth2.hasScope('read')")
    public ResponseEntity<List<BreweryDTO>> searchBrewery(@RequestParam String query) {
        return ResponseEntity.ok(breweryService.searchBrewery(query));
    }

    @GetMapping("/breweries")
    @PreAuthorize("#oauth2.hasScope('read')")
    public ResponseEntity<List<BreweryDTO>> listBreweries(@Valid BreweriesFilter breweriesFilter) {
        return ResponseEntity.ok(breweryService.listBreweries(breweriesFilter));
    }

    @GetMapping("/hello")
    @PreAuthorize("#oauth2.hasScope('read')")
    public ResponseEntity<String> hello() {
        return ResponseEntity.ok("hello Daniel");
    }
}
