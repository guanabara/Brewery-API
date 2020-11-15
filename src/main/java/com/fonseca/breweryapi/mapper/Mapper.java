package com.fonseca.breweryapi.mapper;

import com.fonseca.breweryapi.mapper.impl.BreweryToBreweryDBO;
import com.fonseca.breweryapi.mapper.impl.ControllerBreweryFilterTOClientBreweryFilter;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class Mapper {

    public static final BreweryToBreweryDBO BREWERY_TO_BREWERY_DTO =
            new BreweryToBreweryDBO();
    public static final ControllerBreweryFilterTOClientBreweryFilter CONTROLLER_BREWERY_FILTER_TO_CLIENT_BREWERY_FILTER =
            new ControllerBreweryFilterTOClientBreweryFilter();

    public Mapper() {
    }

    public <F, T> T map(F from, MapperFunction<F, T> mapper) {
        if (from == null) {
            return null;
        }
        return mapper.map(from);
    }

    public <F, T> List<T> map(Collection<F> from, MapperFunction<F, T> mapper) {
        if (from == null) {
            return Collections.emptyList();
        }

        return from.stream()
                .map(mapper::map)
                .collect(Collectors.toList());
    }
}