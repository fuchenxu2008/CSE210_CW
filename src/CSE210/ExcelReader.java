/**
 * ExcelReader
 */

package src.CSE210;

import java.io.File;

import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.WorkbookFactory;
// import org.apache.poi.ss.util.CellReference;
// import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Cell;
// import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
// import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.util.Map;
import java.util.ArrayList;
import java.util.HashMap;

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
            else {
                // Get researcher info from row
                String user = getColumn(row, "User");
                String university = getColumn(row, "University");
                String department = getColumn(row, "Department");
                String topics = getColumn(row, "Topics");
                String skills = getColumn(row, "Skills");
                String interestStr = "";
                // Concate topics and skills properly for splitting
                if (topics.isEmpty() || skills.isEmpty()) interestStr = (topics + skills);
                else interestStr = topics + "," + skills;
                String[] interestSet = interestStr.split(",");
                // Add researcher to map
                Researcher researcher = new Researcher(user, university, department, interestSet);
                // Handle interests
                for (String interest : interestSet) {
                    interest = interest.trim();
                    if (!interest.isEmpty()) {
                        ArrayList<Researcher> interestedResearchersList = new ArrayList<>();
                        if (Interest.interestMap.containsKey(interest)) {
                            interestedResearchersList = Interest.interestMap.get(interest);
                        }
                        interestedResearchersList.add(researcher);
                        Interest.interestMap.put(interest, interestedResearchersList);
                    }
                }
            }
        }
    }


    public void mapTitles(Row row) {
        for (Cell cell : row) {
            this.colTitles.put(formatter.formatCellValue(cell), cell.getColumnIndex());
        }
    }

    // public void getColumn(String title) {
    //     int colIndex = this.colTitles.get(title);
    //     for (Row row : this.sheet) {
    //         Cell cell = row.getCell(colIndex);
    //         System.out.println(formatter.formatCellValue(cell));
    //     }
    // }

    public String getColumn(Row row, String title) {
        int colIndex = this.colTitles.get(title);
        Cell cell = row.getCell(colIndex);
        return formatter.formatCellValue(cell);
    }

    // public void readCell() {
    //     Row row = this.sheet.getRow(2);
    //     Cell cell = row.getCell(12);
    //     String val = formatter.formatCellValue(cell);
    //     System.out.println(val + 1);
    // }
    
}