import io.qameta.allure.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.*;
import static io.qameta.allure.Allure.attachment;
import static io.qameta.allure.Allure.step;

@Owner("AlexeyZakirov")
@Feature("Issue in repo")
@Story("Создание Issue")
@Link(value = "GitHub", url = "https://github.com/AlexeyZakirov/WB-parameterized_tests")
public class AllureTests extends TestBase{
    private final static String REPOSITORY = "WB-parameterized_tests";
    private final static String ISSUE = "WB-parameterized_tests";
    WebSteps webSteps = new WebSteps();

    @Severity(SeverityLevel.BLOCKER)
    @DisplayName("Listener. Название Issue в репозитории " + REPOSITORY + " совпадает с ожидаемым")
    @Test
    public void listenerTest(){
        open("/");
        $(".search-input").click();
        $("#query-builder-test").setValue(REPOSITORY).pressEnter();
        $("a[href='/AlexeyZakirov/" + REPOSITORY + "']").click();
        $("#issues-tab").click();

        $("div[aria-label='Issues']").shouldHave(text(ISSUE));
    }

    @Severity(SeverityLevel.BLOCKER)
    @DisplayName("Lambda. Название Issue в репозитории " + REPOSITORY + " совпадает с ожидаемым")
    @Test
    public void lambdaStepTest(){
        step("Открываем главную страницу", () -> {
            open("/");

            attachment("Source", webdriver().driver().source());
        });

        step("Ищем репозиторий " + REPOSITORY, () -> {
            $(".search-input").click();
            $("#query-builder-test").setValue("WB-parameterized_tests").pressEnter();
        });

        step("Переходим в репозиторий "+ REPOSITORY, () -> {
            $("a[href='/AlexeyZakirov/WB-parameterized_tests']").click();
        });

        step("Переходим в таб Issues", () -> {
            $("#issues-tab").click();
        });

        step("Проверяем Issue с названием " + ISSUE, () -> {
            $("div[aria-label='Issues']").shouldHave(text("HomeworkAllureTests"));
        });
    }

    @Severity(SeverityLevel.BLOCKER)
    @DisplayName("Steps. Название Issue в репозитории " + REPOSITORY + " совпадает с ожидаемым")
    @Test
    public void stepsTest(){
        webSteps.openMainPage();
        webSteps.takeScreenshot();
        webSteps.searchRepository(REPOSITORY);
        webSteps.openRepository(REPOSITORY);
        webSteps.openIssueTab();
        webSteps.checkIssue(ISSUE);
    }
}
