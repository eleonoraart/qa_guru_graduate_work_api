package pages;

import com.codeborne.selenide.selector.ByText;
import io.qameta.allure.Step;
import tests.TestData;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;

public class TestCasesPage {

    @Step("Нажать на тест-кейс")
    public TestCasesPage clickToTestCaseName(){

        $(new ByText(TestData.testCaseName)).click();

        return this;
    }

    @Step("Нажать на кнопку редактирования описания")
    public TestCasesPage clickEditDescriptionButton(){

        $("section[data-testid='section__description'] button[type='button']").click();

        return this;
    }
    @Step("Нажать на кнопку редактирования шагов")
    public TestCasesPage clickEditStepButton(){

        $("section[data-testid='section__scenario'] button[type='button']").click();

        return this;
    }

    @Step("Добавить описание")
    public TestCasesPage addDescription(String description){

        $("textarea[placeholder='Type a description']").setValue(description);

        return this;
    }

    @Step("Добавить шаг")
    public TestCasesPage addStep(String step){

        $("textarea[placeholder='[keyword] step name']").setValue(step);

        return this;
    }

    @Step("Нажать на кнопку подтверждения")
    public TestCasesPage clickSubmitButton(){

        $("button[name='submit']").click();

        return this;
    }
    @Step("Проверка имени тест-кейса")
    public TestCasesPage checkTestCaseName(String description){

        $("section[data-testid='section__description']").shouldHave(text(description));

        return this;
    }
    @Step("Проверка имени шага")
    public TestCasesPage checkStepName(String step){

        $(".TestCaseScenarioStep__name").shouldHave(text(step));

        return this;
    }

}
