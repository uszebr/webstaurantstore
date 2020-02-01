package com.webstaurantstore.basetest.utility;

import org.apache.commons.lang.RandomStringUtils;

import java.util.Random;

public abstract class RandomDataGenerator {
    private static final int MIN_EMAIL_SIZE = 2;//characters before @
    private static final int MAX_EMAIL_SIZE = 60;//characters before @
    private static String[] domains = {"@gmail.com", "@g.com", "@lro.com", "@yahoo.com", "@mail.com", "@f.com", "@aBrACaDaBrA.vOt"};// allowed domains for email


    public static String emailGenerator(int minLenght, int maxLenght) {// Need to Refactor!! Check length and dots inside..  generated emails random size(from constants) and added domains
        String allowedCharsAnywhere = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789_-.";//Characters allowed in email
        String allowedInsideEmail = ".";// dot can not be on first or last position
        String email = "";
        Random random = new Random();
        int emailLenght = random.nextInt(MAX_EMAIL_SIZE - MIN_EMAIL_SIZE) + MIN_EMAIL_SIZE;
        do {
            if (emailLenght < 3) {//for short email - not using dots inside
                email = RandomStringUtils.random(emailLenght, allowedCharsAnywhere);
            } else {//for long emails using dots inside but not in first and last position
                email = RandomStringUtils.random(1, allowedCharsAnywhere)
                        + RandomStringUtils.random(emailLenght - 2, allowedCharsAnywhere + allowedInsideEmail)
                        + RandomStringUtils.random(1, allowedCharsAnywhere);
            }
        } while (email.contains(".."));//not allowed to have to dots near each other

        email = email + domains[random.nextInt(domains.length)];// adding random domain name from array

        return email;
    }

    public static String passwordGenerator() {


        String allowesChars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789_-@#$&%";
        final int MIN_PASSWORD_LENGHT = 6;
        final int MAX_PASSWORD_LENGHT = 22;

        Random random = new Random();
        int passwordLenght = random.nextInt(MAX_PASSWORD_LENGHT - MIN_PASSWORD_LENGHT)+ MIN_PASSWORD_LENGHT;
        String password = RandomStringUtils.random(passwordLenght,allowesChars);
        return password;
    }
}
