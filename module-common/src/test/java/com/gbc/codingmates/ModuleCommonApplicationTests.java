package com.gbc.codingmates;

import org.junit.jupiter.api.Test;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.test.context.ActiveProfiles;

@SpringBootApplication
@EnableJpaAuditing
@ActiveProfiles("test")
public class ModuleCommonApplicationTests {

    @Test
    public void contextLoads() {
    }
}