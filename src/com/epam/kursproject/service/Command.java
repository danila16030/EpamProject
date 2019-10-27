package com.epam.kursproject.service;

import com.epam.kursproject.exception.CommandException;

import java.util.List;

public interface Command {
    boolean execute(List<String> data) throws CommandException;//лист содеожит комманду и пароль
}
