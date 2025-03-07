package tests;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.logevents.SelenideLogger;
import config.WebDriverConfig;
import io.qameta.allure.selenide.AllureSelenide;
import org.aeonbits.owner.ConfigFactory;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.io.IOException;
import java.io.InputStream;
import java.util.Map;
import java.util.Properties;

public class TestBase {
    static WebDriverConfig config;

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
        config = ConfigFactory.create(WebDriverConfig.class, System.getProperties());

        System.out.println(System.getProperties());
        System.out.println("Is Remote: " + config.getIsRemote());
        System.out.println("Remote URL: " + config.getRemoteUrl());
        Configuration.baseUrl = config.getBaseUrl();
        Configuration.pageLoadStrategy = config.getLoadStrategy();

        String userName = System.getProperty("userName");
        String password = System.getProperty("password");

        String remoteUrlTemplate = config.getRemoteUrl();
        if (userName != null && password != null) {
            remoteUrlTemplate = remoteUrlTemplate.replace("${userName}", userName)
                    .replace("${password}", password);
            Configuration.remote = remoteUrlTemplate;  // Устанавливаем remoteUrl после замены
        }
            if (config.getIsRemote()) {
                Configuration.remote = config.getRemoteUrl();
                DesiredCapabilities capabilities = new DesiredCapabilities();
                capabilities.setCapability("selenoid:options", Map.<String, Object>of(
                        "enableVNC", true,
                        "enableVideo", true
                ));
                Configuration.browserCapabilities = capabilities;
            }
        }


    @BeforeEach
    void beforeEach() {
        SelenideLogger.addListener("AllureSelenide", new AllureSelenide());
    }
}