package testcases;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class writexl {

	public static void main(String[] args) throws IOException {
		
		XSSFWorkbook wb = new XSSFWorkbook();
		XSSFSheet sh = wb.createSheet("Sheet1");

		for (int row = 0; row < 4; row++) {
			XSSFRow raw = sh.createRow(row);
			for (int col = 0; col < 4; col++) {
				XSSFCell cell = raw.createCell(col);
				cell.setCellValue("Row " + (row+1) + " " + "Col "+ (col+1));
			}
		}
		FileOutputStream fos = new FileOutputStream(new File("number1.xlsx"));
		wb.write(fos);
		fos.close();
	}
}
