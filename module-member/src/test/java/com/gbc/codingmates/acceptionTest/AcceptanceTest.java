package com.gbc.codingmates.acceptionTest;

import com.gbc.codingmates.acceptionTest.utils.DatabaseCleanup;
import io.restassured.RestAssured;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.ActiveProfiles;

@ActiveProfiles("test")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public abstract class AcceptanceTest {

    @LocalServerPort
    int port;
    @Autowired
    private DatabaseCleanup databaseCleanup;


    public void init() {
        RestAssured.port = port;
        databaseCleanup.execute();
    }

}
