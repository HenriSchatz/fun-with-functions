package org.example.functionalstuff;

import org.example.functionalstuff.adapter.file.FilePersonStorage;
import org.example.functionalstuff.application.CreatePerson;
import org.example.functionalstuff.application.GetAllPeople;
import org.example.functionalstuff.domain.CreatePersonRequest;
import org.example.functionalstuff.domain.PersonStorage;
import org.example.functionalstuff.shared.Functions;

public class FunctionalStuffApplication {

    public static void main(String[] args) {
        PersonStorage storage = new FilePersonStorage();
        CreatePerson createPerson = new CreatePerson(storage);
        GetAllPeople getAllPeople = new GetAllPeople(storage);

        var r1 = new CreatePersonRequest("John", "Doe", 42);
        var r2 = new CreatePersonRequest("Johannes", "Schmohannes", 0);

        createPerson.invoke(r1)
                .fold(Functions::print, Functions::print);
        createPerson.invoke(r2)
                .fold(Functions::print, Functions::print);

        getAllPeople.invoke().foreach(Functions::print);
    }
}
