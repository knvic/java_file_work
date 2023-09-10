package guru.qa;

import com.codeborne.pdftest.PDF;
import com.codeborne.xlstest.XLS;
import com.opencsv.CSVReader;
import guru.qa.auxiliary.AuxiliaryUnZipFileCheckTests;
import guru.qa.auxiliary.TypeExt;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.List;

public class WorkFileTests {
    ClassLoader cl = WorkFileTests.class.getClassLoader();

    AuxiliaryUnZipFileCheckTests auxiliaryUnZipFileCheckTests = new AuxiliaryUnZipFileCheckTests();



    /***
     Появляющиеся символы в первой колонке
     ﻿ - \uFEFF
     п»ї - не понятно что и откуда
     Избавляемся .replace()
     Assertions.assertEquals("[ID;ProtID;Direction;AuxCod;ScadaCode;Level;Comment;rowguid]", Arrays.toString(firstRow).replace("\uFEFF", ""));***/
    @Test
    @DisplayName("Ищем в архиве CSV файл, получаем его имя, извлекаем из архива" +
            "в папку unzip и проверяем содержимое.")
    void csvFileTest() throws Exception {
        String name = AuxiliaryUnZipFileCheckTests.getFileNameSelectedFileExtensionFromArchive(cl, TypeExt.CSV);
        try (InputStream stream = cl.getResourceAsStream(name);
             Reader reader = new InputStreamReader(stream)) {
            CSVReader csvReader = new CSVReader(reader);
            List<String[]> content = csvReader.readAll();
            final String[] firstRow = content.get(0);
            Assertions.assertEquals("[ID;ProtID;Direction;AuxCod;ScadaCode;Level;Comment;rowguid]", Arrays.toString(firstRow).replace("п»ї", ""));

        }
    }

    @Test
    @DisplayName("Ищем в архиве PDF файл, получаем его имя, извлекаем из архива" +
            "в папку unzip и проверяем содержимое.")
    void pdfFileTest() throws Exception {
        String name = AuxiliaryUnZipFileCheckTests.getFileNameSelectedFileExtensionFromArchive(cl, TypeExt.PDF);
        try (InputStream stream = cl.getResourceAsStream(name)) {
            PDF pdf = new PDF(stream);
            Assertions.assertEquals("Упражнения.", pdf.title);
            Assertions.assertTrue(pdf.text.contains("VAR1=Linux "));
        }
    }

    @Test
    @DisplayName("Ищем в архиве TXT файл, получаем его имя, извлекаем из архива" +
            "в папку unzip и проверяем содержимое.")
    void txtFileTest() throws Exception {
        String name = AuxiliaryUnZipFileCheckTests.getFileNameSelectedFileExtensionFromArchive(cl, TypeExt.TXT);
            try (InputStream stream = cl.getResourceAsStream(name)) {
            byte[] bytes = stream.readAllBytes();
            String content = new String(bytes, StandardCharsets.UTF_8);
            Assertions.assertTrue(content.contains("Enchufla mata la cucaracha"));
        }
    }

    @Test
    @DisplayName("Ищем в архиве XLSX файл, получаем его имя, извлекаем из архива" +
            "в папку unzip и проверяем содержимое.")
    void xlsxFileTest() throws Exception {
        String name = AuxiliaryUnZipFileCheckTests.getFileNameSelectedFileExtensionFromArchive(cl, TypeExt.XLSX);
        try (InputStream stream = cl.getResourceAsStream(name)) {
            XLS xls = new XLS(stream);

            Assertions.assertEquals("[Ильенко] ЛР 330 Черкесск ф.А", xls.excel.getSheetAt(0).
                    getRow(1)
                    .getCell(1)
                    .getStringCellValue());
        }
    }


    @Test
    @DisplayName("Удаляем файлы из папки unzipTest. Создаем архив и добавляем файлы из папки resouces." +
            "Проверяем наличие созданного файла.")
    void addFilesFromResourcesToArchiveTest() throws IOException {
        auxiliaryUnZipFileCheckTests.removeFilesFromUnZipDirectory();
        auxiliaryUnZipFileCheckTests.addFilesFromResourcesToArchive("src/test/resources/multiCompressed.zip");
        Assertions.assertTrue(new File("src/test/resources/multiCompressed.zip").exists());

    }


}
