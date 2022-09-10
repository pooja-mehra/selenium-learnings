package parameterization;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Hashtable;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class ExcelReader {
	static String fileName = "/Applications/code/seleniumLearnings/files/parameters.xlsx";

	public Object[][] getExcelData(String fileName) throws EncryptedDocumentException, IOException{
		Object[][] data = new Object[2][2];
		ArrayList<String> headers = new ArrayList<String>();
		File f = new File(fileName);
		FileInputStream fi = new FileInputStream(f);
		
		Workbook workbook = WorkbookFactory.create(fi);
		
		Sheet sheet0 = workbook.getSheetAt(0);
		
		int rowCount = sheet0.getLastRowNum();
		
		for(int i=0;  i< rowCount; i++) {
		
			int colCount= sheet0.getRow(i+1).getLastCellNum();
			System.out.println(colCount);
			for( int j =0; j< colCount; j++){
				Cell cell = sheet0.getRow(i+1).getCell(j);
				
				switch(cell.getCellType()) {
					case STRING:
						data[i][j]= cell.getStringCellValue();
						System.out.print(cell.getStringCellValue());
						System.out.print( "\t");
						break;
					case NUMERIC:
						System.out.print(cell.getNumericCellValue());
						System.out.print( "\t");
						break;
					default:
						break;
				}
			}
			System.out.println();
		}
		
		/*Row row0 = sheet0.getRow(0);
		Cell cellA = row0.getCell(0);
		
		System.out.println(cellA);*/
		
		fi.close();
		return data;
	}

}
