package org.example.functionalstuff.shared.option;

import java.util.function.Function;

public sealed interface Option<A> permits Just, Nothing {
    static <A> Option<A> pure(A value) {
        return new Just<>(value);
    }

    <B> Option<B> fmap(Function<A, B> f);

    <B> Option<B> applyTo(Option<Function<A, B>> f);

    <B> Option<B> bind(Function<A, Option<B>> f);
}