package com.example.steepjakarta.domain.service;

public interface BasicCRUDService<T, R> {

    default void delete(String displayID) {}

    default String create(T obj) {
        return null;
    }

    default void update(String displayID, T obj) {}

    default R get(String displayID) { return null; }

    default Iterable<R> getAll() { return null; }
}
