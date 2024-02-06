package org.example.functionalstuff.functional.list;

import java.util.function.Function;

public sealed interface List<A> permits Cons, Empty {

    <B> List<B> fmap(Function<A, B> f);

    <B> List<B> applyTo(List<Function<A, B>> f);

    <B> List<B> bind(Function<A, List<B>> f);

    List<A> concatWith(List<A> other);
}
