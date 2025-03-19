package specs;

import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

import static helpers.CustomApiListener.withCustomTemplates;
import static io.restassured.RestAssured.with;
import static io.restassured.filter.log.LogDetail.ALL;
import static io.restassured.http.ContentType.JSON;

public class UserSpecs {

    public static final RequestSpecification userRequestSpecification = with()
            .filter(withCustomTemplates())
            .log().all()
            .contentType(JSON);

    public static ResponseSpecification userResponseSpecification200 = new ResponseSpecBuilder()
            .expectStatusCode(200)
            .log(ALL)
            .build();


    public static ResponseSpecification userResponseSpecification404 = new ResponseSpecBuilder()
            .expectStatusCode(404)
            .log(ALL)
            .build();
    public static ResponseSpecification userResponseSpecification201 = new ResponseSpecBuilder()
            .expectStatusCode(201)
            .log(ALL)
            .build();
}
