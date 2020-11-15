package com.fonseca.breweryapi.controller.filter;

import javax.validation.Constraint;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import javax.validation.Payload;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.List;
import java.util.Objects;
import java.util.regex.Pattern;

public class BreweriesFilter {

    private static final String VALID_TYPES =
            "(?:contract|bar|proprietor|brewpub|large|planning|regional|micro|nano|closed)";

    private String city;
    private String name;
    private String state;
    @javax.validation.constraints.Pattern(regexp = "\\d{5}(?:[-_]\\d{4})?")
    private String postal;
    @javax.validation.constraints.Pattern(regexp = VALID_TYPES)
    private String type;
    @Min(0)
    private Integer page;
    @Min(1)
    @Max(50)
    private Integer pageSize;
    @ValidateSort
    private List<String> sort;

    public BreweriesFilter() {
    }

    private BreweriesFilter(Builder builder) {
        setCity(builder.city);
        setName(builder.name);
        setState(builder.state);
        setPostal(builder.postal);
        setType(builder.type);
        setPage(builder.page);
        setPageSize(builder.pageSize);
        setSort(builder.sort);
    }

    public static Builder newBuilder() {
        return new Builder();
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getPostal() {
        return postal;
    }

    public void setPostal(String postal) {
        this.postal = postal;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public List<String> getSort() {
        return sort;
    }

    public void setSort(List<String> sort) {
        this.sort = sort;
    }

    @Constraint(validatedBy = SortValidator.class)
    @Retention(RetentionPolicy.RUNTIME)
    public @interface ValidateSort {
        String message() default "A field in the list doesn't match the expression [+-](?:city|name|state|postal|type)";

        Class<?>[] groups() default {};

        Class<? extends Payload>[] payload() default {};
    }

    public static class SortValidator implements ConstraintValidator<ValidateSort, List<String>> {

        public static final Pattern SORT_VALIDATION_PATTERN =
                Pattern.compile("[+-](?:city|name|state|postal|type)");

        @Override
        public boolean isValid(List<String> sort, ConstraintValidatorContext constraintValidatorContext) {
            if (Objects.isNull(sort)) {
                return true;
            }
            return sort.stream()
                    .allMatch(sortField -> SORT_VALIDATION_PATTERN.matcher(sortField).matches());
        }
    }

    public static final class Builder {
        private String city;
        private String name;
        private String state;
        private String postal;
        private String type;
        private Integer page;
        private Integer pageSize;
        private List<String> sort;

        private Builder() {
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

        public BreweriesFilter build() {
            return new BreweriesFilter(this);
        }
    }
}
