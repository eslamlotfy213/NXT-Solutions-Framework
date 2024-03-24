package com.automationexercise.Utilities;

import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class DataUtils {
    public final static String CONFIG_PATH = "src/test/java/resources/config/";
    public final static String TEST_DATA_PATH = "src/test/resources/test-data/";

    private DataUtils() {
    }

    //TODO: Read data from json file
    public static String getJsonData(String jsonFilename, String field) {
        try {
            // Define object of file Reader
            FileReader reader = new FileReader(TEST_DATA_PATH + jsonFilename + ".json");
            // Parse the JSON directly into a JsonElement
            JsonElement jsonElement = JsonParser.parseReader(reader);
            return jsonElement.getAsJsonObject().get(field).getAsString();
        } catch (Exception e) {
            LogUtils.error(e.getMessage());
        }
        return "";
    }

    // TODO: Read Data From Excel Sheet
    public static String getExcelData(String excelFilename, String sheetName, int rowNum, int colNum) {
        XSSFWorkbook workBook;
        XSSFSheet sheet;

        String cellData;
        try {
            workBook = new XSSFWorkbook(TEST_DATA_PATH + excelFilename);
            sheet = workBook.getSheet(sheetName);
            cellData = sheet.getRow(rowNum).getCell(colNum).getStringCellValue();
            return cellData;

        } catch (IOException e) {
            LogUtils.error(e.getMessage());
            return "";
        }

    }

    //TODO: get properties from any .properties file
    public static String getPropertyValue(String fileName, String key) {
        try {
            Properties properties = new Properties();
            properties.load(new FileInputStream(TEST_DATA_PATH + fileName + ".properties"));
            return properties.getProperty(key);
        } catch (Exception e) {
            LogUtils.error(e.getMessage());
            return "";
        }

    }

    //TODO: get properties from any .properties file
    public static String getConfigValue(String fileName, String key) {
        try {
            Properties properties = new Properties();
            properties.load(new FileInputStream(CONFIG_PATH + fileName + ".properties"));
            return properties.getProperty(key);
        } catch (Exception e) {
            LogUtils.error(e.getMessage());
            return "";
        }
    }
}
