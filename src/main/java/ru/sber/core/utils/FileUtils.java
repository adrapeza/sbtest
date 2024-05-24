package ru.sber.core.utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;

import static ru.sber.core.contants.Constants.LOG_FILE;

public class FileUtils {

    public static void printToFile(String pathToFile, String text) {
        PrintWriter pw = null;
        try {
            pw = new PrintWriter(new FileOutputStream(
                    new File(pathToFile), true));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(String.format("File '' not found", pathToFile));
        }
        pw.append(text);
        pw.close();

    }

}
