package org.example.functionalstuff.shared;

import java.util.function.Supplier;

public class Unit {

    private static Unit UNIT = null;

    public static Unit get() {
        if (UNIT == null) {
            UNIT = new Unit();
        }

        return UNIT;
    }

    private Unit() {
    }

    public <A> A andThen(Supplier<A> f) {
        return f.get();
    }

    @Override
    public String toString() {
        return "Unit";
    }
}
