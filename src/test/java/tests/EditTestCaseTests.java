package tests;

import models.CreateTestCaseResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import pages.TestCasesPage;

import static configs.AuthConfig.projectId;
import static configs.ProjectConfig.openBaseUrl;
import static io.qameta.allure.Allure.step;
import static io.restassured.RestAssured.given;
import static org.assertj.core.api.Assertions.assertThat;
import static specs.TestOpsSpec.requestSpec;
import static specs.TestOpsSpec.responseSpec;

@DisplayName("Редактирование тест-кейсов")
public class EditTestCaseTests extends TestBase{

    TestCasesPage testCasesPage = new TestCasesPage();

    @BeforeEach
    void createTestCase(){
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

        openBaseUrl();
    }

    @Test
    @DisplayName("Редактирование тест-кейса")
    void editTestCaseTest() {

        step("Добавиляем описание", () -> {
            testCasesPage.clickToTestCaseName(TestData.testCaseName)
                            .clickEditDescriptionButton()
                            .addDescription("Some description")
                            .clickSubmitButton();
        });

        step("Проверка отредактированного кейса", () -> {
            testCasesPage.checkTestCaseName("Some description");
        });
    }

    @Test
    @DisplayName("Добавление шагов к тест-кейсу")
    void addStepToTestCaseTest(){

        step("Добавиляем шаг к тест-кейсу", () -> {
            testCasesPage.clickToTestCaseName(TestData.testCaseName)
                    .clickEditStepButton()
                    .addStep(TestData.nameStepTestCase)
                    .clickSubmitButton();
        });

        step("Проверка добавленного шага", () -> {
            testCasesPage.checkStepName(TestData.nameStepTestCase);
        });

    }

    @Test
    @DisplayName("Редактирование шагов тест-кейса")
    void editStepTestCaseTest(){

        step("Добавиляем шаг к тест-кейсу", () -> {
            testCasesPage.clickToTestCaseName(TestData.testCaseName)
                    .clickEditStepButton()
                    .addStep(TestData.nameStepTestCase)
                    .clickSubmitButton();
        });

        step("Редактируем добавленный шаг", () -> {
            testCasesPage.clickToTestCaseName(TestData.testCaseName)
                    .clickEditStepButton()
                    .addStep(" _ edit")
                    .clickSubmitButton();
        });

        step("Проверка отредактированного шага", () -> {
            testCasesPage.checkStepName(TestData.nameStepTestCase + " _ edit");
        });

    }
}

