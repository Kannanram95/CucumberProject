package org.base;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseClass {
	public static WebDriver driver;
	public static Select s;
	public static Actions ac;
	public static void tolaunchChromebrowser() {
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
	}
	public static void tolaunchedgebrowser() {
		WebDriverManager.edgedriver().setup();
		driver = new EdgeDriver();
	}
	public static void tolaunchffbrowser() {
		WebDriverManager.firefoxdriver().setup();
		driver = new FirefoxDriver();
	}
	public static void tolaunchUrl(String url) {
		driver.get(url);
	}
	public static void toFillTxt(WebElement w,String text) {
		w.sendKeys(text);
	}
	public static void toClick(WebElement w) {
		w.click();
	}
	public static String toGetAttribute(WebElement w,String attributeName) {
		return w.getAttribute(attributeName);
	}
	public static String toGetText(WebElement w) {
		return w.getText();
	}
	public static void toImplicitWait(int t) {
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(t));
	}
	public static String toGetTitle() {
		return driver.getTitle();
	}
	public static void toClose(){
		driver.close();
	}
	public static void toQuit() {
		driver.quit();
	}
	public static void toselectByvalue(WebElement w,String data) {
		s = new Select(w);
		s.selectByValue(data);
	}
	public static void toselectByTxt(WebElement w,String data) {
		s = new Select(w);
		s.selectByVisibleText(data);
	}
	public static void toselectByIndex(WebElement w,int data) {
		s = new Select(w);
		s.selectByIndex(data);
	}
	public static void toScrollDown(WebElement w) {
		JavascriptExecutor js=(JavascriptExecutor)driver;
		js.executeScript("arguments[0].scrollIntoView(true)",w);
	}
	public static void toScrollUp(WebElement w) {
		JavascriptExecutor js=(JavascriptExecutor)driver;
		js.executeScript("arguments[0].scrollIntoView(false)",w);
	}
	public static void toScrollToEnd() {
		JavascriptExecutor js=(JavascriptExecutor)driver;
		js.executeScript("window.scrollBy(0,document.body.scrollHeight)");
	}
	public static void toMouseHover(WebElement w1) {
		ac=new Actions(driver);
		ac.moveToElement(w1).perform();
	}
	public static void toFullScreen() {
		driver.manage().window().fullscreen();
	}
	public static void toMaximize() {
		driver.manage().window().maximize();
	}
	public static void toSwitchtoChildWindow() {
		Set<String> s = driver.getWindowHandles();
		List<String> li = new ArrayList<String>(s);
		driver.switchTo().window(li.get(1));
	}
	public static String toGetCellValue(String fileName,String sheetName,int rowNum,int cellNum) throws IOException {
		File f= new File("C:\\Users\\User\\Desktop\\Kannan\\Programs\\MavenProject\\ExcelSource\\"+fileName+".xlsx");
		FileInputStream fin = new FileInputStream(f);
		Workbook w = new XSSFWorkbook(fin);
		Sheet s = w.getSheet(sheetName);
		Row r = s.getRow(rowNum);
		Cell c = r.getCell(cellNum);
		int cellType = c.getCellType();
		String cellvalue=null;
		if (cellType==1) {
			cellvalue = c.getStringCellValue();
		}
		else {
			if (DateUtil.isCellDateFormatted(c)) {
				Date dateCellValue = c.getDateCellValue();
				SimpleDateFormat sim = new SimpleDateFormat("dd-MM-YY");
				cellvalue = sim.format(dateCellValue);
			}
			else {
				double numvalue = c.getNumericCellValue();
				long l = (long)numvalue;
				cellvalue = String.valueOf(l);
			}
		}
		return cellvalue;
	}
	public static int toGetTotalNoOfRows(String fileName,String sheetName) throws IOException {
		File f= new File("C:\\Users\\User\\Desktop\\Kannan\\Programs\\MavenProject\\ExcelSource\\"+fileName+".xlsx");
		FileInputStream fin = new FileInputStream(f);
		Workbook w = new XSSFWorkbook(fin);
		Sheet s = w.getSheet(sheetName);
		return s.getPhysicalNumberOfRows();
	}
	public static int toGetTotalNoOfCells(String fileName,String sheetName) throws IOException {
		File f= new File("C:\\Users\\User\\Desktop\\Kannan\\Programs\\MavenProject\\ExcelSource\\"+fileName+".xlsx");
		FileInputStream fin = new FileInputStream(f);
		Workbook w = new XSSFWorkbook(fin);
		Sheet s = w.getSheet(sheetName);
		int count=0;
		for (int i = 0; i < s.getPhysicalNumberOfRows(); i++) {
			Row r = s.getRow(i);
			count = count + r.getPhysicalNumberOfCells();
		}
		return count;
	}
	public static void toPrintAllValues(String fileName,String sheetName) throws IOException {
		File f= new File("C:\\Users\\User\\Desktop\\Kannan\\Programs\\MavenProject\\ExcelSource\\"+fileName+".xlsx");
		FileInputStream fin = new FileInputStream(f);
		Workbook w = new XSSFWorkbook(fin);
		Sheet s = w.getSheet(sheetName);
		for (int i = 0; i < s.getPhysicalNumberOfRows(); i++) {
			Row r = s.getRow(i);
			for (int j = 0; j < r.getPhysicalNumberOfCells(); j++) {
				Cell c = r.getCell(j);
				int cellType = c.getCellType();
				String cellvalue=null;
				if (cellType==1) {
					cellvalue = c.getStringCellValue();
				}
				else {
					if (DateUtil.isCellDateFormatted(c)) {
						Date dateCellValue = c.getDateCellValue();
						SimpleDateFormat sim = new SimpleDateFormat("dd-MM-YY");
						cellvalue = sim.format(dateCellValue);
					}
					else {
						double numvalue = c.getNumericCellValue();
						long l = (long)numvalue;
						cellvalue = String.valueOf(l);
					}
				}
				System.out.println(cellvalue);
			}
		}
	}
	public static void toCreateRow(String fileName,String sheetName,int cellnos) throws IOException {
		Scanner y = new Scanner(System.in);
		File f= new File("C:\\Users\\User\\Desktop\\Kannan\\Programs\\MavenProject\\ExcelSource\\"+fileName+".xlsx");
		FileInputStream fin = new FileInputStream(f);
		Workbook w = new XSSFWorkbook(fin);
		Sheet s = w.getSheet(sheetName);
		Row r = s.createRow(s.getPhysicalNumberOfRows());
		System.out.println("Enter the cell values");
		for (int i = 0; i < cellnos; i++) {
			Cell c = r.createCell(i);
			c.setCellValue(y.nextLine());
		}
		FileOutputStream fout = new FileOutputStream(f);
		w.write(fout);
		y.close();
	}
	public static void toCreateSheet(String fileName, String sheetName) throws IOException{
		File f= new File("C:\\Users\\User\\Desktop\\Kannan\\Programs\\MavenProject\\ExcelSource\\"+fileName+".xlsx");
		Workbook w = new XSSFWorkbook();
		w.createSheet(sheetName);
		FileOutputStream fout = new FileOutputStream(f);
		w.write(fout);
	}
	public static String togetCellValue(String fileName, String sheetName, int rownum,int cellnum,String input) throws IOException {
		File f= new File("C:\\Users\\User\\Desktop\\Kannan\\Programs\\MavenProject\\ExcelSource\\"+fileName+".xlsx");
		FileInputStream fin = new FileInputStream(f);
		Workbook w = new XSSFWorkbook(fin);
		Sheet s = w.getSheet(sheetName);
		Row r = s.getRow(rownum);
		Cell c = r.getCell(cellnum);
		int cellType = c.getCellType();
		String cellvalue=null;
		if (cellType==1) {
			cellvalue = c.getStringCellValue();
		}
		else {
			if (DateUtil.isCellDateFormatted(c)) {
				Date dateCellValue = c.getDateCellValue();
				SimpleDateFormat sim = new SimpleDateFormat("dd-MM-YY");
				cellvalue = sim.format(dateCellValue);
			}
			else {
				double numvalue = c.getNumericCellValue();
				long l = (long)numvalue;
				cellvalue = String.valueOf(l);
			}
		}
		return cellvalue;
	}
	public static void toCreateRow(String fileName,String sheetName,String ref,String data) throws IOException {
		File f= new File("C:\\Users\\User\\Desktop\\Kannan\\Programs\\MavenProject\\ExcelSource\\"+fileName+".xlsx");
		FileInputStream fin = new FileInputStream(f);
		Workbook w = new XSSFWorkbook(fin);
		Sheet s = w.getSheet(sheetName);
		Row r = s.createRow(s.getPhysicalNumberOfRows());
		for (int i = 0; i < 2; i++) {
			Cell c = r.createCell(i);
			if (i==0) {
				c.setCellValue(ref);
			}else {
				c.setCellValue(data);
			}
		}
		FileOutputStream fout = new FileOutputStream(f);
		w.write(fout);
	}
}
