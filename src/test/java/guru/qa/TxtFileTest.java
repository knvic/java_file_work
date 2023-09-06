package guru.qa;

import guru.qa.interfaces.CheckFile;
import guru.qa.utils.TypeExt;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.InputStream;
import java.nio.charset.StandardCharsets;




public class TxtFileTest implements CheckFile {

    ClassLoader cl = ArchiveTest.class.getClassLoader();

    @Override
    public void checkFile(String name) throws Exception {
          try (InputStream stream = cl.getResourceAsStream(name)){
            byte[] bytes = stream.readAllBytes();
            String content = new String(bytes, StandardCharsets.UTF_8);
             Assertions.assertTrue(content.contains("Enchufla mata la cucaracha"));
        }
    }


    @Test
    void txtFileTest() throws Exception {
       String name= CheckFile.zipTest(cl, TypeExt.TXT);
       System.out.println("name===== "+name);
       checkFile(name);
    }


}
