package guru.qa;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;
import guru.qa.interfaces.CheckFile;
import guru.qa.utils.TypeExt;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.Arrays;
import java.util.List;


public class CSVFileTest implements CheckFile {

    ClassLoader cl =ArchiveTest.class.getClassLoader();

    @Override
    public void checkFile(String name) throws Exception {
       // System.out.println(typeExt.getfileExt());
        try (InputStream stream = cl.getResourceAsStream(name);
             Reader reader = new InputStreamReader(stream)) {
            CSVReader csvReader = new CSVReader(reader);
            List<String[]> content = csvReader.readAll();

           // Assertions.assertEquals(3, content.size());

            final String[] firstRow = content.get(0);
            final String[] secondRow = content.get(1);
            final String[] thirdRow = content.get(2);
            System.out.println("--------------------"+Arrays.toString(firstRow));
            System.out.println("firstRow.length==="+firstRow);
            System.out.println(secondRow);
            System.out.println(thirdRow);
            // ﻿ - \uFEFF   - tmp = tmp.replace("\uFEFF", "");


            Assertions.assertEquals("[ID;ProtID;Direction;AuxCod;ScadaCode;Level;Comment;rowguid]", Arrays.toString(firstRow).replace("\uFEFF", ""));

            //Assertions.assertEquals("[\uFEFFID;ProtID;Direction;AuxCod;ScadaCode;Level;Comment;rowguid]", Arrays.toString(firstRow).replace("\uFEFF", ""));
            /*Assertions.assertArrayEquals(new String[] {"Tuchs", "Files"}, secondRow);
            Assertions.assertArrayEquals(new String[] {"Vasenkov", "REST Assured"}, thirdRow);*/

        }

    }


    @Test
    void csvFileTest() throws Exception {
       String name= CheckFile.zipTest(cl,TypeExt.CSV);
        System.out.println("name===== "+name);
        checkFile(name);
    }


}
