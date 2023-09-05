package guru.qa;

import guru.qa.interfaces.CheckFile;
import guru.qa.utils.TypeExt;
import org.junit.jupiter.api.Test;



public class CSVFileTest implements CheckFile {

    ClassLoader cl =ArchiveTest.class.getClassLoader();

    @Override
    public void checkFile(TypeExt typeExt) {
        System.out.println(typeExt.getfileExt());
    }


    @Test
    void csvFileTest() throws Exception {
       String name= CheckFile.zipTest(cl,TypeExt.CSV);
        System.out.println("name===== "+name);
        checkFile(TypeExt.CSV);
    }


}
