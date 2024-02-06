package org.example.functionalstuff.functional.either;

import lombok.RequiredArgsConstructor;

import java.util.Objects;
import java.util.function.Function;

@RequiredArgsConstructor
public final class Left<Error, A> implements Either<Error, A> {

    private final Error error;

    @Override
    public <B> Either<Error, B> fmap(Function<A, B> f) {
        return new Left<>(error);
    }

    @Override
    public <B> Either<Error, B> applyTo(Either<Error, Function<A, B>> f) {
        return new Left<>(error);
    }

    @Override
    public <B> Either<Error, B> bind(Function<A, Either<Error, B>> f) {
        return new Left<>(error);
    }

    public Error get() {
        return error;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Left<?, ?> left = (Left<?, ?>) o;

        return Objects.equals(error, left.error);
    }

    @Override
    public int hashCode() {
        return error != null ? error.hashCode() : 0;
    }

    @Override
    public String toString() {
        return "Left[%s]".formatted(error);
    }
}