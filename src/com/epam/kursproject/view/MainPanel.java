package com.epam.kursproject.view;

import com.epam.kursproject.controller.Controller;
import com.epam.kursproject.exception.ControllerException;

import java.util.Scanner;

import static com.epam.kursproject.manager.DataManager.*;

public class MainPanel {
    private static boolean entryFlag;
    private static Controller controller;

    public static void main(String[] args) {
        controller = new Controller();
        entryFlag = false;
        System.out.println(MENU_COMMAND);
        while (!entryFlag) {
            Scanner scanner = new Scanner(System.in);
            String line = scanner.nextLine();
            switch (line) {
                case "1": {
                    displayInformationAboutCommandBeforeLogin();
                    break;
                }
                case "0": {
                    return;
                }
                default: {
                    if (line.length() > 5) {
                        if (!line.toUpperCase().substring(0, 5).equals("LOGIN")) {
                            System.out.println("you need to log in before doing other actions");
                        }
                    }
                    try {
                        entryFlag = controller.doPost(line);
                        System.out.println("You in system");
                        System.out.println(MENU_COMMAND);
                    } catch (ControllerException ex) {
                        System.out.println(ex.getMessage());
                    }
                }
            }
        }
        while (true) {
            Scanner scanner = new Scanner(System.in);
            String line = scanner.nextLine();
            switch (line) {
                case "1": {
                    displayInformationAboutCommandAfterLogin();
                    break;
                }
                case "0": {
                    return;
                }
                default: {
                    if (line.length() > 5) {
                        if (line.toUpperCase().substring(0, 5).equals("LOGIN")) {
                            System.out.println("you already in system");
                        }
                    }
                    try {
                        if (controller.doPost(line)) {
                            System.out.println("Command completed successfully");
                        }
                    } catch (ControllerException ex) {
                        System.out.println(ex.getMessage());
                    }
                }
            }
        }
    }

    private static void displayInformationAboutCommandBeforeLogin() {
        System.out.println(LOGIN_COMMAND);
    }

    private static void displayInformationAboutCommandAfterLogin() {
        System.out.println(POSSIBLE_COMMAND);
    }
}
