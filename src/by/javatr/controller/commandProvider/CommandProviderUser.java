package by.javatr.controller.commandProvider;

import by.javatr.controller.command.CommandExecute;
import by.javatr.controller.command.impl.*;
import by.javatr.controller.command.impl.book.ReturnBookInLibrary;
import by.javatr.controller.command.impl.book.SearchBookByName;
import by.javatr.controller.command.impl.book.ShowBibliography;
import by.javatr.controller.command.impl.book.TakeBookForReading;

import java.util.HashMap;
import java.util.Map;

public class CommandProviderUser {
    private final Map<Integer, CommandExecute> repository = new HashMap<>();

    public CommandProviderUser() {
        repository.put(1, new TakeBookForReading());
        repository.put(2, new ReturnBookInLibrary());
        repository.put(3, new SearchBookByName());
        repository.put(4, new ShowBibliography());
        repository.put(0, new WrongCommand());
    }

    public CommandExecute getCommand(int numberCommand) {
        CommandExecute command;
        try {
            command = repository.get(numberCommand);
        } catch (IllegalArgumentException | NullPointerException e) {
            command = repository.get(0);
        }
        return command;
    }
}
