package guru.qa;

import guru.qa.utils.CheckFile;
import guru.qa.utils.FindFileInResources;
import guru.qa.utils.TypeExt;
import org.junit.jupiter.api.Test;

import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Stream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

public class ArchiveTest implements CheckFile {

    ClassLoader cl =ArchiveTest.class.getClassLoader();
    FindFileInResources findFileInResources=new FindFileInResources();

    final Path path = Paths.get("src/test/resources");




    @Test
    void addFilesFromResourcesToArchive() throws IOException {
        List<String> srcFiles = findFileInResources.getListWithNamesOfFiles(path);
        System.out.println("++++++++++++++++++++++++" +srcFiles);
        Stream.of(srcFiles).forEach(System.out::println);

        try( FileOutputStream fos = new FileOutputStream("src/test/resources/multiCompressed.zip");
        ZipOutputStream zipOut = new ZipOutputStream(fos)){

        for (String srcFile : srcFiles) {
            File fileToZip = new File(srcFile);
            try(InputStream fis =cl.getResourceAsStream(srcFile)){
            ZipEntry zipEntry = new ZipEntry(fileToZip.getName());
            zipOut.putNextEntry(zipEntry);

            byte[] bytes = new byte[1024];
            int length;
            while((length = fis.read(bytes)) >= 0) {
                zipOut.write(bytes, 0, length);
            }}
        }}
    }

    @Test
    void zipTest() throws Exception {
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




    @Override
    public File heckFile(TypeExt ext) {
        return null;
    }
}


