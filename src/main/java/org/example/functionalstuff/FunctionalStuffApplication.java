package org.example.functionalstuff;

import org.example.functionalstuff.adapter.file.FilePersonStorage;
import org.example.functionalstuff.application.CreatePerson;
import org.example.functionalstuff.application.DeletePersonById;
import org.example.functionalstuff.application.FindPersonById;
import org.example.functionalstuff.application.GetAllPeople;
import org.example.functionalstuff.domain.CreatePersonRequest;
import org.example.functionalstuff.domain.Person;
import org.example.functionalstuff.domain.PersonStorage;
import org.example.functionalstuff.shared.Functions;

public class FunctionalStuffApplication {

    private static final PersonStorage storage = new FilePersonStorage();
    private static final CreatePerson createPerson = new CreatePerson(storage);
    private static final GetAllPeople getAllPeople = new GetAllPeople(storage);
    private static final DeletePersonById deletePersonById = new DeletePersonById(storage);
    private static final FindPersonById findPersonById = new FindPersonById(storage);

    public static void main(String[] args) {

        var r1 = new CreatePersonRequest("John", "Doe", 42);
        var r2 = new CreatePersonRequest("Johannes", "Schmohannes", 0);

        var id1 = createAndGetId(r1);
        var id2 = createAndGetId(r2);


        System.out.println("printPersonById");
        printPersonById(id1);
        printPersonById(id2);

        System.out.println();
        System.out.println("printFindAll");
        printFindAll();

        System.out.println();
        System.out.println("printDeleteById");
        printDeleteById(id1);

        System.out.println();
        System.out.println("printFindAll");
        printFindAll();
    }

    private static Long createAndGetId(CreatePersonRequest request) {
        return createPerson.invoke(request)
                .fold(s -> {
                    throw new IllegalStateException(s);
                }, Person::id);
    }

    private static void printPersonById(Long id) {
        String message = findPersonById.invoke(id).fold(
                () -> "Not found",
                Record::toString);

        System.out.println(message);
    }

    private static void printFindAll() {
        getAllPeople.invoke()
                .foreach(Functions::print);
    }

    private static void printDeleteById(Long id) {
        deletePersonById.invoke(id).fold(Functions::print, Functions::print);
    }
}
