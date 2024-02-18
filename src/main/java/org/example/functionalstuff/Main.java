package org.example.functionalstuff;

import org.example.functionalstuff.shared.Pair;

import java.util.function.Function;

import static org.example.functionalstuff.shared.Functions.compose;

public class Main {

    public static void main(String[] args) {
        Function<Integer, Integer> times2 = i -> i * 2;
        Function<Integer, Integer> pow = i -> i * i;
        Function<Integer, Integer> inc = i -> i + 1;
        Function<Integer, Integer> times2Plus1 = compose(inc, times2);

        Function<Function<Integer, Integer>, Function<Integer, SomeRecord>> foo = f -> i ->
                SomeRecord.constructor()
                        .apply(f.apply(i))
                        .apply("2")
                        .apply(new Pair<>(3, "4"));

        var bar = foo.apply(times2Plus1).apply(42);
        System.out.println(bar);
    }
}
