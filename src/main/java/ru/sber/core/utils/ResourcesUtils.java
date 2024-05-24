package ru.sber.core.utils;

import java.io.*;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;

import static groovyjarjarantlr4.v4.runtime.misc.Utils.readFile;

public class ResourcesUtils {

    public static String getFileContentAsString(String relativePathFile) {
        ClassLoader loader = ResourcesUtils.class.getClassLoader();
        try {
            return readFile(Paths.get(loader.getResource(relativePathFile).toURI()).toString());
        } catch (URISyntaxException e) {
            e.printStackTrace();
            throw new RuntimeException("File " + relativePathFile + " doesn't exist! Check path to file");
        }
        /*ClassLoader classLoader = ResourcesUtils.class.getClassLoader();
        try {
            String pathToFile =  Paths.get(classLoader.getResource(relativePathFile).toURI()).toString();
            return new String(Files.readAllBytes(new File(pathToFile).toPath()));
        } catch (URISyntaxException | NullPointerException e) {
            throw new RuntimeException("File " + relativePathFile + " doesn't exist! Check path to file");
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("Cannot read the file " + relativePathFile, e);
        }*/
    }

    public static String readFile(String path) {
        try {
            StringBuilder retValue = new StringBuilder();
            BufferedReader in = new BufferedReader(
                    new InputStreamReader(new FileInputStream(new File(path)), "UTF8"));
            String line = "";
            while ((line = in.readLine()) != null) {
                retValue.append(line + System.lineSeparator());
            }
            in.close();
            return retValue.toString();
        } catch (IOException e) {
            throw new RuntimeException("Could not read file by provided path " + path, e);
        }
    }

}
