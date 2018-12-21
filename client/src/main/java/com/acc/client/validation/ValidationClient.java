package com.acc.client.validation;

import org.apache.commons.io.FilenameUtils;

public class ValidationClient {

    public static String pathOfficeFile = null;
    public static Long payments = 0L;
    public static String urlPost = null;
    public static String pathPaymentsFile = null;

    public static void validationArgs (String[] args) {

        try {
            if (args.length == 4) {
                pathOfficeFile = args[0];
                String checkName = FilenameUtils.getExtension(pathOfficeFile);
                if (checkName.equals("txt")) {
                    payments = Long.parseLong(args[1]);
                    if (payments < 0) {
                        throw new NumberFormatException("Payment can not be less than zero");
                    } else {
                        urlPost = args[2];
                        pathPaymentsFile = args[3];
                    }
                } else {
                    throw new NumberFormatException("File must have the extension .txt");
                }
            } else {
                throw new NumberFormatException("There should be four parameters");
            }
        } catch (NumberFormatException e) {
            System.out.println(e.getMessage());
            System.exit(1);
        }
    }
}
