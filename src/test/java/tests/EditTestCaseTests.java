package tests;

import com.codeborne.selenide.selector.ByText;
import models.CreateTestCaseResponse;
import models.TestCaseBody;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static configs.AuthConfig.projectId;
import static configs.ProjectConfig.openBaseUrl;
import static io.qameta.allure.Allure.step;
import static io.restassured.RestAssured.given;
import static org.assertj.core.api.Assertions.assertThat;
import static specs.TestOpsSpec.requestSpec;
import static specs.TestOpsSpec.responseSpec;

public class EditTestCaseTests extends TestBase{

    @Test
    @DisplayName("Редактирование тест-кейса")
    void editTestCaseTest(){

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

        step("Добавиляем описание", () -> {
            openBaseUrl();
            $(new ByText(TestData.testCaseName)).click();
            $("section[data-testid='section__description'] button[type='button']").click();
            $("textarea[placeholder='Type a description']").setValue("Some description");
            $("button[name='submit']").click();
        });

        step("Проверка отредактированного кейса", () -> {
            $("section[data-testid='section__description']").shouldHave(text("Some description"));
        });



    }
}
