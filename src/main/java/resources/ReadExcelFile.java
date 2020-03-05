package resources;

import java.io.FileInputStream;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;

public class ReadExcelFile {
	private HSSFSheet ws = null;
	public String filelocation;
	public FileInputStream ipstr = null;
	private HSSFWorkbook wb = null;

	public ReadExcelFile(String filelocation, String wsName) {
		this.filelocation = filelocation;
		try {
			ipstr = new FileInputStream(filelocation);
			wb = new HSSFWorkbook(ipstr);
			int sheetIndex = wb.getSheetIndex(wsName);
			ws = wb.getSheetAt(sheetIndex);
			ipstr.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/*
	 * To retrieve test data from test case data sheets. noOfColumnstToIgnore
	 * Denotes Number of Columns from Last which should be skipped.
	 */
	public Object[][] retrieveTestData(int noOfColumnstToIgnore) {
		// Removal of Empty Rows and Columns from Sheet
		removeEmptyRows();
		int rowNum = retrieveNoOfRows();
		int colNum = retrieveNoOfCols();
		System.out.println("row num "+rowNum+ " col num "+colNum );
		Object data[][] = new Object[rowNum - 1][colNum - noOfColumnstToIgnore];
		
		for (int i = 0; i < rowNum - 1; i++) {
			// HSSFRow row = ws.getRow(0);
			HSSFRow row = ws.getRow(i + 1);
			for (int j = 0; j < colNum - noOfColumnstToIgnore; j++) {
				if (row == null) {
					data[i][j] = "";
				} else {
					HSSFCell cell = row.getCell(j);
					if (cell == null) {
						data[i][j] = "";
					} else {
						cell.setCellType(CellType.STRING);
						String value = cellToString(cell);
						data[i][j] = value;
						System.out.println("The datum value is "+data[i][j]);
					}
				}
			}
		}
		return data;
	}

	// To Check whether Row is Empty or Not
	public Boolean isRowEmpty(HSSFRow row) {
		for (int c = row.getFirstCellNum(); c < row.getLastCellNum(); c++) {
			Cell cell = row.getCell(c);
			if (cell != null && cell.getCellType() != CellType.BLANK)
				return false;
		}
		return true;
	}

	// Removal of Empty Rows from the Sheet
	public void removeEmptyRows() {
		int rowNum = retrieveNoOfRows();
		for (int i = 0; i < rowNum - 1; i++) {
			HSSFRow row = ws.getRow(i + 1);
			if (isRowEmpty(row)) {
				ws.removeRow(row);
			}

		}
	}

	// To retrieve No Of Rows from .xls file's sheets.
	public int retrieveNoOfRows() {
		int rowCount = ws.getLastRowNum() + 1;
		return rowCount;
	}

	// To retrieve No Of Columns from .xls file's sheets.
	public int retrieveNoOfCols() {
		int colCount = ws.getRow(0).getLastCellNum();

		/*
		 * Rest of Below Code is Written to Exclude any Blank Columns after Test
		 * Data Ends in Count. It works best for only that Case. If any Blank
		 * Column in Between the Test Data, it should be rectified manually.
		 */
		int actualColumns = 0;
		HSSFRow row = ws.getRow(0);
		for (int i = 0; i < colCount; i++) {
			HSSFCell cell = row.getCell(i, Row.MissingCellPolicy.RETURN_BLANK_AS_NULL);
			if (cell != null)
				actualColumns++;
		}
		return actualColumns;
	}

	public static String cellToString(HSSFCell cell) {
		CellType type;
		Object result;

		type = cell.getCellType();
		switch (type) {
		case NUMERIC:
			result = cell.getNumericCellValue();
			break;
		case STRING:
			result = cell.getStringCellValue();
			break;
		default:
			throw new RuntimeException("Unsupported cell.");
		}

		return result.toString().trim();
	}
}
