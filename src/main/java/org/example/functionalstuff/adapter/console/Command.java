package org.example.functionalstuff.adapter.console;

import org.example.functionalstuff.shared.list.List;
import org.example.functionalstuff.shared.option.Option;

public enum Command {
    DISPLAY_ALL("all", "display all people"),
    DISPLAY_SINGLE("single", "display single person by id"),
    CREATE("create", "create a new person"),
    DELETE_SINGLE("delete", "delete single person by id"),
    EXIT("exit", "exit application");
    private final String asString;
    private final String description;

    public static Option<Command> fromString(String s) {
        return List.of(values())
                .find(it -> it.asString.equalsIgnoreCase(s));
    }

    public static String formatted() {
        return List.of(values())
                .fmap(Command::formatted_)
                .fold(s -> acc -> acc + s + '\n', "");
    }

    Command(String asString, String description) {
        this.asString = asString;
        this.description = description;
    }

    private String formatted_() {
        return "%s (%s)".formatted(asString, description);
    }
}
