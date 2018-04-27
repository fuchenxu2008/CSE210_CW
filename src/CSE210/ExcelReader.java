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
        String[] interestSet = interestStr.split(",");
        // Add researcher to map
        Researcher researcher = new Researcher(user, university, department, interestSet);
        // Handle interests
        for (String interest : interestSet) {
            Interest.addInterest(interest.trim(), researcher);
        }
    }

    public void mapTitles(Row row) {
        for (Cell cell : row) {
            this.colTitles.put(formatter.formatCellValue(cell), cell.getColumnIndex());
        }
    }

    public String getColumn(Row row, String title) {
        int colIndex = this.colTitles.get(title);
        Cell cell = row.getCell(colIndex);
        return formatter.formatCellValue(cell);
    }
    
}