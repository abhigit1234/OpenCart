package com.qa.OpenCart.utilities;

import java.io.FileInputStream;

import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class ReadCustomerDetails {

	static FileInputStream fis;
	
	
	
	@DataProvider(name = "CustomerDetails")
	public Object[][] userDetails() throws Exception {
		
		fis = new FileInputStream(System.getProperty("user.dir")+"\\src\\test\\resources\\OpenCart.xlsx");
		XSSFWorkbook wb = new XSSFWorkbook(fis);
		XSSFSheet sheet = wb.getSheet("Sheet1");
		
		int rowcnt = sheet.getPhysicalNumberOfRows();
		int cellcnt = sheet.getRow(0).getPhysicalNumberOfCells();
		Object[][] data = new Object[rowcnt-1][cellcnt];
		
		for(int i=0;i<rowcnt-1;i++) {
			for(int j=0;j<cellcnt;j++) {
			XSSFCell cells = 	sheet.getRow(i+1).getCell(j);
			DataFormatter df = new DataFormatter();
			data[i][j]  = df.formatCellValue(cells);
			
			}
		}
		fis.close();
		wb.close();
		return data;
	}
}
