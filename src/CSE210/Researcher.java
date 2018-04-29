/**
 * Researcher
 */

package src.CSE210;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

import src.CSE210.Utility;

public class Researcher {
    public static HashMap<String, ArrayList<Researcher>> researcherMap = new HashMap<>();
    public static int numberOfResearchers = 0;
    private String name;
    private String university;
    private String department;
    private HashSet<String> interestSet;

    public Researcher(String name, String university, String department, HashSet<String> interestSet) {
        this.name = name;
        this.university = university;
        this.department = department;
        this.interestSet = interestSet;
        this.addResearcher();
    }

    public void addResearcher() {
        // Check for name duplicated researcher
        ArrayList<Researcher> ResearchersWithThatName = new ArrayList<>();
        Boolean newEntry = true;
        Boolean duplicateName = false;
        if (researcherMap.containsKey(this.getName())) {
            duplicateName = true;
            for (Researcher existingResearcher : researcherMap.get(this.getName())) {
                if (existingResearcher.getUniversity().equals(this.getUniversity()) && existingResearcher.getDepartment().equals(this.getDepartment())) {
                    newEntry = false; // Duplicate but same guy
                    for (String interest : this.interestSet) {
                        existingResearcher.mergeInterest(interest);;
                    }
                }
            }
        }
        // No duplicate or different guy
        if (newEntry) {
            if (duplicateName) {
                // Duplicate but different guy
                System.out.println(this.getName());
                ResearchersWithThatName = researcherMap.get(this.getName());
            }
            ResearchersWithThatName.add(this);
            researcherMap.put(this.getName(), ResearchersWithThatName);
            numberOfResearchers += 1;
        }
    }

    public static Researcher getResearcherByName(String name) {
        Researcher desiredResearcher = null;
        if (researcherMap.containsKey(name)) {
            ArrayList<Researcher> researchers = researcherMap.get(name);
            if (researchers.size() > 1) {
                System.out.println("\n!Attention: There are multiple records with that name!");
                for (int i = 0; i < researchers.size(); i++) {
                    System.out.printf("%d", i + 1);
                    researchers.get(i).printDetails();
                }
                System.out.print("->Please choose the desired one: ");
                int choice = Utility.numberInput(1, researchers.size());
                desiredResearcher = researchers.get(choice - 1);
            } else {
                desiredResearcher = researchers.get(0);
            }
        }
        return desiredResearcher;
    }

    public double cosineSimilarityWith(Researcher comparedResearcher) {
        HashSet<String> commonInterests = (HashSet<String>)this.getInterest().clone();
        commonInterests.retainAll(comparedResearcher.getInterest());
        // System.out.println("common interest size" + commonInterests.size());
        if (commonInterests.size() == 0) {
            return 0;
        } else {
            HashSet<String> unionInterests = (HashSet<String>)this.getInterest().clone();
            unionInterests.addAll(comparedResearcher.getInterest());
            // System.out.println("union interest size" + unionInterests.size());
            int product = 0;
            double den1 = 0, den2 = 0;
            for (String unionInterest : unionInterests) {
                int pickedHasInterest = this.getInterest().contains(unionInterest) ? 1 : 0;
                int comparedHasInterest = comparedResearcher.getInterest().contains(unionInterest) ? 1 : 0;
                product += pickedHasInterest * comparedHasInterest;
                den1 += Math.pow(pickedHasInterest, 2);
                den2 += Math.pow(comparedHasInterest, 2);
                // System.out.println("picked: " + pickedHasInterest + " compared: " + comparedHasInterest);
            }
            // System.out.println("Product = " + product);
            // System.out.println("den1 = " + den1 + "den2 = " + den2);
            return product / (Math.sqrt(den1) * Math.sqrt(den2));
        }
    }

    public static void recommendSimilar(Researcher pickedResearcher) {
        ArrayList<Double> similarityRank = new ArrayList<>();
        for (ArrayList<Researcher> researchersWithName: researcherMap.values()) {
            for (Researcher comparedResearcher : researchersWithName) {
                if (!pickedResearcher.equals(comparedResearcher)) {
                    double similarity = pickedResearcher.cosineSimilarityWith(comparedResearcher);
                    if (similarity > 0) {
                        System.out.println(similarity + comparedResearcher.getName());
                    }
                }
            }
        }
    }

    public void printDetails() {
        System.out.printf("\n-----------------------\nUser: %s\nUniversity: %s\nDepartment: %s\nInterests: %s\n-----------------------\n", this.getName(), this.getUniversity(), this.getDepartment(), this.getInterest());
    }
    
    public String getName() {
        return this.name;
    }

    public String getUniversity() {
        return this.university;
    }

    public String getDepartment() {
        return this.department;
    }
    
    public HashSet<String> getInterest() {
        return this.interestSet;
    }

    public void mergeInterest(String newInterest) {
        this.interestSet.add(newInterest);
    }
}