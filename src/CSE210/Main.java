/**
 * Main
 */

package src.CSE210;

import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        System.out.println("Initializing...");
        // Open dataset and read to memory
        ExcelReader excel = new ExcelReader("datasets/Dataset_RG.xlsx");
        Test.runTest();
        Boolean exit = false;
        while (!exit) {
            System.out.print("\nT3-1: calculate the number of distinct researchers in the dataset.\nT3-2: calculate the number of distinct interests in the dataset.\nT3-3: given a researcher’s name, show detailed information about him/her.\nT3-4: given an interest, calculate the number of researchers who have that interest.\nT3-5: given two interests, show the number of times they co-occur.\nT3-6: given a researcher, find similar researchers based on their interests.\n\nInput task number to begin: ");
            int action = Utility.numberInput(1, 7);
            switch (action) {
                case 1:
                    System.out.println(Researcher.numberOfResearchers);
                    break;
                case 2:
                    System.out.println(Interest.interestMap.size());
                    break;
                case 3:
                    System.out.print("\nEnter a researcher's name: ");
                    String name = Utility.stringInput();
                    try {
                        Researcher.getResearcherByName(name).printDetails();
                    } catch (Exception e) {
                        System.out.println("No match found by that name!");
                    }
                    break;
                case 4:
                    System.out.print("\nEnter an interest: ");
                    String interest = Utility.stringInput().toLowerCase();
                    System.out.println(Interest.numOfInterestSharedBy(interest));
                    break;
                case 5:
                    System.out.print("\nEnter two interests (delimited by comma): ");
                    String inputInterests = Utility.stringInput().toLowerCase();
                    System.out.println(Interest.getCoOccurTimes(inputInterests.split(",")));
                    break;
                case 6:
                    System.out.print("\nEnter a researcher's name: ");
                    String inputResearcher = Utility.stringInput();
                    Researcher.recommendSimilar(Researcher.getResearcherByName(inputResearcher));
                    break;
                case 7:
                    exit = true;
                    System.out.println("\nGoodBye!");
                    break;
                default:
                    System.out.println("\nInvalid input!");
                    break;
            }
        }
    }
}