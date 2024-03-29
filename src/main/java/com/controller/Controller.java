package com.controller;

import com.command.Command;
import com.command.Commands;
import com.command.UserInputUtil;
import com.command.UtilEnumToList;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class Controller {
    private Controller() {
    }

    public static void run() {
        try {
            int userAction = chooseAction();
            Commands[] commands = Commands.values();
            Command command = commands[userAction].getCommand();
            command.execute();
            run();
            //repeatOrOtherAction(command);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static int chooseAction() throws IOException {
        System.out.println("Choose action");
        Commands[] commands = Commands.values();
        List<String> commandsList = UtilEnumToList.getNamesOfType(commands);
        return UserInputUtil.getUserInput(commandsList, commands.length);
    }

    /*private static void repeatOrOtherAction(Command command) throws IOException {
        int input = UserInputUtil.getUserInput(List.of("Repeat", "Other action"));
        switch (input) {
            case 0 -> {
                command.execute();
                repeatOrOtherAction(command);
            }
            case 1 -> run();
            default -> {
                System.out.println("Wrong input");
                repeatOrOtherAction(command);
            }
        }
    }*/
}
