package guru.qa.utils;

import org.apache.commons.io.FileUtils;


import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

public class AuxiliaryUnZipFileCheckTests {

    ClassLoader cl = AuxiliaryUnZipFileCheckTests.class.getClassLoader();
    final Path path = Paths.get("src/test/resources");


    public static String getFileNameSelectedFileExtensionFromArchive(ClassLoader cl, TypeExt ext) throws Exception {
        String namefile = null;
        File destDir = new File("src/test/resources/unzipTest");
        try (InputStream stream = cl.getResourceAsStream("multiCompressed.zip");
             ZipInputStream zis = new ZipInputStream(stream)) {
            byte[] buffer = new byte[1024];
            ZipEntry entry;
            int len;
            while ((entry = zis.getNextEntry()) != null) {
                if( entry.getName().contains(ext.getfileExt())) {
                    namefile=entry.getName();
                    File newFile = new File(destDir, entry.getName());
                    FileOutputStream fos = new FileOutputStream(newFile);
                    while ((len = zis.read(buffer)) > 0) {
                        fos.write(buffer, 0, len);
                    }
                    fos.close();
                }
            }
            return namefile;
        }
    }


    public void addFilesFromResourcesToArchive(String name) throws IOException {
        List<String> srcFiles = getListWithNamesOfFiles(path);
        Stream.of(srcFiles).forEach(System.out::println);
        try( FileOutputStream fos = new FileOutputStream(name);
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

    public List<String> getListWithNamesOfFiles(Path path) {
        try (Stream<Path> walk = Files.walk(path)) {
            return walk
                    .filter(Files::isRegularFile)
                    .map(Path::getFileName)
                    .map(Path::toString)
                    .filter(e -> !e.startsWith("multi"))
                    .collect(Collectors.toList());
        } catch (IOException e) {
            throw new RuntimeException("Could not read files for path " + path);
        }
    }

    public List<String> removeArchiveFileFromList(Path path) {
        try (Stream<Path> walk = Files.walk(path)) {
            return walk
                    .filter(Files::isRegularFile)
                    .map(Path::getFileName)
                    .map(Path::toString)
                    .collect(Collectors.toList());
        } catch (IOException e) {
            throw new RuntimeException("Could not read files for path " + path);
        }
    }
    public void removeFilesFromUnZipDirectory() throws IOException {
        File destDir = new File("src/test/resources/unzipTest");
        FileUtils.cleanDirectory(destDir);
    }
}
