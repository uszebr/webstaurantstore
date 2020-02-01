package com.webstaurantstore.basetest.utility;

import au.com.bytecode.opencsv.CSVReader;
import org.testng.annotations.DataProvider;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.*;

public class CsvDataProvider { // here is dataProvider class

    @DataProvider(name = "CsvDataProvider")//here is dataProvider name
    public static Iterator<Object[]> provideData(Method method) {
        List<Object[]> list = new ArrayList<Object[]>();
        //creating filePath to csv file for EACH method... with ClassName_methodName.csv located in test/resourses/test_data/ folder
        String pathName = "src" + File.separator + "test" + File.separator + "testResources" + File.separator + "test_data"
                + File.separator + method.getDeclaringClass().getSimpleName() + "_" + method.getName() + ".csv";


        // src\test\testResources\test_data\LoginTest_negativeLoginTest.csv
        //String pathName = "C:\\Users\\viktor\\IdeaProjects\\bokarat\\src\\test\\testResources\\test_data\\LoginTest_negativeTest.csv";
        File file = new File(pathName);
        CSVReader reader = null;
        try {
            reader = new CSVReader(new FileReader(file));
            String[] keys = reader.readNext(); //reading first row from csv - keys row;Names of collumns
            if (keys != null) {//if keys array not null trying to read data
                String[] dataParts;
                while ((dataParts = reader.readNext()) != null) { // Reading next line of csv to dataParts array, and if
                    //it is not null starting separate them in while loop body
                    Map<String, String> testData = new HashMap<>();//creating Map of keys and dataparts
                    for (int i = 0; i < keys.length; i++) {

                        testData.put(keys[i], dataParts[i]); //adding to Map dataparts and related key(from key array)
                        //для каждой строки создается мэк который заполняется ключами из первой строки и
                        //соответсвующими значениями из текущей строки.
                    }

                    list.add(new Object[]{testData});// additg to the list map of entries(key, dataParts)
                    //next while loop iteration to get next row from csv
                    // добавляем полученную мапу в список  перед этим приводим его к типу обжект

                }


            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException("File " + pathName + "was not found\n" + e.getStackTrace().toString());
        } catch (IOException e) {
            throw new RuntimeException("Could not read/write file " + pathName + e.getStackTrace().toString());
        } finally {
            try { //add later close reader

                if (reader!=null) {
                    reader.close();
                }
            } catch (IOException e) {
                throw new RuntimeException("Can't close file " + pathName + e.getStackTrace().toString());
            }
        }

        return list.iterator();
        //return list.iterator();
    }


}
