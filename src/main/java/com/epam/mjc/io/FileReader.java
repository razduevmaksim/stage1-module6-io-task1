package com.epam.mjc.io;

import java.io.*;
import java.util.HashMap;

public class FileReader {
    HashMap<String, String> fileMapValue = new HashMap<>();

    public Profile getDataFromFile(File file) {
        try (FileInputStream fis = new FileInputStream(file.getPath())) {
            int i;

            String key = "";
            StringBuilder currentWord = new StringBuilder();

            while ((i = fis.read()) != -1) {
                if ((char) i == ':') {
                    key = currentWord.toString();
                    currentWord = new StringBuilder();
                } else if ((char) i == '\n') {
                    fileMapValue.put(key.trim(), currentWord.toString().trim());
                    key = "";
                    currentWord = new StringBuilder();
                } else {
                    currentWord.append((char) i);
                }
            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        return new Profile(fileMapValue.get("Name"), Integer.parseInt(fileMapValue.get("Age")), fileMapValue.get("Email"), Long.parseLong(fileMapValue.get("Phone")));
    }
}
