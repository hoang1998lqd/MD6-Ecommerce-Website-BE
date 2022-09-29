package com.example.md6be.service;

import java.util.List;
import java.util.Optional;

public interface IGeneralService<T>{
    List<T> findAll();

    Optional<T> findById(Long id);

    T save (T t);

    void delete(Long id);
}
