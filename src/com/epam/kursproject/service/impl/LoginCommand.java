package com.epam.kursproject.service.impl;

import com.epam.kursproject.dao.DAOAccess;
import com.epam.kursproject.exception.CommandException;
import com.epam.kursproject.exception.DAOException;
import com.epam.kursproject.manager.DataManager;
import com.epam.kursproject.service.Command;

import java.util.List;
import java.util.Map;

public class LoginCommand implements Command {
    @Override
    public boolean execute(List<String> data) throws CommandException {
        if (data.size() != DataManager.COUNT_ARGUMENTS_LOGIN_COMMAND) {
            throw new CommandException(DataManager.COUNT_ARGUMENTS_COMMAND_EXCEPTION);
        }
        String login = data.get(0);
        String password = data.get(1);
        try {
            Map<String, String> dataAccess = new DAOAccess().getAccessData();
            for (String key : dataAccess.keySet()) {
                if (checkLoginPassword(login, key, password, dataAccess.get(key))) {
                    return true;
                }
            }
            throw new CommandException(DataManager.LOGIN_OR_PASSWORD_EXCEPTION);
        } catch (DAOException ex) {
            throw new CommandException(ex.getMessage());
        }
    }

    private boolean checkLoginPassword(String currentLogin, String requiredLogin, String currentPassword,
                                       String requiredPassword) {
        return currentLogin.equals(requiredLogin) && currentPassword.equals(requiredPassword);
    }
}