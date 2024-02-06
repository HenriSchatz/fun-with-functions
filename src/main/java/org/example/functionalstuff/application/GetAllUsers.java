package org.example.functionalstuff.application;

import lombok.RequiredArgsConstructor;
import org.example.functionalstuff.domain.Person;
import org.example.functionalstuff.domain.PersonStorage;
import org.example.functionalstuff.shared.list.List;

@RequiredArgsConstructor
public class GetAllUsers {

    private final PersonStorage storage;

    public List<Person> invoke() {
        return storage.findAll();
    }
}
