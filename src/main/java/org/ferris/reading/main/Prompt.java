package org.ferris.reading.main;

import java.io.File;
import java.util.Scanner;

/**
 *
 * @author Michael Remijan mjremijan@yahoo.com @mjremijan
 */
public class Prompt {

    private static Scanner scanner = new Scanner(System.in);
    
    public static File file(String prompt) {
        System.out.printf("%s: ", prompt);
        return new File(scanner.nextLine());
    }
    
    public static Integer integer(String prompt) {
        System.out.printf("%s: ", prompt);
        return new Integer(scanner.nextLine());
    }
    
    public static Integer integer(String prompt, int defaultValue) {
        System.out.printf("%s: ", prompt);
        String str = scanner.nextLine();
        Integer toReturn;
        if (str == null || str.isEmpty()) {
            toReturn = defaultValue;
        } else {
            toReturn = new Integer(str);
        }
        return toReturn;
    }
}
