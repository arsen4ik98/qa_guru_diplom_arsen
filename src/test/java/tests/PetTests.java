package tests;


import api.PetApi;
import config.WebDriverConfig;
import io.qameta.allure.Owner;
import io.restassured.response.Response;
import org.aeonbits.owner.ConfigFactory;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tags;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Tag;
import tests.models.OrderStatus;
import tests.models.PetStatus;
import tests.models.PetTag;
import tests.models.Category;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import static org.hamcrest.Matchers.equalTo;
import static specs.UserSpecs.userResponseSpecification200;
import static specs.UserSpecs.userResponseSpecification404;

@DisplayName("Тесты животного")
@Tag("bi_test")
@Owner("arsen4ik98")
public class PetTests extends TestBase {


    WebDriverConfig authConfig = ConfigFactory.create(WebDriverConfig.class);
    PetApi petApi = new PetApi();


    @DisplayName("Проверка добавления животного")
    @Tag("bi_test") // Добавьте этот тег
    @Test
    void registerPetApiTest() {
        Category category = new Category(1232, "555");
        PetTag PetTag = new PetTag(2, "54");
        petApi.addPet(2, category, "CatTest", Collections.emptyList(), PetTag, PetStatus.AVAILABLE);

    }

    @DisplayName("Проверка получение корректных данных животного")
    @Tag("bi_test") // Добавьте этот тег
    @Test
    void getPetApiTest() {
        int petId = 178;
        Category category = new Category(1232, "555");
        String petName = "doggie";
        PetTag PetTag = new PetTag(2, "54");
        petApi.addPet(petId, category, petName, Collections.emptyList(), PetTag, PetStatus.AVAILABLE);
        Response response = petApi.getPet(petId, userResponseSpecification200);
        response.then()
                .body("id", equalTo(petId))
                .body("category.id", equalTo(category.getId()))
                .body("category.name", equalTo(category.getName()))
                .body("name", equalTo(petName))
                .body("tags.id", equalTo(PetTag.getId()))
                .body("tags.name", equalTo(PetTag.getName()))
                .body("status", equalTo(PetStatus.AVAILABLE.toString()));
    }

    @DisplayName("Проверка обновление животного")
    @Tag("bi_test") // Добавьте этот тег
    @Test
    void updatePetApiTest() {
        int petId = 178;
        Category category = new Category(1232, "555");
        String petName = "doggie";
        String newPetName = "cattie";
        PetTag PetTag = new PetTag(2, "54");
        petApi.addPet(petId, category, petName, Collections.emptyList(), PetTag, PetStatus.AVAILABLE);
        Map<String, Object> updatedFields = new HashMap<>();
        updatedFields.put("name", newPetName);
        petApi.updatePet(updatedFields);
        Response response = petApi.getPet(petId, userResponseSpecification200);
        response.then()
                .body("id", equalTo(petId))
                .body("category.id", equalTo(category.getId()))
                .body("category.name", equalTo(category.getName()))
                .body("name", equalTo(newPetName))
                .body("tags.id", equalTo(PetTag.getId()))
                .body("tags.name", equalTo(PetTag.getName()))
                .body("status", equalTo(PetStatus.AVAILABLE.toString()));
    }

    @Test
    @DisplayName("Проверка удаления пользователя")
    void deleteUserApiTest() {
        int petId = 178;
        Category category = new Category(1232, "555");
        PetTag PetTag = new PetTag(2, "54");
        petApi.addPet(petId, category, "CatTest", Collections.emptyList(), PetTag, PetStatus.AVAILABLE);
        petApi.deletePet(petId, userResponseSpecification200);
        Response getResponse = petApi.getPet(petId, userResponseSpecification404);
        getResponse.then()
                .body("message", equalTo("Pet not found"));
    }


}