package com.epam.kursproject.controller;

import com.epam.kursproject.exception.CommandException;
import com.epam.kursproject.exception.ControllerException;
import com.epam.kursproject.manager.DataManager;
import com.epam.kursproject.service.Command;
import com.epam.kursproject.service.factory.CommandFactory;

import java.util.Arrays;
import java.util.List;

public class Controller {
    public boolean doPost(String line) throws ControllerException {
        line.trim();//удаление пробелов
        List<String> arguments = Arrays.asList(line.split(DataManager.WORDS_REGEX));
        Command command;
        boolean flagOfActionCommand = false;
        try {
            command = CommandFactory.getInstance().getCurrentCommand(arguments.get(0));
            arguments = arguments.subList(1, arguments.size());
            flagOfActionCommand = command.execute(arguments);
        } catch (CommandException ex) {
            throw new ControllerException(ex.getMessage());
        }
        return flagOfActionCommand;
    }
}
