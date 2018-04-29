/**
 * ExcelReader
 */

package src.CSE210;

import java.io.File;

import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

import java.util.Map;
import java.util.Set;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

public class ExcelReader {
    // Formatter to get Excel cell content regardless of type
    public static DataFormatter formatter = new DataFormatter();
    // The entire Excel sheet
    private Sheet sheet;
    // Map the column titles with their column index
    private Map<String, Integer> colTitles = new HashMap<String, Integer>();

    // Constructor
    public ExcelReader(String filePath) {
        try {
            Workbook wb = WorkbookFactory.create(new File(filePath));
            this.sheet = wb.getSheetAt(0);
            this.initialize();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    // Traverse and establish two Maps for Researchers and Interests
    public void initialize() {
        for (Row row : this.sheet) {
            // Map the title row
            if (row.getRowNum() == 0) this.mapTitles(row);
            // Add records for the content rows
            else this.addRecord(row);
        }
    }

    public void addRecord(Row row) {
        // Get researcher info from row
        String user = getColumn(row, "User").trim();
        String university = getColumn(row, "University").trim();
        String department = getColumn(row, "Department").trim();
        String topics = getColumn(row, "Topics").trim();
        String skills = getColumn(row, "Skills").trim();
        String interestStr = "";
        // Concate topics and skills properly for splitting
        if (topics.isEmpty() || skills.isEmpty())
            interestStr = (topics + skills);
        else
            interestStr = topics + "," + skills;
        HashSet<String> interestSet = new HashSet<>();
        // Handle interests
        for (String interest : interestStr.split(",")) {
            interestSet.add(interest.trim());
        }
        // Add researcher to map
        Researcher researcher = new Researcher(user, university, department, interestSet);
        // Use set to eliminate duplicate interests
        for (String interest : interestSet) {
            Interest.addInterest(interest.toLowerCase(), researcher);
        }
    }

    public void mapTitles(Row row) {
        for (Cell cell : row) {
            // Set up column title => column index mapping relation
            this.colTitles.put(formatter.formatCellValue(cell), cell.getColumnIndex());
        }
    }

    // Get designated column in row by column title
    public String getColumn(Row row, String title) {
        int colIndex = this.colTitles.get(title);
        Cell cell = row.getCell(colIndex);
        return formatter.formatCellValue(cell);
    }
    
}