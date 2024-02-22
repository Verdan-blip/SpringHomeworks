package ru.kpfu.itis.bagaviev.api.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class IOUtils {

    public static String readAll(InputStream in) throws IOException {
        StringBuilder result = new StringBuilder();
        try (InputStreamReader inputStreamReader = new InputStreamReader(in)) {
            try (BufferedReader reader = new BufferedReader(inputStreamReader)) {
                String part;
                while ((part = reader.readLine()) != null) {
                    result.append(part);
                }
            }
        }
        return result.toString();
    }

}
