package org.example.functionalstuff.adapter.console;

import lombok.RequiredArgsConstructor;
import org.example.functionalstuff.application.CreatePerson;
import org.example.functionalstuff.application.DeletePersonById;
import org.example.functionalstuff.application.FindPersonById;
import org.example.functionalstuff.application.GetAllPeople;
import org.example.functionalstuff.domain.CreatePersonRequest;
import org.example.functionalstuff.shared.Functions;

import java.util.Scanner;

import static org.example.functionalstuff.shared.Functions.print;
import static org.example.functionalstuff.shared.Functions.runCatching;

@RequiredArgsConstructor
public class Console implements Runnable {

    private static final Scanner in = new Scanner(System.in);

    private final CreatePerson createPerson;
    private final GetAllPeople getAllPeople;
    private final DeletePersonById deletePersonById;
    private final FindPersonById findPersonById;

    @Override
    public void run() {
        boolean running = true;
        print("Welcome to person cli stuff");

        while (running) {
            var command = promptCommand();
            switch (command) {
                case DISPLAY_ALL -> displayAll();
                case DISPLAY_SINGLE -> displaySingle();
                case CREATE -> createPerson();
                case DELETE_SINGLE -> deleteSingle();
                case EXIT -> running = false;
            }
        }

        print("Bye!");
    }

    private Command promptCommand() {
        print("Enter command: ");
        print(Command.formatted());

        return Command.fromString(in.nextLine())
                .fold(this::promptCommand, Functions::id);
    }

    private void displayAll() {
        var formatted = getAllPeople.invoke()
                .fmap(p -> "%d %s %s".formatted(p.id(), p.firstName(), p.lastName()))
                .fold(s -> acc -> acc + s + '\n', "");

        print(formatted);
    }

    private void displaySingle() {
        var id = promptId();

        var msg = findPersonById.invoke(id)
                .fold(() -> "Person with id %d does not exist".formatted(id),
                        p -> "%d %s %s, %d years old".formatted(p.id(), p.firstName(), p.lastName(), p.age().asInt()));

        print(msg);
    }

    private long promptId() {
        return print("Enter id:").andThen(() -> runCatching(() -> Long.parseLong(in.nextLine()))
                .onLeft(err -> print("Invalid number input").andThen(this::promptId)));
    }

    private void createPerson() {
        var firstName = print("Enter first name: ").andThen(in::nextLine);
        var lastName = print("Enter last name: ").andThen(in::nextLine);
        var age = getAge();

        var request = new CreatePersonRequest(firstName, lastName, age);
        createPerson.invoke(request)
                .fold(Functions::print, Functions::print);
    }

    private void deleteSingle() {
        var id = promptId();

        var msg = deletePersonById.invoke(id)
                .fold(Functions::id, u -> "Deleted");

        print(msg);
    }

    private int getAge() {
        print("Enter age: ");
        return runCatching(() -> Integer.parseInt(in.nextLine()))
                .onLeft(err -> {
                    print("Invalid number");
                    return getAge();
                });
    }
}
