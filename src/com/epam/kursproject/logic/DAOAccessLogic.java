package com.epam.kursproject.logic;

import com.epam.kursproject.exception.DAOException;
import com.epam.kursproject.manager.DataManager;

import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Stream;

public class DAOAccessLogic {
    public Map<String, String> validateFile(String filePath) throws DAOException {
        Map<String, String> accessData = new HashMap<>();
        boolean flag = checkFile(filePath);//пустой ли файл и существует ли вообще
        if (flag) {
            throw new DAOException("Check file " + filePath);
        } else {
            accessData = loadDataAccessFromFile(filePath);
        }
        if (accessData.isEmpty()) {
            throw new DAOException("All data in file is incorrect " + filePath);
        }
        return accessData;
    }

    private Map<String, String> loadDataAccessFromFile(String filePath) throws DAOException {
        LinkedList<String> listOfLines = new LinkedList<>();
        Map<String, String> allData = new HashMap<>();
        try (Stream<String> stringStream = Files.lines(Paths.get(filePath))) {
            stringStream.forEach(listOfLines::add);
        } catch (IOException ex) {
            throw new DAOException(ex);
        }
        for (String line : listOfLines) {
            if (validationCheck(line, DataManager.DATA_ACCESS_REGEX)) {
                List<String> data = getDataAccessFromLine(line);
                allData.put(data.get(0), data.get(1));
            }
        }
        return allData;
    }

    private List<String> getDataAccessFromLine(String line) {
        String[] parameters = line.split(DataManager.SPLIT_REGEX);
        String login = parameters[0];
        String password = parameters[1];
        List<String> dataList = new ArrayList<>();
        dataList.add(login);
        dataList.add(password);
        return dataList;
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

