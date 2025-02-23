package config;

import org.aeonbits.owner.Config;

@Config.Sources({
        "classpath:${env}.properties",// Использует файл, указанный через переменную окружения "env"
        "classpath:local.properties"   // Файл по умолчанию, если "env" не передан или файл не найден
})

public interface WebDriverConfig extends Config {
    @Key("baseUrl")
    @DefaultValue("https://petstore.swagger.io")
    String getBaseUrl();

    @Key("browser")
    @DefaultValue("CHROME")
    String getBrowser();

    @Key("browserVersion")
    @DefaultValue("latest")
    String getBrowserVersion();

    @Key("isRemote")
    @DefaultValue("false")
    Boolean getIsRemote();

    @Key("remoteUrl")
    @DefaultValue("https://user1:1234@selenoid.autotests.cloud/wd/hub")
    String getRemoteUrl();

    @Key("loadStrategy")
    @DefaultValue("eager")
    String getLoadStrategy();

    @Key("browserSize")
    @DefaultValue("1920x1080")
    String getbrowserSize();

    @Key("userName")
    @DefaultValue("arsen4ik98")
    String userName();

    @Key("password")
    @DefaultValue("test123")
    String password();
}