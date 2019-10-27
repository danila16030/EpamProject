package com.epam.kursproject.dao;

import com.epam.kursproject.exception.DAOException;
import com.epam.kursproject.logic.DAOAccessLogic;
import com.epam.kursproject.manager.DataManager;

import java.util.Map;

public class DAOAccess {
    private Map<String, String> accessData;

    public DAOAccess() throws DAOException {
        try {
            this.accessData = new DAOAccessLogic().validateFile(DataManager.ACCESS_FILE);
        } catch (DAOException ex) {
            throw new DAOException(ex.getMessage());
        }
    }

    public Map<String, String> getAccessData() {
        return accessData;
    }
}
