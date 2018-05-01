/**
 * ExcelReader
 */

package src.CSE210;

import java.io.File;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
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

/**
 * ExcelReader uses POI to read excel file
 * It read the data into memory
 * 
 * @author Chenxu Fu
 */
public class ExcelReader {
    // Formatter to get Excel cell content regardless of type
    private static DataFormatter formatter = new DataFormatter();
    // The entire Excel sheet
    private Sheet sheet;
    // Map the column titles with their column index
    private Map<String, Integer> colTitles = new HashMap<String, Integer>();

    /**
     * Constructs and initialize a Excel Workbook from given file path
     * @param filePath file path of the excel file to read
     */
    public ExcelReader(String filePath) {
        try {
            Workbook wb = WorkbookFactory.create(new File(filePath));
            this.sheet = wb.getSheetAt(0);
            this.initialize();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    /** 
     * Traverse and establish two Maps for Researchers and Interests
     */
    private void initialize() {
        System.out.println("* Initializing...");
        for (Row row : this.sheet) {
            // Map the title row
            if (row.getRowNum() == 0) this.mapTitles(row);
            // Add records for the content rows
            else this.addRecord(row);
        }
    }

    /**
     * Parse the row content and read relevant information into memory, adding records of researchers and interests
     * @param row An Excel row containing record of a researcher  
     */
    private void addRecord(Row row) {
        // Get researcher info from row
        String user = getColumn(row, "User").trim();
        String university = getColumn(row, "University").trim();
        String department = getColumn(row, "Department").trim();
        String topics = getColumn(row, "Topics").trim();
        String skills = getColumn(row, "Skills").trim();

        HashSet<String> interestSet = new HashSet<>();
        if (!(topics.isEmpty() && skills.isEmpty())) {
            String interestStr = "";
            // Concate topics and skills properly for splitting
            if (topics.isEmpty() || skills.isEmpty())
                interestStr = (topics + skills);
            else
                interestStr = topics + "," + skills;
            // Handle interests
            for (String interest : interestStr.split(",")) {
                interestSet.add(interest.trim());
            }
        }
        // Add researcher to map
        Researcher researcher = new Researcher(user, university, department, interestSet);
        // Use set to eliminate duplicate interests
        for (String interest : interestSet) {
            Interest.addInterest(interest.toLowerCase(), researcher);
        }
    }

    /**
     * Create mapping relationship from column title to column index
     * @param row The title row of the Excel sheet
     */
    private void mapTitles(Row row) {
        for (Cell cell : row) {
            this.colTitles.put(formatter.formatCellValue(cell), cell.getColumnIndex());
        }
    }

    /**
     * Get designated cell content in row by column title
     * @param row row of the cell
     * @param title column title of the cell
     * @return the content of the desired cell
     */
    private String getColumn(Row row, String title) {
        int colIndex = this.colTitles.get(title);
        Cell cell = row.getCell(colIndex);
        return formatter.formatCellValue(cell);
    }
    
}