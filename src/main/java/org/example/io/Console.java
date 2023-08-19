package org.example.io;

import java.util.Scanner;

public class Console {

    public static void main(String[] args) {
        System.out.println("Hello World! I'm echo console.");
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please input something: ");
        while (scanner.hasNext()) {
            System.out.println(scanner.nextLine());
            // stop the program when input "exit"
            if (scanner.nextLine().equals("exit")) {
                break;
            }
        }
        scanner.close();
    }
}
