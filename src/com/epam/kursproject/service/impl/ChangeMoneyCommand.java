package com.epam.kursproject.service.impl;

import com.epam.kursproject.bean.User;
import com.epam.kursproject.dao.DAOUser;
import com.epam.kursproject.exception.CommandException;
import com.epam.kursproject.exception.DAOException;
import com.epam.kursproject.logic.ChangeFileAboutUsers;
import com.epam.kursproject.manager.DataManager;
import com.epam.kursproject.service.Command;

import java.util.List;

public class ChangeMoneyCommand implements Command {
    @Override
    public boolean execute(List<String> data) throws CommandException {
        if (data.size() != DataManager.COUNT_ARGUMENTS_CHANGE_MONEY_COMMAND) {
            throw new CommandException(DataManager.COUNT_ARGUMENTS_COMMAND_EXCEPTION);
        }
        String name = data.get(0);
        double moneyTransfer = Double.parseDouble(data.get(1));
        try {
            List<User> userList = new DAOUser().getUserList();
            for (User user : userList) {
                if (user.getName().equals(name)) {
                    changeAmountOfMoney(user, moneyTransfer);
                    new ChangeFileAboutUsers().overwriteFIle(userList);
                    return true;
                }
            }
            throw new CommandException(DataManager.USER_NAME_EXCEPTION);
        } catch (DAOException ex) {
            throw new CommandException(ex.getMessage());
        }
    }

    private void changeAmountOfMoney(User user, double moneyTransfer) {
        user.setAmountOfMoney(user.getAmountOfMoney() + moneyTransfer);
    }
}
