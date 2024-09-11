package com.example.steepjakarta.domain.service;

public interface BasicCRUDService<T> {

    default void delete(Long id) {}

    default String create(T obj) {
        return null;
    }

    default void update(Long id,T obj) {}

    default T get(Long id) { return null; }

    default Iterable<T> getAll() { return null; }
}
