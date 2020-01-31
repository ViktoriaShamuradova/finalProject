package by.javatr.controller.command.impl;

import by.javatr.controller.command.CommandExecute;

public class WrongCommand implements CommandExecute {

    @Override
    public String execute()  {
        return "Wrong request, please, try again";
    }
}
