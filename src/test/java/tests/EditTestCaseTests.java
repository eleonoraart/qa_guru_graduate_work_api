package tests;

import com.codeborne.selenide.selector.ByText;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;
import static configs.ProjectConfig.openBaseUrl;
import static io.qameta.allure.Allure.step;

public class EditTestCaseTests extends TestBase{

    @Test
    @DisplayName("Редактирование тест-кейса")
    void editTestCaseTest() {

        openBaseUrl();

        step("Добавиляем описание", () -> {
            $(new ByText(TestData.testCaseName)).click();
            $("section[data-testid='section__description'] button[type='button']").click();
            $("textarea[placeholder='Type a description']").setValue("Some description");
            $("button[name='submit']").click();
        });

        step("Проверка отредактированного кейса", () -> {
            $("section[data-testid='section__description']").shouldHave(text("Some description"));
        });
    }

    @Test
    @DisplayName("Добавление шагов к тест-кейсу")
    void addStepToTestCaseTest(){

        openBaseUrl();

        step("Добавиляем шаг к тест-кейсу", () -> {
            $(new ByText(TestData.testCaseName)).click();
            $("section[data-testid='section__scenario'] button[type='button']").click();
            $("textarea[placeholder='[keyword] step name']").setValue("Some step");
            $("button[name='submit']").click();
        });

        step("Проверка добавленного шага", () -> {
            $(".TestCaseScenarioStep__name").shouldHave(text("Some step"));
        });

    }

    @Test
    @DisplayName("Редактирование шагов тест-кейса")
    void увшеStepTestCaseTest(){

        openBaseUrl();

        step("Добавиляем шаг к тест-кейсу", () -> {
            $(new ByText(TestData.testCaseName)).click();
            $("section[data-testid='section__scenario'] button[type='button']").click();
            $("textarea[placeholder='[keyword] step name']").setValue("Some step");
            $("button[name='submit']").click();
        });

        step("Редактируем добавленный шаг", () -> {
            $(new ByText(TestData.testCaseName)).click();
            $("section[data-testid='section__scenario'] button[type='button']").click();
            $("textarea[placeholder='[keyword] step name']").setValue(" _ edit");
            $("button[name='submit']").click();
        });

        step("Проверка добавленного шага", () -> {
            $(".TestCaseScenarioStep__name").shouldHave(text("Some step _ edit"));
        });

    }
}

