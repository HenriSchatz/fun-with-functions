package org.example.functionalstuff.domain;

import org.example.functionalstuff.shared.list.List;
import org.example.functionalstuff.shared.option.Option;

public interface PersonStorage {

    Person save(SavePersonRequest request);

    Option<Person> findById(Long id);

    boolean existsById(Long id);

    void deleteById(Long id);

    List<Person> findAll();
}
