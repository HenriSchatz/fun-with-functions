package org.example.functionalstuff.shared.list;

import org.example.functionalstuff.shared.option.Option;

import java.util.function.Function;

public sealed interface List<A> permits Cons, Empty {

    Option<A> get(int index);

    int size();

    <B> List<B> fmap(Function<A, B> f);

    <B> List<B> applyTo(List<Function<A, B>> f);

    <B> List<B> bind(Function<A, List<B>> f);

    List<A> concatWith(List<A> other);
}
