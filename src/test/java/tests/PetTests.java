package tests;

import api.PetApi;
import io.qameta.allure.Owner;
import io.restassured.response.Response;
import models.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;
import static specs.UserSpecs.userResponseSpecification200;
import static specs.UserSpecs.userResponseSpecification404;

@DisplayName("Тесты животного")
@Tag("bi_test")
@Owner("arsen4ik98")
public class PetTests extends TestBase {

    PetApi petApi = new PetApi();

    @DisplayName("Проверка добавления животного")
    @Tag("bi_test")
    @Test
    void registerPetApiTest() {
        Category category = new Category(1232, "555");
        PetTag petTag = new PetTag(2, "54");
        PetModels pet = new PetModels(2, category, "CatTest", Collections.emptyList(), petTag, PetStatus.AVAILABLE);

        Response response = petApi.addPet(pet);

        response.then()
                .statusCode(200)
                .body("id", notNullValue())
                .body("category.id", equalTo(category.getId()))
                .body("category.name", equalTo(category.getName()))
                .body("name", equalTo(pet.getName()))
                .body("tags[0].id", equalTo(petTag.getId()))
                .body("tags[0].name", equalTo(petTag.getName()))
                .body("status", equalTo(pet.getStatus().toString()));
    }

    @DisplayName("Проверка получения корректных данных животного")
    @Tag("bi_test")
    @Test
    void getPetApiTest() {
        int petId = 178;
        Category category = new Category(1232, "555");
        String petName = "doggie";
        PetTag petTag = new PetTag(2, "54");
        PetModels pet = new PetModels(petId, category, petName, Collections.emptyList(), petTag, PetStatus.AVAILABLE);
        petApi.addPet(pet);
        Response response = petApi.getPet(petId, userResponseSpecification200);

        response.then()
                .statusCode(200)
                .body("id", equalTo(petId))
                .body("category.id", equalTo(category.getId()))
                .body("category.name", equalTo(category.getName()))
                .body("name", equalTo(petName))
                .body("tags[0].id", equalTo(petTag.getId()))
                .body("tags[0].name", equalTo(petTag.getName()))
                .body("status", equalTo(PetStatus.AVAILABLE.toString()));
    }

    @DisplayName("Проверка обновления животного")
    @Tag("bi_test")
    @Test
    void updatePetApiTest() {
        int petId = 178;
        Category category = new Category(1232, "555");
        String petName = "doggie";
        String newPetName = "cattie";
        PetTag petTag = new PetTag(2, "54");
        PetModels pet = new PetModels(petId, category, petName, Collections.emptyList(), petTag, PetStatus.AVAILABLE);

        petApi.addPet(pet);

        Map<String, Object> updatedFields = new HashMap<>();
        updatedFields.put("name", newPetName);
        petApi.updatePet(petId, updatedFields, userResponseSpecification200);

        Response response = petApi.getPet(petId, userResponseSpecification200);
        response.then()
                .statusCode(200)
                .body("id", equalTo(petId))
                .body("category.id", equalTo(category.getId()))
                .body("category.name", equalTo(category.getName()))
                .body("name", equalTo(newPetName))
                .body("tags[0].id", equalTo(petTag.getId()))
                .body("tags[0].name", equalTo(petTag.getName()))
                .body("status", equalTo(PetStatus.AVAILABLE.toString()));
    }

    @Test
    @DisplayName("Проверка удаления животного")
    void deletePetApiTest() {
        int petId = 178;
        Category category = new Category(1232, "555");
        PetTag petTag = new PetTag(2, "54");
        PetModels pet = new PetModels(petId, category, "CatTest", Collections.emptyList(), petTag, PetStatus.AVAILABLE);

        petApi.addPet(pet);
        petApi.deletePet(petId, userResponseSpecification200);

        Response getResponse = petApi.getPet(petId, userResponseSpecification404);
        getResponse.then()
                .statusCode(404)
                .body("message", equalTo("Pet not found"));
    }
}
