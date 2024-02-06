package org.example.functionalstuff.shared.option;

import java.util.function.Function;

public final class Nothing<A> implements Option<A> {
    @Override
    public <B> Option<B> fmap(Function<A, B> f) {
        return new Nothing<>();
    }

    @Override
    public <B> Option<B> applyTo(Option<Function<A, B>> f) {
        return new Nothing<>();
    }

    @Override
    public <B> Option<B> bind(Function<A, Option<B>> f) {
        return new Nothing<>();
    }

    @Override
    public String toString() {
        return "Nothing";
    }

    @Override
    public int hashCode() {
        return 0;
    }

    @Override
    public boolean equals(Object obj) {
        return obj instanceof Nothing;
    }
}
