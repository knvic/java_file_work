package guru.qa.utils;

import java.io.File;
import java.io.InputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

public interface CheckFile {


    static void zipTest(ClassLoader cl,TypeExt ext) throws Exception {
    try (InputStream stream = cl.getResourceAsStream("multiCompressed.zip");
         ZipInputStream zis = new ZipInputStream(stream)) {

        ZipEntry entry;
        while ((entry = zis.getNextEntry()) != null) {
            final String name = entry.getName();
            System.out.println("entry = "+name);
            //Assertions.assertTrue(name.contains("sample.txt"));
        }
    }
}

    public File heckFile(TypeExt ext);
}
