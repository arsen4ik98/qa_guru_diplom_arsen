package tests;

import config.ApiConfig;
import org.aeonbits.owner.ConfigFactory;
import org.junit.jupiter.api.BeforeAll;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class TestBase {
    static ApiConfig config;

    @BeforeAll
    static void beforeAll() {
        String env = System.getProperty("env", "local");
        System.out.println("Environment: " + env);

        try (InputStream input = TestBase.class.getClassLoader().getResourceAsStream(env + ".properties")) {
            if (input == null) {
                throw new RuntimeException("Could not find properties file: " + env + ".properties");
            }

            Properties props = new Properties();
            props.load(input);
            System.getProperties().putAll(props);
        } catch (IOException e) {
            throw new RuntimeException("Could not load properties file: " + env + ".properties", e);
        }

        config = ConfigFactory.create(ApiConfig.class, System.getProperties());

        System.out.println(System.getProperties());
        System.out.println("Base URL: " + config.getBaseUrl());
        System.out.println("API Key: " + config.apiKey());
    }
}
