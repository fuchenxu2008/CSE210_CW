/**
 * Test
 */

package src.CSE210;

import java.io.InputStream;

/**
 * Class to implement testing
 * @author Chenxu Fu
 */
public class Test {
    /**
     * Auto-run all tests
     * @return boolean if all tests pass
     */
    public static boolean runTest() {
        Util.log("------------- Tests Running in Progress ---------------");
        int testPassed = 0;
        for (int i = 1; i <= 6; i++) {
            try {
                Util.log("# Testing task " + i + " ...");
                testTask(i);
                testPassed += 1;
                Util.log("√ Test of task " + i + " passed!");
            } catch (AssertionError e) {
                Util.log("X Test of task " + i + " failed!");
            } catch (Exception e) {
                Util.log("X Unknown error occured!");
            }
        }
        if (testPassed == 6) {
            Util.log("------------- All 6 Tests Passed ---------------");
        } else {
            Util.log("------------- " + (6 - testPassed) + "/6 Tests Failed! Aborting... ---------------");
            return false;
        }
        return true;
    }
    
    /**
     * Test task by task number
     * @param taskNo task number
     */
    public static void testTask(int taskNo) {
        switch (taskNo) {
            case 1:
                Util.log("* Total number of distinct researchers is " + Researcher.numberOfResearchers + ". (Expected 6553)");
                assert Researcher.numberOfResearchers == 6553;
                break;
            case 2:
                Util.log("* Total number of distinct interests is " + Interest.interestMap.size() + ". (Expected 7277)");
                assert Interest.interestMap.size() == 7277;
                break;
            case 3:
                Util.log("* Given name of 'Debra Siegel Levine':");
                Researcher researcher = Researcher.getResearcherByName("Debra Siegel Levine");
                researcher.printDetails();
                assert researcher.getName().equals("Debra Siegel Levine");
                assert researcher.getUniversity().equals("University of Michigan");
                assert researcher.getDepartment().equals("Department of Psychology");
                assert researcher.getInterest().size() == 10;
                break;
            case 4:
                Util.log("* Given interest of 'Sleep':");
                Util.log("* Getting a total of " + Interest.numOfInterestSharedBy("sleep") + ". (expected 30)");
                assert Interest.numOfInterestSharedBy("sleep") == 30;
                break;
            case 5:
                Util.log("* Given interest of 'Machine Learning, Artificial Intelligence':");
                Util.log("* Getting a total of " + Interest.getCoOccurTimes("artificial intelligence, machine learning") + ". (expected 80)");
                assert Interest.getCoOccurTimes("artificial intelligence, machine learning") == 80;
                break;
            case 6:
                Util.log("* Given name of 'Marisa Perera':");
                String mostRelatedResearcherName = Researcher.getResearcherByName("Marisa Perera").recommendSimilar();
                Util.log("* Most related is " + mostRelatedResearcherName + ". (expected 'Russell B Hanford')");
                assert mostRelatedResearcherName.equals("Russell B Hanford");
                break;
        }
    }
}