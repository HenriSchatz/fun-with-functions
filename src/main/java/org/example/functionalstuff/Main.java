package org.example.functionalstuff;

import org.example.functionalstuff.adapter.console.Console;
import org.example.functionalstuff.adapter.file.FilePersonStorage;
import org.example.functionalstuff.application.CreatePerson;
import org.example.functionalstuff.application.DeletePersonById;
import org.example.functionalstuff.application.FindPersonById;
import org.example.functionalstuff.application.GetAllPeople;
import org.example.functionalstuff.domain.PersonStorage;

public class Main {

    public static void main(String[] args) {
        PersonStorage storage = new FilePersonStorage();
        GetAllPeople getAllPeople = new GetAllPeople(storage);
        FindPersonById findPersonById = new FindPersonById(storage);
        CreatePerson createPerson = new CreatePerson(storage);
        DeletePersonById deletePersonById = new DeletePersonById(storage);

        new Console(
                createPerson,
                getAllPeople,
                deletePersonById,
                findPersonById
        ).run();
    }
}
