package tests;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import pages.TestCasesPage;

import static configs.ProjectConfig.openBaseUrl;
import static io.qameta.allure.Allure.step;

@DisplayName("Редактирование тест-кейсов")
public class EditTestCaseTests extends TestBase{

    TestCasesPage testCasesPage = new TestCasesPage();

    @Test
    @DisplayName("Редактирование тест-кейса")
    void editTestCaseTest() {

        openBaseUrl();

        step("Добавиляем описание", () -> {
            testCasesPage.clickToTestCaseName()
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

        openBaseUrl();

        step("Добавиляем шаг к тест-кейсу", () -> {
            testCasesPage.clickToTestCaseName()
                    .clickEditStepButton()
                    .addStep("Some step")
                    .clickSubmitButton();
        });

        step("Проверка добавленного шага", () -> {
            testCasesPage.checkStepName("Some step");
        });

    }

    @Test
    @DisplayName("Редактирование шагов тест-кейса")
    void editStepTestCaseTest(){

        openBaseUrl();

        step("Добавиляем шаг к тест-кейсу", () -> {
            testCasesPage.clickToTestCaseName()
                    .clickEditStepButton()
                    .addStep("Some step")
                    .clickSubmitButton();
        });

        step("Редактируем добавленный шаг", () -> {
            testCasesPage.clickToTestCaseName()
                    .clickEditStepButton()
                    .addStep(" _ edit")
                    .clickSubmitButton();
        });

        step("Проверка отредактированного шага", () -> {
            testCasesPage.checkStepName("Some step _ edit");
        });

    }
}

