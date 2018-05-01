/**
 * Interest
 */

package src.CSE210;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Class to group interest related function
 * @author Chenxu Fu
 */
public class Interest {
    public static HashMap<String, ArrayList<Researcher>> interestMap = new HashMap<>();

    /**
     * Add to interestMap with interest name as key and list of Researcher objects as value
     * @param interest interest name
     * @param researcher Researcher object who has that interest
     */
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

    /**
     * Get the total number of researchers who have the given interest
     * @param interest given interest name
     * @return the total number of researchers
     */
    public static int numOfInterestSharedBy(String interest) {
        int size = 0;
        if (interestMap.containsKey(interest)) {
            size = interestMap.get(interest).size();
        } else {
            Util.log("* No info on that bizzare interest.");
        }
        return size;
    }

    /**
     * Get the number of circumstances where the given two interests co-exist in the same researcher
     * @param twoInterests two interests from user input
     * @return the total number of co-occur times
     */
    public static int getCoOccurTimes(String twoInterests) {
        String[] interestPair = twoInterests.split(",");
        if (interestPair.length != 2) {
            Util.log("* Please input two interests!");
            return 0;
        }
        String firstInterest = interestPair[0].trim();
        String secondInterest = interestPair[1].trim();
        if (!interestMap.containsKey(firstInterest) || !interestMap.containsKey(secondInterest)) {
            Util.log("* Entered interest(s) does not exist!");
            return 0;
        }
        ArrayList<Researcher> sharedResearchers = new ArrayList<>(interestMap.get(firstInterest));
        sharedResearchers.retainAll(interestMap.get(secondInterest));
        return sharedResearchers.size();
    }
}