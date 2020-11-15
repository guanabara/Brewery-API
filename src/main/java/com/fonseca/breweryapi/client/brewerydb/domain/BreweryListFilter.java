package com.fonseca.breweryapi.client.brewerydb.domain;

import org.apache.commons.codec.Charsets;
import org.springframework.util.CollectionUtils;

import java.net.URLEncoder;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

public class BreweryListFilter {
    private final String city;
    private final String name;
    private final String state;
    private final String postal;
    private final String type;
    private final Integer page;
    private final Integer pageSize;
    private final List<String> sort;

    public BreweryListFilter(String city,
                             String name,
                             String state,
                             String postal,
                             String type,
                             Integer page,
                             Integer pageSize,
                             List<String> sort) {
        this.city = city;
        this.name = name;
        this.state = state;
        this.postal = postal;
        this.type = type;
        this.page = page;
        this.pageSize = pageSize;
        this.sort = sort;
    }

    private BreweryListFilter(Builder builder) {
        city = builder.city;
        name = builder.name;
        state = builder.state;
        postal = builder.postal;
        type = builder.type;
        page = builder.page;
        pageSize = builder.pageSize;
        sort = builder.sort;
    }

    public static Builder newBuilder() {
        return new Builder();
    }

    public String buildQueryParams() {
        return List.of(
                buildQueryParameter("by_city", city),
                buildQueryParameter("by_name", name),
                buildQueryParameter("by_state", state),
                buildQueryParameter("by_postal", postal),
                buildQueryParameter("by_type", type),
                buildQueryParameter("page", page),
                buildQueryParameter("per_page", pageSize),
                buildQueryParameter("sort", sort)
        )
                .stream()
                .filter(Optional::isPresent)
                .map(Optional::get)
                .collect(Collectors.joining("&"));
    }

    private <T> Optional<String> buildQueryParameter(String key, List<T> value) {
        if (CollectionUtils.isEmpty(value)) {
            return Optional.empty();
        }
        String valueEncoded = value.stream()
                .map(field -> URLEncoder.encode(field.toString(), Charsets.UTF_8))
                .collect(Collectors.joining(","));

        return Optional.of(key + "=" + valueEncoded);
    }

    private <T> Optional<String> buildQueryParameter(String key, T value) {
        if (Objects.isNull(value)) {
            return Optional.empty();
        }
        return Optional.of(key + "=" + URLEncoder.encode(value.toString(), Charsets.UTF_8));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BreweryListFilter that = (BreweryListFilter) o;
        return Objects.equals(city, that.city) &&
                Objects.equals(name, that.name) &&
                Objects.equals(state, that.state) &&
                Objects.equals(postal, that.postal) &&
                Objects.equals(type, that.type) &&
                Objects.equals(page, that.page) &&
                Objects.equals(pageSize, that.pageSize) &&
                Objects.equals(sort, that.sort);
    }

    @Override
    public int hashCode() {
        return Objects.hash(city, name, state, postal, type, page, pageSize, sort);
    }

    public static final class Builder {
        private Integer page;
        private Integer pageSize;
        private List<String> sort;
        private String city;
        private String name;
        private String state;
        private String postal;
        private String type;

        private Builder() {
        }

        public Builder withPage(Integer page) {
            this.page = page;
            return this;
        }

        public Builder withPageSize(Integer pageSize) {
            this.pageSize = pageSize;
            return this;
        }

        public Builder withSort(List<String> sort) {
            this.sort = sort;
            return this;
        }

        public BreweryListFilter build() {
            return new BreweryListFilter(this);
        }

        public Builder withCity(String city) {
            this.city = city;
            return this;
        }

        public Builder withName(String name) {
            this.name = name;
            return this;
        }

        public Builder withState(String state) {
            this.state = state;
            return this;
        }

        public Builder withPostal(String postal) {
            this.postal = postal;
            return this;
        }

        public Builder withType(String type) {
            this.type = type;
            return this;
        }
    }
}
