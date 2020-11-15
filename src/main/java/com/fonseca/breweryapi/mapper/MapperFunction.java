package com.fonseca.breweryapi.mapper;

@FunctionalInterface
public interface MapperFunction<F, T> {
    T map(F from);
}
