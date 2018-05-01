/**
 * Util
 */

package src.CSE210;

import java.util.Scanner;

/**
 * Class that gathers some helper functions
 * @author Chenxu Fu
 */
public class Util {
    static Scanner kb = new Scanner(System.in);
    
    /**
     * Accept user's input as String
     * @param prompt text hint for input content
     * @return a string of user input
     */
    public static String stringInput(String prompt) {
        Boolean validInput = false;
        String input = "";
        while (!validInput) {
            try {
                System.out.print("\n=> " + prompt);
                input = kb.nextLine().trim();
                validInput = true;
            } catch (Exception e) {
                Util.log("Invalid input, please retry! ");
                kb.nextLine(); // Clear the buffer
            }
        }
        return input;
    }

    /**
     * Accept user's input as int
     * @param prompt text hint for input content
     * @param lowerBound the lower limit of input int
     * @param upperBound the upper limit of input int
     * @return an int within given range
     */
    public static int numberInput(String prompt, int lowerBound, int upperBound) {
        Boolean validInput = false;
        int input = 0;
        while (!validInput) {
            try {
                System.out.print("\n=> " + prompt);
                input = kb.nextInt();
                if (lowerBound <= input && input <= upperBound) validInput = true;
                else Util.log("Invalid input, please retry! ");
                kb.nextLine();
            } catch (Exception e) {
                Util.log("Invalid input, please retry! ");
                kb.nextLine(); // Clear the buffer
            }
        }
        return input;
    }

    /**
     * Simply output to the console
     * @param str string to log onto console
     */
    public static void log(String str) {
       System.out.println(str);
    }
}