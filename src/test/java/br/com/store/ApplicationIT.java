package br.com.store;

import com.intuit.karate.junit5.Karate;
import com.intuit.karate.junit5.Karate.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ActiveProfiles;

@ActiveProfiles("it")
@DisplayName("ApplicationIT")
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ApplicationIT {
    private static final String[] FEATURES = {
            "classpath:features/orders.feature"
    };

    @LocalServerPort
    private Integer port;

    @Value("${spring.security.user.token}")
    private String token;

    @Autowired
    private ApplicationContext context;

    @BeforeEach
    public void before() {
        System.setProperty("java.awt.headless", "false");
        System.setProperty("server.application", "http://localhost:" + port);
        System.setProperty("server.authorization", token);
    }

    @Test
    @DisplayName("Application.Controller.features")
    public Karate features() {
        return Karate.run(FEATURES)
                .relativeTo(getClass());
    }
}
