package com.command;

import com.command.Command;

public class Exit implements Command {

    @Override
    public void execute() {
        System.exit(0);
    }
}
