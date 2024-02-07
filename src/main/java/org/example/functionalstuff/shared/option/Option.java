package org.example.functionalstuff.shared.option;

import jakarta.annotation.Nullable;

import java.util.function.Function;
import java.util.function.Supplier;

public sealed interface Option<A> permits Just, Nothing {
    static <A> Option<A> pure(A value) {
        return new Just<>(value);
    }

    <B> Option<B> fmap(Function<A, B> f);

    <B> Option<B> applyTo(Option<Function<A, B>> f);

    <B> Option<B> bind(Function<A, Option<B>> f);

    <B> B fold(Supplier<B> onNothing, Function<A, B> onJust);

    @Nullable
    A orNull();
}