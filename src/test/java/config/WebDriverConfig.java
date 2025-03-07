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

    @Key("isRemote")
    @DefaultValue("true")
    Boolean getIsRemote();

    @Key("remoteUrl")
    String getRemoteUrl();

    @Key("loadStrategy")
    @DefaultValue("eager")
    String getLoadStrategy();

    @Key("userName")
    String userName();

    @Key("password")
    String password();
}