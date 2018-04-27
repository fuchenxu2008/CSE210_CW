/**
 * Researcher
 */

package src.CSE210;

import java.util.ArrayList;
import java.util.HashMap;

public class Researcher {
    public static HashMap<String, ArrayList<Researcher>> researcherMap = new HashMap<>();
    public static int numberOfResearchers = 0;
    private String name;
    private String university;
    private String department;
    private String[] interestSet;

    public Researcher(String name, String university, String department, String[] interestSet) {
        this.name = name;
        this.university = university;
        this.department = department;
        this.interestSet = interestSet;
        // Check for name duplicated researcher
        ArrayList<Researcher> ResearchersWithThatName = new ArrayList<>();
        if (researcherMap.containsKey(name)) {
            /*
                TODO: Judge if same person
            */
            ResearchersWithThatName = researcherMap.get(name);
        } 
        ResearchersWithThatName.add(this);
        researcherMap.put(name, ResearchersWithThatName);
        numberOfResearchers += 1;
    }

    public static ArrayList<Researcher> getResearchersByName(String name) {
        return researcherMap.get(name);
    }

    public void printDetails() {
        String interests = String.join(",", this.interestSet);
        System.out.printf("\nUser: %s\n\nUniversity: %s\nDepartment: %s\nInterests: %s\n", this.name, this.university, this.department, interests);
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
    
    public String[] getInterestSet() {
        return this.interestSet;
    }
}