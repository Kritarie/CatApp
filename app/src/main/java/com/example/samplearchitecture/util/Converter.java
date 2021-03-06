package com.example.samplearchitecture.util;

/**
 * Converts one kind of thing to another kind of thing
 */
public interface Converter<T, R> {
    R convert(T entity);
}
