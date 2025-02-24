package tests;


import api.PetApi;
import config.WebDriverConfig;
import io.qameta.allure.Owner;
import org.aeonbits.owner.ConfigFactory;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tags;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Tag;
import tests.models.PetStatus;
import tests.models.PetTag;
import tests.models.Category;

import java.util.Collections;
@Tag("bi_test")
@Owner("arsen4ik98")
public class PetTests extends TestBase {



    WebDriverConfig authConfig = ConfigFactory.create(WebDriverConfig.class);
    PetApi petApi = new PetApi();


    @DisplayName("Проверка добавления животного")
    @Tag("bi_test") // Добавьте этот тег
    @Test

    void registerUserApiTest() {
        Category category = new Category(1232, "555");
        PetTag PetTag = new PetTag(2, "54");

        petApi.addPet(2, category, "CatTest", Collections.emptyList(), PetTag, PetStatus.AVAILABLE);


    }
}