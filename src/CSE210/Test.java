/**
 * Test
 */

package src.CSE210;

import src.CSE210.Researcher;

public class Test {
    public static void runTest() {
        // describe("", () => {
        //     it("", () => {
        //         expect().toBe();
        //     });
        // });
        System.out.println(Researcher.numberOfResearchers);
        System.out.println(Interest.interestMap.size());
        Researcher.getResearcherByName("Debra Siegel Levine").printDetails();
        System.out.println(Interest.numOfInterestSharedBy("sleep"));
        System.out.println(Interest.getCoOccurTimes(("artificial intelligence, machine learning").split(",")));
        System.out.println(Researcher.getResearcherByName("Marisa Perera").cosineSimilarityWith(Researcher.getResearcherByName("Binhan Ngoc Nguyen")));
    }
    
}