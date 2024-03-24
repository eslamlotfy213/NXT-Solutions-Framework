package com.automationexercise.resources;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.util.NumberToTextConverter;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class DataDriven {

    XSSFWorkbook workbook;
    FileInputStream fis;
    public ArrayList<String> getData(String testcaseName,String headertestCases ,String sheetName, String pathFile)
    {
        ArrayList<String> a=new ArrayList<String>();
        try {
            fis = new FileInputStream(pathFile);
            workbook = new XSSFWorkbook(fis);
        }catch (IOException e) {
            e.printStackTrace();
            return null; // or throw a custom exception
        }

        int sheets=workbook.getNumberOfSheets();
        for(int i=0;i<sheets;i++)
        {
            if(workbook.getSheetName(i).equalsIgnoreCase(sheetName))
            {
                XSSFSheet sheet=workbook.getSheetAt(i);

                Iterator<Row>  rows= sheet.iterator();// sheet is collection of rows
                Row firstrow= rows.next();
                Iterator<Cell> ce=firstrow.cellIterator();//row is collection of cells
                int k=0;
                int coloumn = 0;
                while(ce.hasNext())
                {
                    Cell value=ce.next();

                    if(value.getStringCellValue().equalsIgnoreCase(headertestCases))
                    {
                        coloumn=k;

                    }

                    k++;
                }
                System.out.println("coloumn :"+coloumn);

                ////once coloumn is identified then scan entire testcase coloum to identify purcjhase testcase row
                while(rows.hasNext())
                {

                    Row r=rows.next();

                    if(r.getCell(coloumn).getStringCellValue().equalsIgnoreCase(testcaseName))
                    {

                        ////after you grab purchase testcase row = pull all the data of that row and feed into test

                        Iterator<Cell>  cv=r.cellIterator();
                        while(cv.hasNext())
                        {
                            Cell c=	cv.next();
                            if(c.getCellType()==CellType.STRING)
                            {

                                a.add(c.getStringCellValue());
                            }
                            else{

                                a.add(NumberToTextConverter.toText(c.getNumericCellValue()));

                            }
                        }
                    }

                }
            }
        }
        return a;

    }

}
