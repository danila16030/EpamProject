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

public class DeleteUserCommand implements Command {
    @Override
    public boolean execute(List<String> data) throws CommandException {

        if (data.size() != DataManager.COUNT_ARGUMENTS_DELETE_USER_COMMAND) {
            throw new CommandException(DataManager.COUNT_ARGUMENTS_COMMAND_EXCEPTION);
        }
        String login = data.get(0);
        try {
            Map<String, String> dataAccess = new DAOAccess().getAccessData();
            for (String key : dataAccess.keySet()) {
                if (login.equals(key)) {
                    dataAccess.remove(login);
                    List<User> userList = new DAOUser().getUserList();
                    for (User user : userList) {
                        if (user.getName().equals(login)) {
                            userList.remove(user);
                            break;
                        }
                    }
                    new ChangeFileAboutUsers().overwriteFIle(userList);
                    new ChangeUsersFile().overwriteFIle(dataAccess);
                    return true;
                }
            }
            throw new CommandException(DataManager.USER_LOGIN_EXCEPTION);
        } catch (
                DAOException ex) {
            throw new CommandException(ex.getMessage());
        }
    }
}
