package com.example.demo.domain.interfaces;

import java.util.Optional;

public interface IGetRepository<T> {
    Optional<T> get(long id);
}
