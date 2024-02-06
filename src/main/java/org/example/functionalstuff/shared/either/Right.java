package org.example.functionalstuff.shared.either;

import lombok.RequiredArgsConstructor;

import java.util.Objects;
import java.util.function.Function;

@RequiredArgsConstructor
public final class Right<Error, A> implements Either<Error, A> {

    private final A value;

    @Override
    public <B> Either<Error, B> fmap(Function<A, B> f) {
        var result = f.apply(value);
        return new Right<>(result);
    }

    @Override
    public <B> Either<Error, B> applyTo(Either<Error, Function<A, B>> f) {
        return switch (f) {
            case Left<Error, Function<A, B>> l -> new Left<>(l.get());
            case Right<Error, Function<A, B>> f_ -> new Right<>(f_.get().apply(value));
        };
    }

    @Override
    public <B> Either<Error, B> bind(Function<A, Either<Error, B>> f) {
        return f.apply(value);
    }

    public A get() {
        return value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Right<?, ?> right = (Right<?, ?>) o;

        return Objects.equals(value, right.value);
    }

    @Override
    public int hashCode() {
        return value != null ? value.hashCode() : 0;
    }

    @Override
    public String toString() {
        return "Right[%s]".formatted(value);
    }
}
