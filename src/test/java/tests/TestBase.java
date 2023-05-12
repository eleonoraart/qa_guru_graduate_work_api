package tests;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.logevents.SelenideLogger;
import configs.AuthConfig;
import configs.ReaderConfig;
import helpers.Attach;
import io.qameta.allure.selenide.AllureSelenide;
import io.restassured.RestAssured;
import models.CreateTestCaseResponse;
import models.TestCaseBody;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;

import java.io.IOException;

import static configs.AuthConfig.*;
import static io.qameta.allure.Allure.step;
import static io.restassured.RestAssured.given;
import static org.assertj.core.api.Assertions.assertThat;
import static specs.TestOpsSpec.requestSpec;
import static specs.TestOpsSpec.responseSpec;

public class TestBase {

    public static String allureTestOpsSession;
    static AuthConfig authConfig = new AuthConfig();
    public static String testCaseID;
    static TestCaseBody testCaseBody = new TestCaseBody();
    @BeforeAll

    static void authToAllureTestOps() throws IOException {

        authConfig.getAuthConfig();

        Configuration.browser = ReaderConfig.config.getBrowser();
        Configuration.browserVersion = ReaderConfig.config.getBrowserVersion();
        Configuration.browserSize = ReaderConfig.config.getBrowserSize();
        Configuration.remote = ReaderConfig.config.getRemoteDriverUrl();
        Configuration.baseUrl = "https://allure.autotests.cloud";
        RestAssured.baseURI = "https://allure.autotests.cloud";

        allureTestOpsSession = given()
                    .header("X-XSRF-TOKEN", xsrfToken)
                    .header("Cookie", "XSRF-TOKEN=" + xsrfToken)
                    .formParam("username", username)
                    .formParam("password", password)
                    .when()
                    .post("/api/login/system")
                    .then()
                    .statusCode(200)
                    .extract().response()
                    .getCookie("ALLURE_TESTOPS_SESSION");

        testCaseBody.setName(TestData.testCaseName);

    }

    @BeforeEach
    void addListener () {
        SelenideLogger.addListener("AllureSelenide", new AllureSelenide());

        CreateTestCaseResponse testCaseResponse = step("Cоздание тест-кейса", () ->
                given(requestSpec)
                        .body(testCaseBody)
                        .queryParam("projectId", projectId)
                        .when()
                        .post("/testcasetree/leaf")
                        .then()
                        .spec(responseSpec)
                        .statusCode(200).extract().as(CreateTestCaseResponse.class));

        step("Проверка создания тест-кейса", () -> {
            assertThat(testCaseResponse.getName()).isEqualTo(TestData.testCaseName);
        });
    }

    @AfterEach
    void addAttachments () {
        Attach.screenshotAs("Last screenshot");
        Attach.pageSource();
        Attach.browserConsoleLogs();
        Attach.addVideo();
    }
}
