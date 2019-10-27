package com.epam.kursproject.logic;

import com.epam.kursproject.bean.User;

import java.io.*;
import java.util.List;

import static com.epam.kursproject.manager.DataManager.USER_FILE;

public class ChangeFileAboutUsers {
    public boolean overwriteFIle(List<User> userList) {
        String text;
        try (FileWriter writer = new FileWriter(USER_FILE, false)) {
            for (User user : userList) {
                text=user.getName()+" "+user.getAmountOfMoney()+'\n';
                writer.write(text);
            }
            writer.flush();// финализирует выходное состояние, очищая все буферы вывода
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
        return true;
    }
}
