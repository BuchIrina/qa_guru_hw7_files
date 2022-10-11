package com.buchneva;

import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static org.assertj.core.api.Assertions.assertThat;

public class SelenideFileTest {

    /* If you don't have href:
       static {
           Configuration.fileDownload = FileDownloadMode.PROXY;
       }*/


    @Test
    void selenideFileDownloadTest() throws Exception {
        open("https://github.com/junit-team/junit5/blob/main/README.md");
        File downloadedFile = $("#raw-url").download();
        try (InputStream is = new FileInputStream(downloadedFile);) {
            byte[] fileSource = is.readAllBytes();
            String fileContent = new String(fileSource, StandardCharsets.UTF_8);
            assertThat(fileContent).contains("This repository is the home of the next generation of JUnit, _JUnit 5_.");
        }
    }

    @Test
    void uploadFileTest() throws Exception {
        open("https://fineuploader.com/demos.html");
        $("input[type = 'file']").uploadFromClasspath("folder/nature.jpeg");
        $(".qq-file-info").shouldHave(text("nature.jpeg"));
    }


}
