package com.acc.validation;

public class ValidationServer {

    private static int port = 8080;

    public static int getPort() {
        return port;
    }

    public static void validationPort(String[] args) {

        try {
            if (args.length == 1) {
                port = Integer.parseInt(args[0]);
                if (port < 1 || port > 65535) {
                    throw new NumberFormatException();
                }
            }
        } catch (NumberFormatException e) {
            System.out.println("The argument must be an Integer and should be between 1 and 65535");
            System.exit(1);
        }
    }
}

