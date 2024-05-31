package org.ismetg.utility;

import java.util.Scanner;

public class InputUtil {
    static Scanner scanner = new Scanner(System.in);
    public static String getStringValue(String message) {
        System.out.println(message);
        String value = scanner.nextLine();
        return value;
    }
    public static double getDoubleValue(String message) {
        System.out.println(message);
        double value = scanner.nextDouble();
        scanner.nextLine();
        return value;
    }
    public static int getIntegerValue(String message) {
        System.out.println(message);
        int value = scanner.nextInt();
        scanner.nextLine();
        return value;
    }
}
