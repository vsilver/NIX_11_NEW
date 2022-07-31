package com.command;

import com.model.product.ProductType;
import org.apache.commons.lang3.StringUtils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class UserInputUtil {

    private static final BufferedReader READER = new BufferedReader(new InputStreamReader(System.in));

    private UserInputUtil() {
    }

    /*public static int getUserInput(int length, List<String> names) {
        int userType = -1;
        do {
            userType = getUserInput(names, length);
        } while (userType == -1);
        return userType;
    }*/

    public static int getUserInput(List<String> names, int length) throws IOException{
    //public static int getUserInput(List<String> names) throws IOException {
        /*try {
            System.out.println("Please enter number between 0 and " + length);
            for (int i = 0; i < length; i++) {
                System.out.printf("%d) %s%n", i, names.get(i));
            }
            int input = Integer.parseInt(READER.readLine());
            if (input >= 0 && input < length) {
                return input;
            }
        } catch (IOException | NumberFormatException e) {
            System.out.println("Input is not valid");
        }
        return -1;*/
        try {
        while (true) {
            System.out.println("Please enter number between 0 and " + names.size());
            for (int i = 0; i < names.size(); i++) {
                System.out.printf("%d) %s%n", i, names.get(i));
            }
            String input = READER.readLine();
            if (StringUtils.isNumeric(input)) {
                int userInput = Integer.parseInt(input);
                if (userInput < names.size()) {
                    return userInput;
                }
            } else {
                System.out.println("Wrong input");
            }
        }
        } catch (IOException | NumberFormatException e) {
            System.out.println("Input is not valid");
        }
        return -1;
    }
}
