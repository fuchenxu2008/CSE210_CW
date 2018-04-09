/**
 * Main
 */

package src.CSE210;
import java.util.Scanner;

public class Main {
    static Scanner kb = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.println("Initializing...");
        ExcelReader excel = new ExcelReader("datasets/Dataset_RG.xlsx");
        System.out.print("T3-1: calculate the number of distinct researchers in the dataset.\nT3-2: calculate the number of distinct interests in the dataset.\nT3-3: given a researcher’s name, show detailed information about him/her (e.g., university, department, interests).\nT3-4: given an interest, calculate the number of researchers who have that interest.\nT3-5: given two interests, show the number of times they co-occur.\nT3-6: given a researcher, find similar researchers based on their interests.\nInput task number to begin: ");
        int action = kb.nextInt();
        switch (action) {
            case 1:
                System.out.println(Researcher.researcherMap.size());
                break;
            case 2:
                System.out.println(Interest.interestMap.size());
                break;
            case 3:
                System.out.print("Enter a researcher's name: ");
                String name = kb.nextLine();
                break;
            case 4:
                System.out.println(Interest.interestMap.containsKey(""));
                break;
            default:
                break;
        }
    }
}