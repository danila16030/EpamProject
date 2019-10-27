package com.epam.kursproject.logic;

import com.epam.kursproject.bean.User;
import com.epam.kursproject.exception.DAOException;
import com.epam.kursproject.manager.DataManager;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Stream;

public class DaoUsersLogic {

    public List<User> validateUserFile(String filePath) throws DAOException {
        List<User> usersList = new ArrayList<>();
        boolean flag = checkFile(filePath);//пустой ли файл и существует ли вообще
        if (flag) {
            throw new DAOException("Check file " + filePath);
        } else {
            usersList = loadDataUsersFromFile(filePath);
        }
        if (usersList.isEmpty()) {
            throw new DAOException("All data in file is incorrect " + filePath);
        }
        return usersList;
    }

    private List<User> loadDataUsersFromFile(String filePath) throws DAOException {
        LinkedList<String> listOfLines = new LinkedList<>();
        List<User> allData = new ArrayList<>();
        try (Stream<String> stringStream = Files.lines(Paths.get(filePath))) {
            stringStream.forEach(listOfLines::add);
        } catch (IOException ex) {
            throw new DAOException(ex);
        }
        for (String line : listOfLines) {
            if (validationCheck(line, DataManager.DATA_USERS_REGEX)) {
                User user = getUserFromLine(line);
                allData.add(user);
            }
        }
        return allData;
    }

    private User getUserFromLine(String line) {
        String[] parameters = line.split(DataManager.SPLIT_REGEX);
        String name = parameters[0];
        Double money = Double.parseDouble(parameters[1]);
        User user=new User(name,money);
        return user;
    }

    private boolean validationCheck(String line, final String regex) {
        Pattern p = Pattern.compile(regex);
        Matcher m = p.matcher(line);
        return m.matches();
    }

    private boolean checkFile(String filePath) {
        File testFile = new File(filePath);
        if (testFile.length() == 0 || !testFile.exists()) {
            return true;
        } else {
            return false;
        }
    }
}
