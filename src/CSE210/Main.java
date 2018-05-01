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
        Util.log("* Initializing...");
        // Open dataset and read to memory
        ExcelReader excel = new ExcelReader("datasets/Dataset_RG.xlsx");
        if (!Test.runTest()) return;
        Boolean exit = false;
        while (!exit) {
            Util.log("\n-------Task List--------\n1: Calculate the number of distinct researchers in the dataset.\n2: Calculate the number of distinct interests in the dataset.\n3: Given a researcher’s name, show detailed information about him/her.\n4: Given an interest, calculate the number of researchers who have that interest.\n5: Given two interests, show the number of times they co-occur.\n6: Given a researcher, find similar researchers based on their interests.\n7. Quit");
            int action = Util.numberInput("Input task number to begin: ", 1, 7);
            switch (action) {
                case 1:
                    Util.log("Total number of distinct researchers is " + Researcher.numberOfResearchers);
                    break;
                case 2:
                    Util.log("Total number of distinct interests is " + Interest.interestMap.size());
                    break;
                case 3:
                    String name = Util.stringInput("Enter a researcher's name: ");
                    Researcher researcher = Researcher.getResearcherByName(name);
                    if (researcher instanceof Researcher) researcher.printDetails();
                    else Util.log("* No match found by that name!");
                    break;
                case 4:
                    String interest = Util.stringInput("Enter an interest: ").toLowerCase();
                    Util.log(Interest.numOfInterestSharedBy(interest) + " researchers found with that interest.");
                    break;
                case 5:
                    String inputInterests = Util.stringInput("Enter two interests (delimited by comma): ").toLowerCase();
                    Util.log(Interest.getCoOccurTimes(inputInterests) +" researchers have those two interests.");
                    break;
                case 6:
                    String inputResearcher = Util.stringInput("Enter a researcher's name: ");
                    Researcher pickedResearcher = Researcher.getResearcherByName(inputResearcher);
                    if (pickedResearcher instanceof Researcher) pickedResearcher.recommendSimilar();
                    else Util.log("* No match found by that name!");
                    break;
                case 7:
                    exit = true;
                    Util.log("\n---- GoodBye! -----");
                    break;
                default:
                    Util.log("\nInvalid input!");
                    break;
            }
        }
    }
}