/**
 * Interest
 */

package src.CSE210;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

import src.CSE210.Researcher;
import src.CSE210.Utility;

public class Interest {
    public static HashMap<String, ArrayList<Researcher>> interestMap = new HashMap<>();

    public static void addInterest(String interest, Researcher researcher) {
        if (!interest.isEmpty()) {
            ArrayList<Researcher> interestedResearchersList = new ArrayList<>();
            if (interestMap.containsKey(interest)) {
                interestedResearchersList = interestMap.get(interest);
            }
            interestedResearchersList.add(researcher);
            interestMap.put(interest, interestedResearchersList);
        }
    }

    public static void interestSharedBy(String interest) {
        if (interestMap.containsKey(interest)) {
            ArrayList<Researcher> interestedResearchersList = interestMap.get(interest);
            for (Researcher researcher : interestedResearchersList) {
                System.out.println(researcher.getName());
            }
        } else {
            System.out.println("No researcher info on that bizzare interest.");
        }
    }

    public static int numOfInterestSharedBy(String interest) {
        int size = 0;
        if (interestMap.containsKey(interest)) {
            size = interestMap.get(interest).size();
        } else {
            System.out.println("No info on that bizzare interest.");
        }
        return size;
    }

    public static int getCoOccurTimes(String[] interestsPair) {
        String firstInterest = interestsPair[0].trim();
        String secondInterest = interestsPair[1].trim();
        ArrayList<Researcher> sharedResearchers = (ArrayList<Researcher>)interestMap.get(firstInterest).clone();
        // ArrayList<Researcher> sharedResearchers = interestMap.get(firstInterest);
        sharedResearchers.retainAll(interestMap.get(secondInterest));
        return sharedResearchers.size();
    }
}