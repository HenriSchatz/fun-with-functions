package org.example.functionalstuff.application;

import lombok.RequiredArgsConstructor;
import org.example.functionalstuff.domain.*;
import org.example.functionalstuff.shared.Pair;
import org.example.functionalstuff.shared.either.Either;

@RequiredArgsConstructor
public final class CreatePerson {

    private final PersonStorage storage;

    public Either<String, Person> invoke(CreatePersonRequest request) {
        return savePersonRequest(request)
                .fmap(storage::save);
    }

    private Either<String, SavePersonRequest> savePersonRequest(CreatePersonRequest request) {
        return firstToLastName(request)
                .bind(pair ->
                        Age.create(request.age())
                                .fmap(age -> new SavePersonRequest(pair.a(), pair.b(), age))
                );
    }

    private Either<String, Pair<Name, Name>> firstToLastName(CreatePersonRequest request) {
        return Name.create(request.firstName())
                .bind(fname ->
                        Name.create(request.lastName()).fmap(lname -> new Pair<>(fname, lname))
                );
    }

}
