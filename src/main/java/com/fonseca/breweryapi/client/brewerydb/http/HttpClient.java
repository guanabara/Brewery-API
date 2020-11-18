package com.fonseca.breweryapi.client.brewerydb.http;

import com.fonseca.breweryapi.client.brewerydb.exception.BreweryNotFoundException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Component
public class HttpClient {

    @Value("${brewerydb.client.protocol}")
    private String protocol;

    @Value("${brewerydb.client.host}")
    private String host;

    private final RestTemplate restTemplate;

    public HttpClient() {
        this.restTemplate = new RestTemplate();
    }

    public <T> T request(RequestEntity<?> requestEntity, Class<T> responseType) {
        try {
            return restTemplate.exchange(requestEntity, responseType).getBody();
        } catch (HttpClientErrorException e) {
            if (HttpStatus.NOT_FOUND == e.getStatusCode()) {
                throw new BreweryNotFoundException(e);
            } else {
                throw e;
            }
        }
    }

    public <T> List<T> request(RequestEntity<?> requestEntity, ParameterizedTypeReference<List<T>> responseType) {
        try {
            return restTemplate.exchange(requestEntity, responseType).getBody();
        } catch (HttpClientErrorException e) {
            if (HttpStatus.NOT_FOUND == e.getStatusCode()) {
                throw new BreweryNotFoundException(e);
            } else {
                throw e;
            }
        }
    }

    public String getTargetUrl(String path) {
        return protocol.concat("://")
                .concat(host)
                .concat(path);
    }
}
