package com.epam.kursproject.service.factory;

import com.epam.kursproject.exception.CommandException;
import com.epam.kursproject.manager.DataManager;
import com.epam.kursproject.service.Command;

public class CommandFactory {
    private static CommandFactory commandFactory;

    private CommandFactory() {
    }

    public static CommandFactory getInstance() {
        if (commandFactory == null) {
            commandFactory = new CommandFactory();
        }
        return commandFactory;
    }

    public Command getCurrentCommand(String command) throws CommandException {
        Command currentCommand;
        try {
            currentCommand = CommandEnum.getCurrentCommand(command);
        } catch (IllegalArgumentException ex) {
            throw new CommandException(DataManager.COMMAND_NAME_EXCEPTION);
        }
        return currentCommand;
    }
}
