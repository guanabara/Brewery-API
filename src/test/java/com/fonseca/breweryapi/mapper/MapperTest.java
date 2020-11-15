package com.fonseca.breweryapi.mapper;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class MapperTest {

    private final Mapper target = new Mapper();

    @Test
    void testMap() {
        // Given
        MapperFunction<Integer, Integer> mapperFunction = i -> ++i;
        int integer = 0;

        // When
        Integer actual = target.map(integer, mapperFunction);

        // Then
        assertThat(actual).isEqualTo(1);
    }

    @Test
    void testMapList() {
        // Given
        MapperFunction<Integer, Integer> mapperFunction = i -> ++i;
        List<Integer> integerList = List.of(0, 1, 2);

        // When
        List<Integer> actual = target.map(integerList, mapperFunction);

        // Then
        assertThat(actual).isEqualTo(List.of(1, 2, 3));
    }

    @Test
    void testMapNullList() {
        // Given
        MapperFunction<Integer, Integer> mapperFunction = i -> i++;
        List<Integer> integerList = null;

        // When
        List<Integer> actual = target.map(integerList, mapperFunction);

        // Then
        assertThat(actual)
                .isNotNull()
                .isEmpty();
    }
}