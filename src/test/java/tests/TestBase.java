package tests;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.logevents.SelenideLogger;
import configs.AuthConfig;
import configs.ReaderConfig;
import helpers.Attach;
import io.qameta.allure.selenide.AllureSelenide;
import io.restassured.RestAssured;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;

import java.io.IOException;

import static configs.AuthConfig.*;
import static io.restassured.RestAssured.given;

public class TestBase {

    public static String allureTestOpsSession;
    static AuthConfig authConfig = new AuthConfig();
    public static String testCaseID;
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
        }


    @BeforeEach
    void addListener () {
        SelenideLogger.addListener("AllureSelenide", new AllureSelenide());
    }

    @AfterEach
    void addAttachments () {
        Attach.screenshotAs("Last screenshot");
        Attach.pageSource();
        Attach.browserConsoleLogs();
        Attach.addVideo();
    }
}
