package config;

import org.aeonbits.owner.Config;

@Config.Sources({
        "classpath:${env}.properties",
        "classpath:local.properties"
})

public interface ApiConfig extends Config {
    @Key("baseUrl")
    @DefaultValue("https://petstore.swagger.io")
    String getBaseUrl();

    @Key("isRemote")
    @DefaultValue("true")
    Boolean getIsRemote();

    @Key("remoteUrl")
    String getRemoteUrl();

    @Key("api_key")
    @DefaultValue("special-shadow")
    String apiKey();

}