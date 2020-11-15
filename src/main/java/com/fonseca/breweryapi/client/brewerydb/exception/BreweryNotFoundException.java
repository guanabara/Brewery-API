package com.fonseca.breweryapi.client.brewerydb.exception;

public class BreweryNotFoundException extends RuntimeException {

    public BreweryNotFoundException(Exception e) {
        super(e);
    }
}
