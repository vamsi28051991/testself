package config;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class excelProgram {
	private static FileInputStream fis;
    private static FileOutputStream fileOut;
    private static XSSFWorkbook wb;
    private static XSSFSheet sh;
    private static XSSFCell cell;
    private static XSSFRow row;
    
    public static int getExcelRowCount(String strFilepath, String strSheetname) throws IOException{
    	XSSFWorkbook ipWorkbook = new XSSFWorkbook(strFilepath);
		XSSFSheet ipSheet = ipWorkbook.getSheet(strSheetname);
		int rowCount = ipSheet.getLastRowNum()-ipSheet.getFirstRowNum();
		ipWorkbook.close();
		return rowCount;
    }
	
	public static String getExcelStringData(String strFilepath, String strSheetname, int rowNumber, int colNumber) throws IOException{
		XSSFWorkbook ipWorkbook = new XSSFWorkbook(strFilepath);
		XSSFSheet ipSheet = ipWorkbook.getSheet(strSheetname);
		XSSFCell cell = ipSheet.getRow(rowNumber).getCell(colNumber);
		String retVal = cell.getStringCellValue();
		ipWorkbook.close();
		return retVal;
	}
	public static void setExcelData(String strFilepath, String strSheetname, int rowNumber, int colNumber, String strData) throws IOException{
		File f = new File(strFilepath);
		if(!f.exists()){
			f.createNewFile();
		}
		fis = new FileInputStream(strFilepath);
		wb = new XSSFWorkbook(fis);
		sh = wb.getSheet(strSheetname);
		if(sh == null){
			sh = wb.createSheet(strSheetname);
		}
		row = sh.getRow(rowNumber);
		if(row == null){
			row = sh.createRow(rowNumber);
		}
		cell = row.getCell(colNumber);
		if(cell != null){
			cell.setCellValue(strData);
		}
		else{
			cell = row.createCell(colNumber);
			cell.setCellValue(strData);
		}
		fileOut = new FileOutputStream(strFilepath);
		wb.write(fileOut);
		fileOut.flush();
		fileOut.close();
	}
	@SuppressWarnings("deprecation")
	public static void fillColour(String strFilepath, String strSheetname, int rowNumber, int colNumber, String strData) throws IOException{
		fis = new FileInputStream(strFilepath);
		wb = new XSSFWorkbook(fis);
		sh = wb.createSheet(strSheetname);
		row = sh.getRow(rowNumber);
		cell = row.getCell(colNumber);
		CellStyle style = wb.createCellStyle();
		style.setFillForegroundColor(IndexedColors.RED.getIndex());
		style.setFillPattern(CellStyle.SOLID_FOREGROUND);
		cell.setCellStyle(style);
		fileOut = new FileOutputStream(strFilepath);
		wb.write(fileOut);
		fileOut.flush();
		fileOut.close();
	}

}
