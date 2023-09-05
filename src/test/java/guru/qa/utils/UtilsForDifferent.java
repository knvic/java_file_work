package guru.qa.utils;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class UtilsForDifferent {

    public  List<String> getListWithNamesOfFiles(Path path) {
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

    @Test
    void utilsForDifferent() {

       // Stream.of("CSVFile.csv", "Excel.xls", "multiCompressed.zip").forEach(System.out::println);
        List<String> collect = Stream.of("CSVFile.csv", "Excel.xls", "multiCompressed.zip").peek(System.out::println).collect(Collectors.toList());
        Stream.of( collect).forEach(System.out::println);

         boolean zip = Stream.of(collect)
           .filter(e -> e.contains("multiCompressed.zip"))
           .findAny()
           .isPresent();
         System.out.println("zip=" +zip);

        ClassLoader cl =UtilsForDifferent.class.getClassLoader();

        System.out.println( "==================================================="+cl.getResource("Excel.xls"));
       /* Path path =Paths.get(
                        System.getProperty("user.dir"));*/


        Path path = Paths.get("src/test/resources");
        List<String> newlist = getListWithNamesOfFiles(path);
        System.out.println("===============================");
        Stream.of(newlist).forEach(System.out::println);



    }
}


