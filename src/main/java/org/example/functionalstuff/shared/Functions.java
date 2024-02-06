package org.example.functionalstuff.shared;

import org.example.functionalstuff.shared.list.Cons;
import org.example.functionalstuff.shared.list.Empty;
import org.example.functionalstuff.shared.list.List;

import java.util.function.BiFunction;
import java.util.function.Function;

public final class Functions {

    private Functions() {
        // no instance pls
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
}
