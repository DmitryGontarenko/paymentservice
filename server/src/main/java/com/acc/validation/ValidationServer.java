package com.acc.validation;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ValidationServer {

    public static void validationPort(String[] args) {

        int num = 0;

        try {
            if (args.length == 1) {
                num = Integer.parseInt(args[0]);
                if (num < 1 || num > 65535) {
                    throw new NumberFormatException();
                }
            }
        } catch (NumberFormatException e) {
            System.out.println("The argument must be an Integer and should be between 1 and 65535");
            System.exit(0);
        }
    }
}

