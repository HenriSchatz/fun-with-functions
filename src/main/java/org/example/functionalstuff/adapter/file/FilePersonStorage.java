package org.example.functionalstuff.adapter.file;

import org.example.functionalstuff.domain.*;
import org.example.functionalstuff.shared.list.Cons;
import org.example.functionalstuff.shared.list.Empty;
import org.example.functionalstuff.shared.list.List;
import org.example.functionalstuff.shared.option.Option;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.util.concurrent.atomic.AtomicLong;

public final class FilePersonStorage implements PersonStorage {

    private static final String path = "./personstorage.db";
    private final AtomicLong currentId;

    public FilePersonStorage() {
        this.currentId = new AtomicLong(
                findAll().fmap(Person::id)
                        .fold(id -> maxId -> id > maxId ? id : maxId, 0L) + 1L
        );
    }

    @Override
    public Person save(SavePersonRequest request) {
        var nextId = currentId.getAndIncrement();

        var personToSave = new Person(nextId, request.firstName(), request.lastName(), request.age());
        writeToFile(writePerson(personToSave) + '\n', true);

        return personToSave;
    }

    @Override
    public Option<Person> findById(Long id) {
        return findAll()
                .find(p -> p.id().equals(id));
    }

    @Override
    public boolean existsById(Long id) {
        return findAll().any(p -> p.id().equals(id));
    }

    @Override
    public void deleteById(Long id) {
        var newPeople = findAll()
                .filter(p -> !p.id().equals(id))
                .fmap(this::writePerson)
                .fold(p -> acc -> acc + p + '\n', "");

        writeToFile(newPeople, false);
    }

    @Override
    public List<Person> findAll() {
        return fileEntries()
                .fmap(this::readPerson);
    }

    private void writeToFile(String s, boolean append) {
        try (FileWriter writer = new FileWriter(path, append)) {
            writer.write(s);
            writer.flush();
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }
    }

    private List<String> fileEntries() {
        try {
            Scanner s = new Scanner(new File(path));
            return fileEntries_(s);
        } catch (FileNotFoundException e) {
            throw new IllegalStateException(e);
        }
    }

    private List<String> fileEntries_(Scanner s) {
        if (s.hasNext()) {
            return new Cons<>(s.next(), fileEntries_(s));
        } else {
            return new Empty<>();
        }
    }

    private String writePerson(Person person) {
        var id = person.id();
        var firstName = person.firstName().toString();
        var lastName = person.lastName().toString();
        var age = person.age().asInt();

        return "%d;%s;%s;%d".formatted(id, firstName, lastName, age);
    }

    private Person readPerson(String s) {
        try {
            var split = s.split(";");
            var id = Long.parseLong(split[0]);
            var firstName = split[1];
            var lastName = split[2];
            var age = Integer.parseInt(split[3]);

            return new Person(
                    id,
                    Name.create(firstName).orNull(),
                    Name.create(lastName).orNull(),
                    Age.create(age).orNull()
            );
        } catch (Exception e) {
            throw new IllegalStateException(e);
        }
    }
}
