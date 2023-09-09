package guru.qa;

import com.codeborne.pdftest.PDF;
import com.codeborne.xlstest.XLS;
import com.opencsv.CSVReader;
import guru.qa.interfaces.CheckFile;
import guru.qa.utils.ArchiveWork;
import guru.qa.utils.TypeExt;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

public class WorkFileTests {
    ClassLoader cl = ArchiveWork.class.getClassLoader();

    static String zipTest(ClassLoader cl, TypeExt ext) throws Exception {
        String namefile = null;
        File destDir = new File("src/test/resources/unzipTest");
        try (InputStream stream = cl.getResourceAsStream("multiCompressed.zip");
             ZipInputStream zis = new ZipInputStream(stream)) {
            byte[] buffer = new byte[1024];
            ZipEntry entry;
            int len;
            while ((entry = zis.getNextEntry()) != null) {
                if (entry.getName().contains(ext.getfileExt())) {
                    namefile = entry.getName();
                    File newFile = new File(destDir, entry.getName());
                    FileOutputStream fos = new FileOutputStream(newFile);
                    while ((len = zis.read(buffer)) > 0) {
                        fos.write(buffer, 0, len);
                    }
                    fos.close();
                }
            }
            return namefile;
        }
    }


    @Test
    void csvFileTest() throws Exception {
        String name = CheckFile.zipTest(cl, TypeExt.CSV);
        try (InputStream stream = cl.getResourceAsStream(name);
             Reader reader = new InputStreamReader(stream)) {
            CSVReader csvReader = new CSVReader(reader);
            List<String[]> content = csvReader.readAll();

            final String[] firstRow = content.get(0);
            final String[] secondRow = content.get(1);
            final String[] thirdRow = content.get(2);
            /*﻿ - \uFEFF   - tmp = tmp.replace("\uFEFF", "");п»ї
            Assertions.assertEquals("[ID;ProtID;Direction;AuxCod;ScadaCode;Level;Comment;rowguid]", Arrays.toString(firstRow).replace("\uFEFF", ""));*/
            Assertions.assertEquals("[ID;ProtID;Direction;AuxCod;ScadaCode;Level;Comment;rowguid]", Arrays.toString(firstRow).replace("п»ї", ""));

        }
    }

    @Test
    void pdfFileTest() throws Exception {
        String name = CheckFile.zipTest(cl, TypeExt.PDF);
        try (InputStream stream = cl.getResourceAsStream(name)) {
            PDF pdf = new PDF(stream);
            Assertions.assertEquals("Упражнения.", pdf.title);
            Assertions.assertTrue(pdf.text.contains("VAR1=Linux "));
        }
    }

    @Test
    void txtFileTest() throws Exception {
        String name = CheckFile.zipTest(cl, TypeExt.TXT);
        System.out.println("name===== " + name);
        try (InputStream stream = cl.getResourceAsStream(name)) {
            byte[] bytes = stream.readAllBytes();
            String content = new String(bytes, StandardCharsets.UTF_8);
            Assertions.assertTrue(content.contains("Enchufla mata la cucaracha"));
        }
    }

    @Test
    void xlsxFileTest() throws Exception {
        String name = CheckFile.zipTest(cl, TypeExt.XLSX);
        try (InputStream stream = cl.getResourceAsStream(name)) {
            XLS xls = new XLS(stream);

            Assertions.assertEquals("[Ильенко] ЛР 330 Черкесск ф.А", xls.excel.getSheetAt(0).
                    getRow(1)
                    .getCell(1)
                    .getStringCellValue());
        }
    }

}
