package specs;

import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

import static configs.AuthConfig.token;
import static helpers.CustomApiListener.withCustomTemplates;
import static io.restassured.RestAssured.with;
import static io.restassured.filter.log.LogDetail.BODY;
import static io.restassured.filter.log.LogDetail.STATUS;
import static io.restassured.http.ContentType.JSON;
import static tests.TestBase.allureTestOpsSession;

public class TestCaseSpec {

    public static RequestSpecification requestStepSpec = with()
            .header("Authorization", "Api-Token " + token)
            .cookie("ALLURE_TESTOPS_SESSION", allureTestOpsSession)
            .baseUri("https://allure.autotests.cloud")
            .basePath("/api/rs")
            .log().all()
            .filter(withCustomTemplates())
            .contentType(JSON);


    public static ResponseSpecification responseStepSpec = new ResponseSpecBuilder().
            log(STATUS).
            log(BODY).
            build();
}
