package com.epam.kursproject.service.factory;

import com.epam.kursproject.service.Command;
import com.epam.kursproject.service.impl.AddUserCommand;
import com.epam.kursproject.service.impl.ChangeMoneyCommand;
import com.epam.kursproject.service.impl.DeleteUserCommand;
import com.epam.kursproject.service.impl.LoginCommand;

public enum CommandEnum {
    LOGIN(new LoginCommand()),
    BALANCECHANGE(new ChangeMoneyCommand()),
    ADDUSSER(new AddUserCommand()),
    DELETEUSER(new DeleteUserCommand());

    private Command command;

    CommandEnum(Command command) {
        this.command = command;
    }

    public static Command getCurrentCommand(String action) {
        return CommandEnum.valueOf(action.toUpperCase()).command;//toUpperCase увеличивает(делает ее как класс и передает ее в вер где логин
    }
}
