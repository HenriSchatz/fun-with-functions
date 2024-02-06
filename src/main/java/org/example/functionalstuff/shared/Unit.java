package org.example.functionalstuff.shared;

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

    @Override
    public String toString() {
        return "Unit";
    }
}
