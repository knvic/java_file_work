package guru.qa.utils;

import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Stream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class ArchiveWork {

    ClassLoader cl = ArchiveWork.class.getClassLoader();
    FindFileInResources findFileInResources=new FindFileInResources();

    final Path path = Paths.get("src/test/resources");


    void addFilesFromResourcesToArchive() throws IOException {
        List<String> srcFiles = findFileInResources.getListWithNamesOfFiles(path);
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

}


