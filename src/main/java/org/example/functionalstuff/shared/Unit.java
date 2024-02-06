package org.example.functionalstuff.shared;

import java.util.function.Function;

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

    public <A> A andThen(Function<Unit, A> f) {
        return f.apply(this);
    }

    @Override
    public String toString() {
        return "Unit";
    }
}
