package guru.qa.interfaces;

import guru.qa.utils.TypeExt;

import java.io.*;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

public interface CheckFile {


    static String zipTest(ClassLoader cl, TypeExt ext) throws Exception {
        File destDir = new File("src/test/resources/unzipTest");
        try (InputStream stream = cl.getResourceAsStream("multiCompressed.zip");
             ZipInputStream zis = new ZipInputStream(stream)) {
            byte[] buffer = new byte[1024];
            ZipEntry entry;
            int len;
            while ((entry = zis.getNextEntry()) != null) {
                if( entry.getName().contains(ext.getfileExt())) {
                    File newFile = new File(destDir, entry.getName());
                    FileOutputStream fos = new FileOutputStream(newFile);
                    while ((len = zis.read(buffer)) > 0) {
                        fos.write(buffer, 0, len);
                    }
                    fos.close();
                }
            }
            return null;
        }
    }


    public void checkFile(TypeExt typeExt);



}



