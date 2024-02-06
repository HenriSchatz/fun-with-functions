package org.example.functionalstuff.domain;

import org.example.functionalstuff.functional.either.Either;
import org.example.functionalstuff.functional.either.Left;

public class Age {
    private static final Range ageRange = new Range(0, 150);
    private final int asInt;

    public static Either<String, Age> create(int age) {
        if (!ageRange.contains(age)) {
            return new Left<>("Age %d not in range %s".formatted(age, ageRange));
        }

        return Either.pure(new Age(age));
    }

    private Age(int asInt) {
        this.asInt = asInt;
    }

    @Override
    public String toString() {
        return "Age[%d]".formatted(asInt);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Age age = (Age) o;

        return asInt == age.asInt;
    }

    @Override
    public int hashCode() {
        return asInt;
    }
}
