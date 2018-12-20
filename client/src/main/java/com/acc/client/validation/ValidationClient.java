package com.acc.client.validation;

import org.apache.commons.io.FilenameUtils;

public class ValidationClient {

    private static String pathOfficeFile = null;
    private static Long payments = 0L;
    private static String urlPost = null;
    private static String pathPaymentsFile = null;

    public static String getPathOfficeFile() {
        return pathOfficeFile;
    }

    public static Long getPayments() {
        return payments;
    }

    public static String getUrlPost() {
        return urlPost;
    }

    public static String getPathPaymentsFile() {
        return pathPaymentsFile;
    }

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
