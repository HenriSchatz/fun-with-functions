package org.example.functionalstuff;

import lombok.Value;
import org.example.functionalstuff.shared.Pair;

import java.util.function.Function;

@Value
public class SomeRecord {
    int anInt;
    String aString;
    Pair<Integer, String> aTuple;

    public static Function<Integer, Function<String, Function<Pair<Integer, String>, SomeRecord>>> constructor() {
        return i -> s -> p -> new SomeRecord(i, s, p);
    }

    private SomeRecord(int anInt, String aString, Pair<Integer, String> aTuple) {
        this.anInt = anInt;
        this.aString = aString;
        this.aTuple = aTuple;
    }
}
