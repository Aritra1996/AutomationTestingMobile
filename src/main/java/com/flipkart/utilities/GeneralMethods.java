package com.flipkart.utilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Iterator;
import java.util.Set;

import org.apache.commons.io.FileUtils;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

public class GeneralMethods {

	HSSFWorkbook ExcelWBook;
	HSSFSheet ExcelWSheet;

	public String captureScreenShot(String testStep, String startTimeString, WebDriver driver) throws IOException {
		long currTime = System.currentTimeMillis();
		File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		String currentTime = String.valueOf(currTime);
		String imagePath = "src/test/resources/reports/" + startTimeString + "/" + "Screenshots/" + testStep
				+ currentTime + ".png";
		FileUtils.copyFile(src, new File(imagePath));

		String imagePathReport = "Screenshots/" + testStep + currentTime + ".png";
		return imagePathReport;
	}

	public void switchToLastTab(WebDriver driver) {
		Set windowsList = driver.getWindowHandles();
		Iterator windowHandlesIterator = windowsList.iterator();
		for (int i = 0; i < windowsList.size(); i++) {
			if (windowHandlesIterator.hasNext()) {
				String handleName = windowHandlesIterator.next().toString();
				driver.switchTo().window(handleName);
			}
		}
	}

	public Object[][] getTableArray(String FilePath, String SheetName) throws Exception {

		String[][] tabArray = null;

		try {

			FileInputStream ExcelFile = new FileInputStream(FilePath);

			// Access the required test data sheet

			ExcelWBook = new HSSFWorkbook(ExcelFile);

			ExcelWSheet = ExcelWBook.getSheet(SheetName);

			int startRow = 1;

			int startCol = 1;

			int ci, cj;

			int totalRows = ExcelWSheet.getLastRowNum();

			// you can write a function as well to get Column count

			int totalCols = 2;

			tabArray = new String[totalRows][totalCols];

			ci = 0;

			for (int i = startRow; i <= totalRows; i++, ci++) {

				cj = 0;

				for (int j = startCol; j <= totalCols; j++, cj++) {

					tabArray[ci][cj] = getCellData(i, j);

					System.out.println(tabArray[ci][cj]);

				}

			}

		}

		catch (FileNotFoundException e) {

			System.out.println("Could not read the Excel sheet");

			e.printStackTrace();

		}

		catch (IOException e) {

			System.out.println("Could not read the Excel sheet");

			e.printStackTrace();

		}

		return (tabArray);

	}

	public String getCellData(int RowNum, int ColNum) throws Exception {

		try {
			HSSFCell Cell = ExcelWSheet.getRow(RowNum).getCell(ColNum);
			String CellData = Cell.getStringCellValue();
			return CellData;
		} catch (Exception e) {
			System.out.println(e.getMessage());
			throw (e);
		}

	}
}
