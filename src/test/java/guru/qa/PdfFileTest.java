package guru.qa;

import com.codeborne.pdftest.PDF;
import com.opencsv.CSVReader;
import guru.qa.interfaces.CheckFile;
import guru.qa.utils.TypeExt;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.Arrays;
import java.util.List;


public class PdfFileTest implements CheckFile {

    ClassLoader cl =ArchiveTest.class.getClassLoader();

    @Override
    public void checkFile(String name) throws Exception {
        try (InputStream stream = cl.getResourceAsStream(name);
             Reader reader = new InputStreamReader(stream)) {
            PDF pdf = new PDF(stream);
            System.out.println("title = "+pdf.title);
            Assertions.assertEquals("Упражнения.", pdf.title);
            Assertions.assertTrue(pdf.text.contains("VAR1=Linux "));

        }

    }


    @Test
    void csvFileTest() throws Exception {
       String name= CheckFile.zipTest(cl,TypeExt.PDF);
        System.out.println("name===== "+name);
        checkFile(name);
    }


}
