package guru.qa;

import guru.qa.utils.FindFileInResources;
import org.junit.jupiter.api.Test;

import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class ArchiveTest {

    ClassLoader cl =ArchiveTest.class.getClassLoader();
    FindFileInResources findFileInResources=new FindFileInResources();

    final Path path = Paths.get("src/test/resources");
   // final Path path = Paths.get("src/");

    @Test
    void addToArchive() throws IOException {
        List<String> srcFiles = findFileInResources.getListWithNamesOfFiles(path);
        System.out.println("===============================");
        Stream.of(srcFiles).forEach(System.out::println);


        FileOutputStream fos = new FileOutputStream("src/test/resources/multiCompressed.zip");
        ZipOutputStream zipOut = new ZipOutputStream(fos);
        for (String srcFile : srcFiles) {
            File fileToZip = new File(srcFile);
           // FileInputStream fis = new FileInputStream(fileToZip);
            InputStream fis =cl.getResourceAsStream(srcFile);
            ZipEntry zipEntry = new ZipEntry(fileToZip.getName());
            zipOut.putNextEntry(zipEntry);

            byte[] bytes = new byte[1024];
            int length;
            while((length = fis.read(bytes)) >= 0) {
                zipOut.write(bytes, 0, length);
            }
            fis.close();
        }
        zipOut.close();
        fos.close();
    }

    @Test
    void addToArchiveWTR() throws IOException {
        List<String> srcFiles = findFileInResources.getListWithNamesOfFiles(path);
        System.out.println("===============================");
        Stream.of(srcFiles).forEach(System.out::println);

        try( FileOutputStream fos = new FileOutputStream("src/test/resources/multiCompressed.zip");
        ZipOutputStream zipOut = new ZipOutputStream(fos)){

        for (String srcFile : srcFiles) {
            File fileToZip = new File(srcFile);
            //FileInputStream fis = new FileInputStream(fileToZip);
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



    }


