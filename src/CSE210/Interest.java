/**
 * Interest
 */

package src.CSE210;

import java.util.ArrayList;
import java.util.HashMap;

import src.CSE210.Researcher;

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

    public static int numOfInterestSharedBy(String interest) {
        return interestMap.get(interest).size();
    }

    public static int getCoOccurTimes(String[] interestsPair) {
        String firstInterest = interestsPair[0].trim();
        String secondInterest = interestsPair[1].trim();
        ArrayList<Researcher> sharedResearchers = (ArrayList<Researcher>)interestMap.get(firstInterest).clone();
        sharedResearchers.retainAll(interestMap.get(secondInterest));
        return sharedResearchers.size();
    }
}