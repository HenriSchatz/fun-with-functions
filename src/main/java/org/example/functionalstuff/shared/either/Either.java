package org.example.functionalstuff.shared.either;

import org.example.functionalstuff.shared.option.Option;

import java.util.function.Function;

public sealed interface Either<Error, A> permits Left, Right {
    static <Error, A> Either<Error, A> pure(A value) {
        return new Right<>(value);
    }

    <B> Either<Error, B> fmap(Function<A, B> f);

    <B> Either<Error, B> applyTo(Either<Error, Function<A, B>> f);

    <B> Either<Error, B> bind(Function<A, Either<Error, B>> f);
    Option<A> toOption();

    <B> B fold(Function<Error, B> onLeft, Function<A, B> onRight);

    default A orNull() {
        return toOption().orNull();
    }
}
