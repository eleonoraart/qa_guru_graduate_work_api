package configs;

import org.openqa.selenium.Cookie;
import tests.TestBase;

import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.Selenide.sleep;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;
import static configs.AuthConfig.projectId;


public class ProjectConfig extends TestBase {

    public static void openBaseUrl() {
        open("/favicon.ico");
        Cookie authorizationCookie = new Cookie("ALLURE_TESTOPS_SESSION", allureTestOpsSession);
        getWebDriver().manage().addCookie(authorizationCookie);
        open(String.format("/project/%s/test-cases/%s", projectId, testCaseID));
        sleep(3000);
    }
}
