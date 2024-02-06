package org.example.functionalstuff.functional.list;

import java.util.function.Function;

public final class Empty<A> implements List<A> {
    @Override
    public <B> List<B> fmap(Function<A, B> f) {
        return new Empty<>();
    }

    @Override
    public <B> List<B> applyTo(List<Function<A, B>> f) {
        return new Empty<>();
    }

    @Override
    public <B> List<B> bind(Function<A, List<B>> f) {
        return new Empty<>();
    }

    @Override
    public List<A> concatWith(List<A> other) {
        return other;
    }

    @Override
    public int hashCode() {
        return 0;
    }

    @Override
    public boolean equals(Object obj) {
        return obj instanceof Empty;
    }

    @Override
    public String toString() {
        return "Empty";
    }
}
