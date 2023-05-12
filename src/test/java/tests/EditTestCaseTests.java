package tests;

import com.codeborne.selenide.selector.ByText;
import models.CreateTestCaseResponse;
import models.TestCaseBody;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;
import static configs.AuthConfig.projectId;
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

        step("Изменение имени тест-кейса", () -> {
            $(new ByText(TestData.testCaseName)).hover();
            $(".Button.Button_size_tiny.Button_style_row-control.Button_shape_rectangular").click();
            $("").setValue(" edit").pressEnter();
        });

        step("Проверка нового имени тест-кейса", () -> {
            $("a[class='LoadableTreeNodeView LoadableTreeNodeView_active'] div[class='TreeNodeName']").shouldHave(text(TestData.testCaseName + " edit"));
        });



    }
}
