package org.example.functionalstuff.shared.list;

import org.example.functionalstuff.shared.Unit;
import org.example.functionalstuff.shared.option.Option;

import java.util.Arrays;
import java.util.function.Function;
import java.util.function.Predicate;

public sealed interface List<A> permits Cons, Empty {

    static <A> List<A> from(A[] as) {
        if (as.length == 0) return new Empty<>();
        A cur = as[0];
        A[] next = Arrays.copyOfRange(as, 1, as.length);

        return new Cons<>(cur, from(next));
    }

    Option<A> get(int index);

    int size();

    <B> List<B> fmap(Function<A, B> f);

    <B> List<B> applyTo(List<Function<A, B>> f);

    <B> List<B> bind(Function<A, List<B>> f);

    List<A> concatWith(List<A> other);

    Option<A> find(Predicate<A> predicate);

    boolean any(Predicate<A> predicate);

    List<A> filter(Predicate<A> predicate);

    <B> B fold(Function<A, Function<B, B>> f, B start);

    Unit foreach(Function<A, ?> f);
}
