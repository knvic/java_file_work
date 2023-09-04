package guru.qa;

import com.codeborne.selenide.SelenideElement;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.*;
import java.nio.charset.StandardCharsets;

import static com.codeborne.selenide.Selenide.*;

public class WorkWithFile extends BaseTest {


    @Test
    void downloadFile() throws IOException {
        SelenideElement
                link1=$("a[href*='/README.md']");
        open("https://github.com/junit-team/junit5/blob/main/README.md");
       // File downloaded = $("#raw-url").download();
       // File downloaded =link1.download();
        //File downloaded =$$("a[href*='/README.md']").first().download();

       // Assertions.assertThrows(FileNotFoundException.class, ()-> $("[data-testid=raw-button]").download());
     File downloaded =$("[data-testid=raw-button]").download();


        try (InputStream is = new FileInputStream(downloaded)) {
            byte[] bytes = is.readAllBytes();
            String content = new String(bytes, StandardCharsets.UTF_8);

            Assertions.assertTrue(content.contains("This repository is the home of _JUnit 5_."));


        }
    }


    @Test
    void downloadTxtFileTest() throws Exception {
        open("https://github.com/junit-team/junit5/blob/main/README.md");
        File downloaded = $("#raw-url").download();
        try (InputStream is = new FileInputStream(downloaded)) {
            byte[] bytes = is.readAllBytes();
            String content = new String(bytes, StandardCharsets.UTF_8);
            Assertions.assertTrue(content.contains("This repository is the home of _JUnit 5_."));

        }
    }
}
