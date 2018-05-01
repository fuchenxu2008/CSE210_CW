/**
 * Researcher
 */

package src.CSE210;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

import src.CSE210.Utility;

/**
 * Class to instantiate Researcher object and group researcher related functions
 * @author Chenxu Fu
 */
public class Researcher {
    public static HashMap<String, ArrayList<Researcher>> researcherMap = new HashMap<>();
    public static int numberOfResearchers = 0;
    private String name;
    private String university;
    private String department;
    private HashSet<String> interestSet;

    /**
     * Constructs and instantiate a new Researcher and add it to the researcherMap for query
     * @param name researcher name
     * @param university researcher university
     * @param department researcher department
     * @param interestSet researcher interest contained in a HashSet
     */
    public Researcher(String name, String university, String department, HashSet<String> interestSet) {
        this.name = name;
        this.university = university;
        this.department = department;
        this.interestSet = interestSet;
        this.addResearcher();
    }

    /**
     * First check if there are already researcher(s) duplicated with newly constructed researcher's name
     * And if duplicate, determine whether they are refering to the same researcher
     * Then add it to the researcherMap for storage
     */
    private void addResearcher() {
        // Check for name duplicated researcher
        ArrayList<Researcher> ResearchersWithThatName = new ArrayList<>();
        Boolean newEntry = true;
        Boolean duplicateName = false;
        if (researcherMap.containsKey(this.getName())) {
            duplicateName = true;
            for (Researcher existingResearcher : researcherMap.get(this.getName())) {
                if (existingResearcher.getUniversity().equals(this.getUniversity()) && existingResearcher.getDepartment().equals(this.getDepartment())) {
                    newEntry = false; // Duplicate but same guy
                    existingResearcher.mergeInterest(this.interestSet);
                }
            }
        }
        // No duplicate or different guy
        if (newEntry) {
            if (duplicateName) {
                // Duplicate but different guy
                ResearchersWithThatName = researcherMap.get(this.getName());
            }
            ResearchersWithThatName.add(this);
            researcherMap.put(this.getName(), ResearchersWithThatName);
            numberOfResearchers += 1;
        }
    }

    /**
     * Search the researcherMap for a researcher with given name
     * @param name name of the researcher to query
     * @return One researcher object with given name or null if not found
     */
    public static Researcher getResearcherByName(String name) {
        Researcher desiredResearcher = null;
        if (researcherMap.containsKey(name)) {
            ArrayList<Researcher> researchers = researcherMap.get(name);
            if (researchers.size() > 1) {
                System.out.println("\n* Attention: There are multiple records with that name!");
                for (int i = 0; i < researchers.size(); i++) {
                    System.out.printf("%d", i + 1);
                    researchers.get(i).printDetails();
                }
                int choice = Utility.numberInput("Please choose the desired one: ", 1, researchers.size());
                desiredResearcher = researchers.get(choice - 1);
            } else {
                desiredResearcher = researchers.get(0);
            }
        }
        return desiredResearcher;
    }

    /**
     * Compute cosine similarity of two researchers based on their interests
     * @param comparedResearcher the researcher object that current picked researcher is comparing with
     * @return The result of Cosine Similarity algorithm
     */
    public double cosineSimilarityWith(Researcher comparedResearcher) {
        HashSet<String> commonInterests = new HashSet<>(this.getInterest());
        commonInterests.retainAll(comparedResearcher.getInterest());
        if (commonInterests.size() == 0) {
            return 0;
        } else {
            HashSet<String> unionInterests = new HashSet<>(this.getInterest());
            unionInterests.addAll(comparedResearcher.getInterest());
            int product = 0;
            double den1 = 0, den2 = 0;
            for (String unionInterest : unionInterests) {
                int pickedHasInterest = this.getInterest().contains(unionInterest) ? 1 : 0;
                int comparedHasInterest = comparedResearcher.getInterest().contains(unionInterest) ? 1 : 0;
                product += pickedHasInterest * comparedHasInterest;
                den1 += Math.pow(pickedHasInterest, 2);
                den2 += Math.pow(comparedHasInterest, 2);
            }
            return product / (Math.sqrt(den1) * Math.sqrt(den2));
        }
    }

    /**
     * Iterate all other researchers to compute cosine similarity with current researcher
     * Sort the result and give top 5 recommendations
     */
    public void recommendSimilar() {
        if (this.getInterest().size() == 0) {
            System.out.println("This researcher has no interest, can't find similar researchers!");
            return;
        }
        HashMap<Researcher, Double> similarityRank = new HashMap<>();
        for (ArrayList<Researcher> researchersWithName: researcherMap.values()) {
            for (Researcher comparedResearcher : researchersWithName) {
                if (!this.equals(comparedResearcher)) {
                    double similarity = this.cosineSimilarityWith(comparedResearcher);
                    if (similarity > 0) {
                        similarityRank.put(comparedResearcher, similarity);   
                    }
                }
            }
        }
        ArrayList<Map.Entry<Researcher, Double>> similarityRankList = new ArrayList<>(similarityRank.entrySet());
        Collections.sort(similarityRankList, new Comparator<Map.Entry<Researcher, Double>>() {
            public int compare(Map.Entry<Researcher, Double> o1, Map.Entry<Researcher, Double> o2) {
                //o1 to o2 Ascending   o2 to o1 Descending
                return o2.getValue().compareTo(o1.getValue());
            }
        });
        int NumOfRecommendation = similarityRankList.size() >= 5 ? 5 : similarityRankList.size();
        for (int i = 0; i < NumOfRecommendation; i++) {
            System.out.printf("%d. %s: %f\n", i + 1, similarityRankList.get(i).getKey().getName(), similarityRankList.get(i).getValue());
        }
    }

    /**
     * Print the detailed information of a researcher
     */
    public void printDetails() {
        System.out.printf("\n------- User Info ---------\nUser: %s\nUniversity: %s\nDepartment: %s\nInterests: %s\n-----------------------\n", this.getName(), this.getUniversity(), this.getDepartment(), this.getInterest());
    }
    
    /**
     * @return researcher's name
     */
    public String getName() {
        return this.name;
    }

    /**
     * @return researcher's university
     */
    public String getUniversity() {
        return this.university;
    }

    /**
     * @return researcher's department
     */
    public String getDepartment() {
        return this.department;
    }
    
    /**
     * @return researcher's interests set
     */
    public HashSet<String> getInterest() {
        return this.interestSet;
    }

    /**
     * Supplement of existing researcher's interest set
     * @param newInterestSet new interestSet to merge
     */
    private void mergeInterest(HashSet<String> newInterestSet) {
        this.interestSet.addAll(newInterestSet);
    }
}