package com.buchneva;

import com.codeborne.pdftest.PDF;
import com.codeborne.xlstest.XLS;
import com.opencsv.CSVReader;
import org.junit.jupiter.api.Test;

import java.io.InputStreamReader;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

import static org.assertj.core.api.Assertions.assertThat;

public class ZipTest {

    ClassLoader loader = FileParseTest.class.getClassLoader();


    @Test
    void zipFilesTest() throws Exception {
        try (ZipInputStream files = new ZipInputStream(loader.getResourceAsStream("qa_guru_zipfiles.zip"))) {
            ZipEntry entry;
            while ((entry = files.getNextEntry()) != null) {
                if (entry.getName().contains(".pdf")) {
                    PDF pdf = new PDF(files);
                    assertThat(pdf.numberOfPages).isEqualTo(300);
                }
                if (entry.getName().contains("csv")) {
                    CSVReader reader = new CSVReader(new InputStreamReader(files));
                    List<String[]> content = reader.readAll();
                    String[] row = content.get(0);
                    assertThat(row[0]).isEqualTo("Teacher");
                    assertThat(row[1]).isEqualTo("Lesson");
                }
                if (entry.getName().contains("xlsx")) {
                    XLS xls = new XLS(files);
                    assertThat(xls.excel.getSheetAt(0).getRow(2).getCell(3).getStringCellValue())
                            .isEqualTo("1. На компьютере открыт браузер Google Chrome версии не ниже 70 в режиме инкогнито.\n" +
                                    "2. Открыть сайт Яндекс.Метро по ссылке: https://qa-metro.stand-1.praktikum-services.ru/moscow");
                }
            }
        }
    }
}
