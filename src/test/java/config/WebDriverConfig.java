package config;

import org.aeonbits.owner.Config;

@Config.Sources({
        "classpath:${env}.properties",
        "classpath:local.properties"
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


}