package com.buchneva;

import com.buchneva.model.Cat;
import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;

import java.io.InputStream;
import java.io.InputStreamReader;

import static org.assertj.core.api.Assertions.assertThat;

public class JsonTest {

    ClassLoader loader = FileParseTest.class.getClassLoader();

    @Test
    void jsonTest() throws Exception {
        try (InputStream stream = loader.getResourceAsStream("cats.json")) {
            ObjectMapper objectMapper = new ObjectMapper();
            Cat cat = objectMapper.readValue(new InputStreamReader(stream), Cat.class);
            assertThat(cat.name).isEqualTo("Murka");
            assertThat(cat.age).isEqualTo(3);
            assertThat(cat.parameters.weight).isEqualTo(2.5);
            assertThat(cat.ability).contains("jump");
            assertThat(cat.likesToSleep).isTrue();
        }
    }
}
