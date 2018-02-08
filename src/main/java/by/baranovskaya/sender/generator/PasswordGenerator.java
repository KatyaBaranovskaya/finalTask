package by.baranovskaya.sender.generator;

import java.util.Random;

public class PasswordGenerator {
    private final static int MIN_PASSWORD_LENGTH = 6;
    private final static int MAX_PASSWORD_LENGTH = 10;
    private final static int BOUND = 3;

    public static String generate() {
        StringBuffer pass = new StringBuffer();
        Random random = new Random();
        int amtChars = MIN_PASSWORD_LENGTH + random.nextInt(MAX_PASSWORD_LENGTH - MIN_PASSWORD_LENGTH + 1);

        for (int i = 0; i < amtChars; ++i) {
            char next = 0;
            int range = 10;

            switch(random.nextInt(BOUND)) {
                case 0: {next = '0'; range = 10;} break;
                case 1: {next = 'a'; range = 26;} break;
                case 2: {next = 'A'; range = 26;} break;
            }

            pass.append((char)((random.nextInt(range)) + next));
        }

        return pass.toString();
    }
}