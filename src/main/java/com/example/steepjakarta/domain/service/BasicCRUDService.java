package com.example.steepjakarta.domain.service;

public interface BasicCRUDService<T, R> {

    default void delete(Long id) {}

    default String create(T obj) {
        return null;
    }

    default void update(Long id, T obj) {}

    default R get(Long id) { return null; }

    default Iterable<R> getAll() { return null; }
}
