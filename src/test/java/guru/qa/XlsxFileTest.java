package guru.qa;

import com.codeborne.xlstest.XLS;
import com.opencsv.CSVReader;
import guru.qa.interfaces.CheckFile;
import guru.qa.utils.TypeExt;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.InputStream;



public class XlsxFileTest implements CheckFile {

    ClassLoader cl =ArchiveTest.class.getClassLoader();

    @Override
    public void checkFile(String name) throws Exception {
             try (InputStream stream = cl.getResourceAsStream(name)) {
            XLS xls = new XLS(stream);

            Assertions.assertEquals("[Ильенко] ЛР 330 Черкесск ф.А",xls.excel.getSheetAt(0).
                    getRow(1)
                    .getCell(1)
                    .getStringCellValue());
        }
    }


    @Test
    void csvFileTest() throws Exception {
       String name= CheckFile.zipTest(cl,TypeExt.XLSX);
        System.out.println("name===== "+name);
        checkFile(name);
    }


}
