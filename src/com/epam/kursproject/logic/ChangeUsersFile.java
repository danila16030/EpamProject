package com.epam.kursproject.logic;

import com.epam.kursproject.bean.User;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.Map;

import static com.epam.kursproject.manager.DataManager.ACCESS_FILE;

public class ChangeUsersFile {
    public boolean overwriteFIle(Map<String,String> userMap) {
        String text;
        try (FileWriter writer = new FileWriter(ACCESS_FILE, false)) {
            for (String login : userMap.keySet()) {
                text = login + " " + userMap.get(login) + '\n';
                writer.write(text);
            }
            writer.flush();// финализирует выходное состояние, очищая все буферы вывода
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
        return true;
    }
}
