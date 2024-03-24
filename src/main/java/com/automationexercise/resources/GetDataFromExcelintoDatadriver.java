package com.automationexercise.resources;

import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.IOException;

public class GetDataFromExcelintoDatadriver {


    DataFormatter formatter = new DataFormatter();
    FileInputStream fis;
    XSSFWorkbook workbook;
    public Object[][] getData(String sheetName, String pathFile)  {
        try {
            fis = new FileInputStream(pathFile);
            workbook = new XSSFWorkbook(fis);
        } catch (IOException e) {
            e.printStackTrace();
        }

        XSSFSheet sheet=workbook.getSheet(sheetName);
        int rowCount = sheet.getPhysicalNumberOfRows();
        XSSFRow row = sheet.getRow(0);
        int colcount = row.getLastCellNum();
        Object data[][] = new Object[rowCount-1][colcount];
        for (int i = 0; i < rowCount-1; i++) {
            row= sheet.getRow(i+1);
            for (int j = 0; j < colcount; j++) {
                XSSFCell cell = row.getCell(j);
                data[i][j]= formatter.formatCellValue(cell);
            }
        }
        return data;
    }




}
