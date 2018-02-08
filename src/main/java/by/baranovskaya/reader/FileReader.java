package by.baranovskaya.reader;

import by.baranovskaya.exception.ParseDataException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.*;
import java.util.Scanner;

public class FileReader {
    private final static Logger LOGGER = LogManager.getLogger(FileReader.class);
    private final static String FILENAME = "D://breakfastPrice.txt";

    public static double readData() throws ParseDataException {
        double price = 0;
        File file = new File(FILENAME);

        if(!file.exists()) {
            throw new ParseDataException("File doesn't exist.");
        }
        else if(file.length() == 0) {
            throw new ParseDataException("File is empty.");
        }

        Scanner scanner = null;
        try {
            scanner = new Scanner(file);
            if (scanner.hasNext()){
                price = scanner.nextDouble();
            }
        } catch (FileNotFoundException e) {
            LOGGER.error("File not found" + e);
        } finally {
            if (scanner != null) {
                scanner.close();
            }
        }

        return price;
    }
}
