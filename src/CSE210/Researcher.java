/**
 * Researcher
 */

package src.CSE210;

import java.util.ArrayList;
import java.util.HashMap;

public class Researcher {
    public static HashMap<String, Researcher> researcherMap = new HashMap<String, Researcher>();
    private String name;
    private String university;
    private String department;
    private String[] interestSet;

    public Researcher(String name, String university, String department, String[] interestSet) {
        this.name = name;
        this.university = university;
        this.department = department;
        this.interestSet = interestSet;
        researcherMap.put(name, this);
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