package testcases;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class readxl {

	public static void main(String[] args) throws IOException {
		
		FileInputStream fis = new FileInputStream(new File("D://test1.xlsx"));
		XSSFWorkbook wb = new XSSFWorkbook(fis);
		XSSFSheet sh = wb.getSheet("Sheet1");
		int rowcount = sh.getPhysicalNumberOfRows();
		int cellcount = sh.getRow(0).getPhysicalNumberOfCells();
		
		for(int i=1; i<rowcount;i++) {
			for(int j=0;j<cellcount;j++) {
				String data = sh.getRow(i).getCell(j).getStringCellValue();
			System.out.print(data+"\t");
			}
			System.out.println("");
		}
	}

}
