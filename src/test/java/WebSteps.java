import com.codeborne.selenide.WebDriverRunner;
import io.qameta.allure.Attachment;
import io.qameta.allure.Step;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class WebSteps {
    @Step("Открываем главную страницу")
    public void openMainPage(){
        open("/");
    }

    @Step("Ищем репозиторий {repo}")
    public void searchRepository(String repo){
        $(".search-input").click();
        $("#query-builder-test").setValue(repo).pressEnter();
    }

    @Step("Переходим в репозиторий {repo}")
    public void openRepository(String repo){
        $("a[href='/AlexeyZakirov/" + repo + "']").click();
    }

    @Step("Переходим в таб Issues")
    public void openIssueTab(){
        $("#issues-tab").click();
    }

    @Step("Проверяем Issue с названием {issue}")
    public void checkIssue(String issue){
        $("div[aria-label='Issues']").shouldHave(text(issue));
    }

    @Attachment(value = "Screenshot", type = "image/png", fileExtension = "png")
    public byte[] takeScreenshot(){
        return ((TakesScreenshot)WebDriverRunner.getWebDriver()).getScreenshotAs(OutputType.BYTES);
    }
}
