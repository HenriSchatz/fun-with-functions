package org.example.functionalstuff.domain;

import jakarta.annotation.Nullable;
import org.example.functionalstuff.shared.either.Either;
import org.example.functionalstuff.shared.either.Left;

import java.util.Objects;

public class Name {
    private final String asString;

    public static Either<String, Name> create(@Nullable String name) {
        if (name == null || name.isEmpty()) {
            return new Left<>("name must not be empty");
        }
        if (name.isBlank()) {
            return new Left<>("name must not be blank");
        }
        var name_ = name.trim();
        if (name_.length() < 3) {
            return new Left<>("name '%s' must be at least 3 characters long".formatted(name_));
        }

        return Either.pure(new Name(name_));
    }

    private Name(String asString) {
        this.asString = asString;
    }

    @Override
    public String toString() {
        return asString;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Name name = (Name) o;

        return Objects.equals(asString, name.asString);
    }

    @Override
    public int hashCode() {
        return asString != null ? asString.hashCode() : 0;
    }
}
