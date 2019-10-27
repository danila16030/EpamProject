package com.epam.kursproject.dao;

import com.epam.kursproject.bean.User;
import com.epam.kursproject.exception.DAOException;
import com.epam.kursproject.logic.DAOAccessLogic;
import com.epam.kursproject.logic.DaoUsersLogic;
import com.epam.kursproject.manager.DataManager;

import java.util.List;
import java.util.Map;

public class DAOUser {
    private List<User> userList;

    public DAOUser() throws DAOException {
        try {
            this.userList = new DaoUsersLogic().validateUserFile(DataManager.USER_FILE);
        } catch (DAOException ex) {
            throw new DAOException(ex.getMessage());
        }
    }

    public List<User> getUserList() {
        return userList;
    }
}
