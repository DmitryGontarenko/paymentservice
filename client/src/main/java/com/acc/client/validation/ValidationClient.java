package com.acc.client.validation;

import org.apache.commons.io.FilenameUtils;

public class ValidationClient {

    public static void validationArgs (String[] args) {

        String fileName = args[0];
        int payments = 0;

        try {
            if (args.length == 4) {
                String checkName = FilenameUtils.getExtension(fileName);
                if (checkName.equals("txt")) {
                    payments = Integer.parseInt(args[1]);
                    if (payments < 0) {
                        throw new NumberFormatException("Payment can not be less than zero");
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
