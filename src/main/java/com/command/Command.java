package com.command;

import java.io.IOException;
import java.util.Scanner;

public interface Command {
    void execute() throws IOException;

    Scanner SCANNER = new Scanner(System.in);
}
