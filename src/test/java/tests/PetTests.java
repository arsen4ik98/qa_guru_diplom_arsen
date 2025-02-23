package tests;

import api.PetApi;
import config.WebDriverConfig;
import org.aeonbits.owner.ConfigFactory;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import tests.models.Category;
import tests.models.PetStatus;
import tests.models.Tag;

import java.util.Collections;

public class PetTests extends TestBase{

    WebDriverConfig authConfig = ConfigFactory.create(WebDriverConfig.class);
    String userName = authConfig.userName();
    String password = authConfig.password();
    PetApi petApi = new PetApi();

    @DisplayName("Проверка добавления животного")
    @Test
    void registerUserApiTest() {
        Category category = new Category(1232, "555");
        Tag tag = new Tag(2, "54");

        petApi.addPet(2,category,"CatTest", Collections.emptyList(),tag, PetStatus.AVAILABLE);
    }

}
