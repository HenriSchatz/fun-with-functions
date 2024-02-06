package org.example.functionalstuff.shared.option;

import jakarta.annotation.Nullable;
import lombok.RequiredArgsConstructor;

import java.util.Objects;
import java.util.function.Function;

@RequiredArgsConstructor
public final class Just<A> implements Option<A> {

    private final A value;

    @Override
    public <B> Option<B> fmap(Function<A, B> f) {
        B result = f.apply(value);
        return new Just<>(result);
    }

    @Override
    public <B> Option<B> applyTo(Option<Function<A, B>> f) {
        return switch (f) {
            case Nothing<Function<A, B>> ignored -> new Nothing<>();
            case Just<Function<A, B>> f_ -> new Just<>(f_.get().apply(value));
        };
    }

    @Override
    public <B> Option<B> bind(Function<A, Option<B>> f) {
        return f.apply(value);
    }

    @Nullable
    @Override
    public A orNull() {
        return value;
    }

    public A get() {
        return value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Just<?> just = (Just<?>) o;

        return Objects.equals(value, just.value);
    }

    @Override
    public int hashCode() {
        return value != null ? value.hashCode() : 0;
    }

    @Override
    public String toString() {
        return "Just[%s]".formatted(value);
    }
}
