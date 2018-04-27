/**
 * Main
 */

package src.CSE210;

import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        System.out.println("Initializing...");
        ExcelReader excel = new ExcelReader("datasets/Dataset_RG.xlsx");
        Boolean exit = false;
        while (!exit) {
            System.out.print("\nT3-1: calculate the number of distinct researchers in the dataset.\nT3-2: calculate the number of distinct interests in the dataset.\nT3-3: given a researcher’s name, show detailed information about him/her (e.g., university, department, interests).\nT3-4: given an interest, calculate the number of researchers who have that interest.\nT3-5: given two interests, show the number of times they co-occur.\nT3-6: given a researcher, find similar researchers based on their interests.\n\nInput task number to begin: ");
            String action = Utility.userInput();
            switch (action) {
                case "1":
                    System.out.println(Researcher.numberOfResearchers);
                    break;
                case "2":
                    System.out.println(Interest.interestMap.size());
                    break;
                case "3":
                    System.out.print("\nEnter a researcher's name: ");
                    String name = Utility.userInput();
                    ArrayList<Researcher> researchers = Researcher.getResearchersByName(name);
                    if (researchers.size() == 1) {
                        researchers.get(0).printDetails();
                    } else if (researchers.size() > 1) {
                        System.out.println("\n!! Attention: There are multiple records with that name!");
                        for (Researcher r : researchers) {
                            r.printDetails();
                        }
                    }
                    break;
                case "4":
                    System.out.print("\nEnter an interest: ");
                    String interest = Utility.userInput();
                    System.out.println(Interest.numOfInterestSharedBy(interest));
                    break;
                case "5":
                    System.out.print("\nEnter two interests (delimited by comma): ");
                    String input = Utility.userInput();
                    String[] inputInterests = input.split(",");
                    System.out.println(Interest.getCoOccurTimes(inputInterests));
                    break;
                case "7":
                    exit = true;
                    break;
                default:
                    System.out.println("\nInvalid input!");
                    break;
            }
        }
    }
}