package com.epam.kursproject.manager;

import com.epam.kursproject.service.impl.AddUserCommand;

public class DataManager {
    public static final String ACCESS_FILE = "data\\daoAccess.txt";
    public static final String USER_FILE = "data\\daoUsers.txt";

    public static final int COUNT_ARGUMENTS_LOGIN_COMMAND = 2;
    public static final int COUNT_ARGUMENTS_CHANGE_MONEY_COMMAND = 2;
    public static final int COUNT_ARGUMENTS_ADD_USER_COMMAND = 3;
    public static final int COUNT_ARGUMENTS_DELETE_USER_COMMAND = 1;

    public static final String SPLIT_REGEX = "\\s+";
    public static final String DATA_ACCESS_REGEX = "\\s*(\\w+)\\s+(\\w+)\\s*";//регулярное выраж с помощью кот мы можем проверять каждую строку на соотв нашему шаблону
    public static final String DATA_USERS_REGEX = "\\s*(\\w+)\\s+(\\-?\\d+\\.?\\d*)\\s*";//регулярное выраж с помощью кот мы можем проверять каждую строку на соотв нашему шаблону
    public static final String WORDS_REGEX = "[\\p{IsWhite_Space}]+";//разбивает строку на слова

    public static final String COMMAND_NAME_EXCEPTION = "Command is incorrect";
    public static final String COUNT_ARGUMENTS_COMMAND_EXCEPTION = "Count of arguments in command is incorrect";
    public static final String LOGIN_OR_PASSWORD_EXCEPTION = "Login or password is incorrect";
    public static final String USER_NAME_EXCEPTION = "Cant find user with this name";
    public static final String ADD_USER_NAME_EXCEPTION = "User with this name already existed";
    public static final String USER_LOGIN_EXCEPTION = "Cant find user with this login";

    public static final char DM = (char) 34;

    public static final String LOGIN_COMMAND = "To enter you must enter " + DM + "login" + DM + " then your login and parole";

    public static final String MENU_COMMAND = "If you wanna see posible command enter" + DM + " 1" + DM +
            " if you wanna quit enter"+DM + " 0" + DM+" or enter one of the application commands";
    public static final String POSSIBLE_COMMAND = "To change your balance you mast enter " + DM + "balancechange" + DM +
            " then your account name and money transaction(by positive or negative number)\n" +
            "To add new user in system  you mast enter " + DM + "adduser" + DM + " then information about user(login, " +
            " parole and amount of money on account)\n" +
            "To delete user from system you mast enter " + DM + "deleteuser" + DM + " login of the user that you want " +
            "to delete";

    private DataManager() {
    }
}
