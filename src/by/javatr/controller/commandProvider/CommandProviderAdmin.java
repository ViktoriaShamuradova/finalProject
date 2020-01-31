package by.javatr.controller.commandProvider;

import by.javatr.controller.command.CommandExecute;
import by.javatr.controller.command.impl.*;
import by.javatr.controller.command.impl.book.*;
import by.javatr.controller.command.impl.user.AllAdmins;
import by.javatr.controller.command.impl.user.AllClients;
import by.javatr.controller.command.impl.user.DeleteAdmin;
import by.javatr.controller.command.impl.user.RegistrationAdmin;

import java.util.HashMap;
import java.util.Map;

public final class CommandProviderAdmin {
    private final Map<Integer, CommandExecute> repository = new HashMap<>();

    public CommandProviderAdmin() {
        repository.put(1, new AddNewBook());
        repository.put(2, new ShowAllBooksInLibrary());
        repository.put(3, new DeleteBookById());
        repository.put(4, new SearchBookByName());
        repository.put(5, new SearchBookById());
        repository.put(6, new ReplaceTheBook());
        repository.put(7, new RegistrationAdmin());
        repository.put(8, new AllClients());
        repository.put(9, new AllAdmins());
        repository.put(10, new DeleteAdmin());
        repository.put(11, new ShowAllIssuedBooks());
        repository.put(0, new WrongCommand());
    }

    public CommandExecute getCommand(int numberCommand) {
        CommandExecute command = null;
        try {
            command = repository.get(numberCommand);
        } catch (IllegalArgumentException | NullPointerException e) {
            command = repository.get(0);
        }
        return command;
    }
}
