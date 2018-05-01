/**
 * Main
 */

package src.CSE210;

import java.util.ArrayList;

/**
 * Entry point of the program
 * @author Chenxu Fu
 */
public class Main {
    public static void main(String[] args) {
        System.out.println("* Starting...");
        // Open dataset and read to memory
        ExcelReader excel = new ExcelReader("datasets/Dataset_RG.xlsx");
        Test.runTest();
        Boolean exit = false;
        while (!exit) {
            System.out.println("\n1: calculate the number of distinct researchers in the dataset.\n2: calculate the number of distinct interests in the dataset.\n3: given a researcher’s name, show detailed information about him/her.\n4: given an interest, calculate the number of researchers who have that interest.\n5: given two interests, show the number of times they co-occur.\n6: given a researcher, find similar researchers based on their interests.");
            int action = Utility.numberInput("Input task number to begin: ", 1, 7);
            switch (action) {
                case 1:
                    System.out.println(Researcher.numberOfResearchers);
                    break;
                case 2:
                    System.out.println(Interest.interestMap.size());
                    break;
                case 3:
                    String name = Utility.stringInput("Enter a researcher's name: ");
                    Researcher researcher = Researcher.getResearcherByName(name);
                    if (researcher instanceof Researcher) researcher.printDetails();
                    else System.out.println("* No match found by that name!");
                    break;
                case 4:
                    String interest = Utility.stringInput("Enter an interest: ").toLowerCase();
                    System.out.println(Interest.numOfInterestSharedBy(interest));
                    break;
                case 5:
                    String inputInterests = Utility.stringInput("Enter two interests (delimited by comma): ").toLowerCase();
                    System.out.println(Interest.getCoOccurTimes(inputInterests));
                    break;
                case 6:
                    String inputResearcher = Utility.stringInput("Enter a researcher's name: ");
                    Researcher pickedResearcher = Researcher.getResearcherByName(inputResearcher);
                    if (pickedResearcher instanceof Researcher) pickedResearcher.recommendSimilar();
                    else System.out.println("* No match found by that name!");
                    break;
                case 7:
                    exit = true;
                    System.out.println("\n---- GoodBye! -----");
                    break;
                default:
                    System.out.println("\nInvalid input!");
                    break;
            }
        }
    }
}