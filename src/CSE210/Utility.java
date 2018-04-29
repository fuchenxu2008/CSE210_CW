/**
 * Utility
 */

package src.CSE210;

import java.util.ArrayList;
import java.util.Scanner;
// import java.util.ArrayList;

public class Utility {
    static Scanner kb = new Scanner(System.in);
    
    public static String stringInput() {
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

    public static int numberInput(int lowerBound, int upperBound) {
        Boolean validInput = false;
        int input = 0;
        while (!validInput) {
            try {
                input = kb.nextInt();
                if (lowerBound <= input && input <= upperBound) validInput = true;
                else System.out.print("Invalid input!\nRetry: ");
                kb.nextLine();
            } catch (Exception e) {
                System.out.print("Invalid input!\nRetry: ");
                kb.nextLine(); // Clear the buffer
            }
        }
        return input;
    }

    public static int sumOfArr(ArrayList<Integer> arr) {
        int sum = 0;
        for (int ele : arr) {
            sum += ele;
        }
        return sum;
    }
}