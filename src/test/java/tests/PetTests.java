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
import java.util.List;
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
        int petId = 180;
        Category category = new Category(1232, "555");
        String petName = "doggie";
        PetTag petTag = new PetTag(2, "54");
        List<PetTag> tags = Collections.singletonList(petTag);
        PetModels pet = new PetModels(petId, category, petName, Collections.emptyList(), tags, PetStatus.AVAILABLE);
        petApi.deleteFirstPet(petId,userResponseSpecification200);
        Response response = petApi.addPet(pet,userResponseSpecification200);

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
        int petId = 180;
        Category category = new Category(1232, "555");
        String petName = "doggie";
        PetTag petTag = new PetTag(2, "54");
        List<PetTag> tags = Collections.singletonList(petTag);
        PetModels pet = new PetModels(petId, category, petName, Collections.emptyList(), tags, PetStatus.AVAILABLE);
        petApi.deleteFirstPet(petId,userResponseSpecification200);
        petApi.addPet(pet,userResponseSpecification200);
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
        int petId = 180;
        Category category = new Category(1232, "555");
        String petName = "doggie";
        String newPetName = "cattie";
        PetTag petTag = new PetTag(2, "54");
        List<PetTag> tags = Collections.singletonList(petTag);
        PetModels pet = new PetModels(petId, category, petName, Collections.emptyList(), tags, PetStatus.AVAILABLE);
        petApi.deleteFirstPet(petId,userResponseSpecification200);
        petApi.addPet(pet,userResponseSpecification200);

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
    @DisplayName("Добавление и удаление животного")
    void createAndDeletePetTest() {
        int petId = 180;
        PetModels pet = PetModels.builder()
                .id(petId)
                .category(new Category(1232, "555"))
                .name("CatTest")
                .photoUrls(List.of("http://photo.url"))
                .tags(List.of(new PetTag(2, "54")))
                .status(PetStatus.AVAILABLE)
                .build();
        petApi.deleteFirstPet(petId,userResponseSpecification200);
        petApi.addPet(pet,userResponseSpecification200);
        petApi.deletePet(petId, userResponseSpecification200);

        Response getResponse = petApi.getPet(petId, userResponseSpecification404);
        getResponse.then()
                .statusCode(404)
                .body("message", equalTo("Pet not found"));
    }
}
