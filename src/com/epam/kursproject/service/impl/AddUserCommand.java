package com.epam.kursproject.service.impl;

import com.epam.kursproject.bean.User;
import com.epam.kursproject.dao.DAOAccess;
import com.epam.kursproject.dao.DAOUser;
import com.epam.kursproject.exception.CommandException;
import com.epam.kursproject.exception.DAOException;
import com.epam.kursproject.logic.ChangeFileAboutUsers;
import com.epam.kursproject.logic.ChangeUsersFile;
import com.epam.kursproject.manager.DataManager;
import com.epam.kursproject.service.Command;

import java.util.List;
import java.util.Map;

public class AddUserCommand implements Command {
    @Override
    public boolean execute(List<String> data) throws CommandException {
        if (data.size() != DataManager.COUNT_ARGUMENTS_ADD_USER_COMMAND) {
            throw new CommandException(DataManager.COUNT_ARGUMENTS_COMMAND_EXCEPTION);
        }
        String login = data.get(0);
        String parole = data.get(1);
        String name = data.get(0);
        double moneyTransfer = Double.parseDouble(data.get(2));
        try {
            Map<String, String> dataAccess = new DAOAccess().getAccessData();
            for (String key : dataAccess.keySet()) {
                if (login.equals(key)) {
                    throw new CommandException(DataManager.ADD_USER_NAME_EXCEPTION);
                }
            }
            dataAccess.put(login, parole);
            List<User> userList = new DAOUser().getUserList();
            userList.add(new User(name, moneyTransfer));
            new ChangeFileAboutUsers().overwriteFIle(userList);
            new ChangeUsersFile().overwriteFIle(dataAccess);
            return true;
        } catch (DAOException ex) {
            throw new CommandException(ex.getMessage());
        }
    }
}
