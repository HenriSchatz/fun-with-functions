package org.example.functionalstuff.shared;

import org.example.functionalstuff.shared.either.Either;
import org.example.functionalstuff.shared.either.Left;
import org.example.functionalstuff.shared.either.Right;
import org.example.functionalstuff.shared.list.Cons;
import org.example.functionalstuff.shared.list.Empty;
import org.example.functionalstuff.shared.list.List;

import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.Supplier;

public final class Functions {

    private Functions() {
        // no instance pls
    }

    public static <A, B, C> Function<A, C> compose(Function<B, C> g, Function<A, B> f) {
        return a -> g.apply(f.apply(a));
    }

    public static <A> A id(A a) {
        return a;
    }

    public static <A> Either<String, A> runCatching(Supplier<A> s) {
        try {
            return new Right<>(s.get());
        } catch (Exception e) {
            var msg = e.getMessage() == null ? "Error while running" : e.getMessage();
            return new Left<>(msg);
        }
    }

    public static String stringify(List<?> list) {
        var content = stringify_(list);

        return "[" + content.substring(0, content.length() - 2) + "]";
    }

    private static String stringify_(List<?> list) {
        return switch (list) {
            case Empty<?> ignored -> "";
            case Cons<?> l -> l.head() + ", " + stringify_(l.tail());
        };
    }

    public static <A, B, C> Function<A, Function<B, C>> curry(BiFunction<A, B, C> f) {
        return (a) -> (b) -> f.apply(a, b);
    }

    public static <A, B, C> Function<B, Function<A, C>> flip(Function<A, Function<B, C>> f) {
        return (b) -> (a) -> f.apply(a).apply(b);
    }

    public static Unit print(Object msg) {
        System.out.println(msg);
        return Unit.get();
    }
}
