package com.fonseca.breweryapi.dto;

import java.util.Objects;

public class BreweryDTO {
    private final int id;
    private final String name;
    private final String breweryType;
    private final String street;
    private final String address2;
    private final String address3;
    private final String city;
    private final String countyProvince;
    private final String state;
    private final String postalCode;
    private final String country;
    private final String longitude;
    private final String latitude;
    private final String phone;
    private final String websiteUrl;
    private final String updatedAt;
    private final String createdAt;

    public BreweryDTO(int id,
                      String name,
                      String breweryType,
                      String street,
                      String address2,
                      String address3,
                      String city,
                      String countyProvince,
                      String state,
                      String postalCode,
                      String country,
                      String longitude,
                      String latitude,
                      String phone,
                      String websiteUrl,
                      String updatedAt,
                      String createdAt) {
        this.id = id;
        this.name = name;
        this.breweryType = breweryType;
        this.street = street;
        this.address2 = address2;
        this.address3 = address3;
        this.city = city;
        this.countyProvince = countyProvince;
        this.state = state;
        this.postalCode = postalCode;
        this.country = country;
        this.longitude = longitude;
        this.latitude = latitude;
        this.phone = phone;
        this.websiteUrl = websiteUrl;
        this.updatedAt = updatedAt;
        this.createdAt = createdAt;
    }

    private BreweryDTO(Builder builder) {
        id = builder.id;
        name = builder.name;
        breweryType = builder.breweryType;
        street = builder.street;
        address2 = builder.address2;
        address3 = builder.address3;
        city = builder.city;
        countyProvince = builder.countyProvince;
        state = builder.state;
        postalCode = builder.postalCode;
        country = builder.country;
        longitude = builder.longitude;
        latitude = builder.latitude;
        phone = builder.phone;
        websiteUrl = builder.websiteUrl;
        updatedAt = builder.updatedAt;
        createdAt = builder.createdAt;
    }

    public static Builder newBuilder() {
        return new Builder();
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getBreweryType() {
        return breweryType;
    }

    public String getStreet() {
        return street;
    }

    public String getAddress2() {
        return address2;
    }

    public String getAddress3() {
        return address3;
    }

    public String getCity() {
        return city;
    }

    public String getCountyProvince() {
        return countyProvince;
    }

    public String getState() {
        return state;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public String getCountry() {
        return country;
    }

    public String getLongitude() {
        return longitude;
    }

    public String getLatitude() {
        return latitude;
    }

    public String getPhone() {
        return phone;
    }

    public String getWebsiteUrl() {
        return websiteUrl;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BreweryDTO that = (BreweryDTO) o;
        return id == that.id &&
                Objects.equals(name, that.name) &&
                Objects.equals(breweryType, that.breweryType) &&
                Objects.equals(street, that.street) &&
                Objects.equals(address2, that.address2) &&
                Objects.equals(address3, that.address3) &&
                Objects.equals(city, that.city) &&
                Objects.equals(countyProvince, that.countyProvince) &&
                Objects.equals(state, that.state) &&
                Objects.equals(postalCode, that.postalCode) &&
                Objects.equals(country, that.country) &&
                Objects.equals(longitude, that.longitude) &&
                Objects.equals(latitude, that.latitude) &&
                Objects.equals(phone, that.phone) &&
                Objects.equals(websiteUrl, that.websiteUrl) &&
                Objects.equals(updatedAt, that.updatedAt) &&
                Objects.equals(createdAt, that.createdAt);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, breweryType, street, address2, address3, city, countyProvince, state, postalCode, country, longitude, latitude, phone, websiteUrl, updatedAt, createdAt);
    }

    public static final class Builder {
        private int id;
        private String name;
        private String breweryType;
        private String street;
        private String address2;
        private String address3;
        private String city;
        private String countyProvince;
        private String state;
        private String postalCode;
        private String country;
        private String longitude;
        private String latitude;
        private String phone;
        private String websiteUrl;
        private String updatedAt;
        private String createdAt;

        private Builder() {
        }

        public Builder withId(int id) {
            this.id = id;
            return this;
        }

        public Builder withName(String name) {
            this.name = name;
            return this;
        }

        public Builder withBreweryType(String breweryType) {
            this.breweryType = breweryType;
            return this;
        }

        public Builder withStreet(String street) {
            this.street = street;
            return this;
        }

        public Builder withAddress2(String address2) {
            this.address2 = address2;
            return this;
        }

        public Builder withAddress3(String address3) {
            this.address3 = address3;
            return this;
        }

        public Builder withCity(String city) {
            this.city = city;
            return this;
        }

        public Builder withCountyProvince(String countyProvince) {
            this.countyProvince = countyProvince;
            return this;
        }

        public Builder withState(String state) {
            this.state = state;
            return this;
        }

        public Builder withPostalCode(String postalCode) {
            this.postalCode = postalCode;
            return this;
        }

        public Builder withCountry(String country) {
            this.country = country;
            return this;
        }

        public Builder withLongitude(String longitude) {
            this.longitude = longitude;
            return this;
        }

        public Builder withLatitude(String latitude) {
            this.latitude = latitude;
            return this;
        }

        public Builder withPhone(String phone) {
            this.phone = phone;
            return this;
        }

        public Builder withWebsiteUrl(String websiteUrl) {
            this.websiteUrl = websiteUrl;
            return this;
        }

        public Builder withUpdatedAt(String updatedAt) {
            this.updatedAt = updatedAt;
            return this;
        }

        public Builder withCreatedAt(String createdAt) {
            this.createdAt = createdAt;
            return this;
        }

        public BreweryDTO build() {
            return new BreweryDTO(this);
        }
    }
}
