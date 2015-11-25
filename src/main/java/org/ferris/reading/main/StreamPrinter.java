package org.ferris.reading.main;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 *
 * @author Michael Remijan mjremijan@yahoo.com @mjremijan
 */
public class StreamPrinter {

    public static void print(InputStream inputStream, String streamDescription) {
        try {
            System.out.println(streamDescription);
            for (int i = 0; i < streamDescription.length(); i++) {
                System.out.print(">");
            }
            System.out.println();

            if (inputStream == null) {
                System.out.println("The InputStream object is null.");
            } else {
                BufferedReader reader = new BufferedReader(new InputStreamReader(
                        inputStream));
                for (String s = reader.readLine(); s != null; s = reader.readLine()) {
                    System.out.println(s);
                }
            }

            for (int i = 0; i < streamDescription.length(); i++) {
                System.out.print("<");
            }
            System.out.println();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
