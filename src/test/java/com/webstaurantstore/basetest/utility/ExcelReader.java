package com.webstaurantstore.basetest.utility;

import com.webstaurantstore.basetest.BaseTest;
import org.apache.poi.ss.usermodel.*;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

public abstract class ExcelReader  {
    public static ArrayList<Object[]> getExcelData(String excelPath) {//method returns all xls, xlsx file ArrayList without first row!!
        ArrayList<Object[]> testData = new ArrayList<>();
        File file = new File(excelPath);
        Workbook workbook = null;
        try {
            workbook = WorkbookFactory.create(file);
        } catch (IOException e) {
            System.out.println("Can Not read the file " + excelPath);
        }
        Sheet sheet = workbook.getSheetAt(0);
        Iterator<Row> iteratorRows = sheet.rowIterator();
        iteratorRows.next();//skip first row -> names column
        while (iteratorRows.hasNext()) {//iterating rows
            Row row = iteratorRows.next();
            Iterator<Cell> iteratorCells = row.cellIterator();
            Object[] object = new Object[row.getLastCellNum()];
            int cellCount = 0;
            while (iteratorCells.hasNext()) {
                Cell cell = iteratorCells.next();
                //System.out.print(cell.toString() + "\t\t");
                String cellString = "";
                if (cell.getCellType() == CellType.NUMERIC){
                    cellString = String.valueOf ((int)cell.getNumericCellValue());// removing end zero from numeric cells
                }else {
                    cellString = cell.toString();
                }
                object[cellCount] = cellString;
                cellCount++;
            }


            testData.add(object);
        }

        return testData;
    }
}