/**
 * Utility
 */

package src.CSE210;

import java.util.Scanner;
import java.util.ArrayList;

public class Utility {
    static Scanner kb = new Scanner(System.in);

    // public String[] concat(String[] arr1, String[] arr2) {
    //     String[] mergedArr = new String[arr1.length + arr2.length];
    // }
    
    public static String userInput() {
        Boolean validInput = false;
        String input = "";
        while (!validInput) {
            try {
                input = kb.nextLine().trim();
                validInput = true;
            } catch (Exception e) {
                System.out.print("Invalid input!\nRetry: ");
                kb.nextLine(); // Clear the buffer
            }
        }
        return input;
    }
}