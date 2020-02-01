package com.webstaurantstore.basetest.utility;

import org.testng.annotations.DataProvider;

import java.io.File;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Iterator;

public class ExcelDataProvider {

    @DataProvider(name = "ExcelDataProvider")
    public Iterator<Object[]> getExcelData(Method method){// need to refactor as CSV.. to get map with key
        String excelPath = "src" + File.separator + "test" + File.separator + "testResources" + File.separator + "test_data"
                + File.separator + method.getDeclaringClass().getSimpleName() + "_" + method.getName() + ".xls";



        ArrayList<Object[]> list = ExcelReader.getExcelData(excelPath);
        return list.iterator();

    }

}
