package org.example.functionalstuff.application;

import lombok.RequiredArgsConstructor;
import org.example.functionalstuff.domain.PersonStorage;
import org.example.functionalstuff.shared.Unit;
import org.example.functionalstuff.shared.either.Either;
import org.example.functionalstuff.shared.either.Left;
import org.example.functionalstuff.shared.either.Right;

@RequiredArgsConstructor
public class DeletePersonById {
    private final PersonStorage storage;

    public Either<String, Unit> invoke(Long id) {
        if (!storage.existsById(id)) {
            return new Left<>("Person with id %d not found".formatted(id));
        }

        storage.deleteById(id);
        return new Right<>(Unit.get());
    }
}
