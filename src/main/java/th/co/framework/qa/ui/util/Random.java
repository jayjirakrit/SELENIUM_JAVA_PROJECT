package th.co.framework.qa.ui.util;

import org.apache.commons.lang3.RandomStringUtils;

public class Random {
    public static String getRandomForUser(int min, int max) {

        return new NumberToWords().getTextFromNumber(String.valueOf((int) Math.floor(Math.random() * (max - min + 1) + min)));
    }

    public static int getRandomNumber(int min, int max) {

        return (int) Math.floor(Math.random() * (max - min + 1) + min);
    }

    public static String getRandomNumber(int digit) {
        return RandomStringUtils.randomNumeric(digit);
    }

    public static String getRandomForPhoneNumber() {
        int min = 10000000;
        int max = 99999999;
        String phoneNumber = "06";
        int prefix = (int) Math.floor(Math.random() * (3 - 1 + 1) + 1);
        switch (prefix) {
            case 1:
                phoneNumber = "06";
                break;
            case 2:
                phoneNumber = "08";
                break;
            case 3:
                phoneNumber = "09";
                break;

        }
        return phoneNumber + String.valueOf((int) Math.floor(Math.random() * (max - min + 1) + min));
    }

    public static String getRandomAlphaNumeric(int digit) {
        return RandomStringUtils.randomAlphanumeric(digit);
    }

}
