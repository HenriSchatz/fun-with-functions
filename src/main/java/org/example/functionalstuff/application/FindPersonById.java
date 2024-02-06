package org.example.functionalstuff.application;

import lombok.RequiredArgsConstructor;
import org.example.functionalstuff.domain.Person;
import org.example.functionalstuff.domain.PersonStorage;
import org.example.functionalstuff.shared.option.Option;

@RequiredArgsConstructor
public class FindPersonById {

    private final PersonStorage storage;

    public Option<Person> invoke(Long id) {
        return storage.findById(id);
    }
}
