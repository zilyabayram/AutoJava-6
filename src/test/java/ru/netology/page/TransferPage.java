package ru.netology.page;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.Keys;

import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$x;

public class TransferPage {

    private SelenideElement title = $x("//h2[@data-test-id='dashboard']");
    private SelenideElement amountInput = $("[data-test-id='amount'] input");
    private SelenideElement fromInput = $("[data-test-id='from'] input");
    private SelenideElement button1 = $x("//button[@data-test-id='action-transfer']");
    private SelenideElement button2 = $x("//button[@data-test-id='action-cancel']");
    private SelenideElement errorMessage = $x("//div[@data-test-id='error-notification']");


    public TransferPage() {
        title.shouldBe(Condition.visible);
    }

    public void errorMessage() {
        errorMessage.shouldHave(exactText("Ошибка!"))
                .shouldBe(Condition.visible);
    }

    public DashboardPage transferMoney(String cardFrom, int sum) {
        amountInput.sendKeys(Keys.LEFT_CONTROL + "A");
        amountInput.sendKeys(Keys.BACK_SPACE);
        amountInput.setValue(String.valueOf(sum));
        fromInput.sendKeys(Keys.LEFT_CONTROL + "A");
        fromInput.sendKeys(Keys.BACK_SPACE);
        fromInput.setValue(cardFrom);
        button1.click();
        return new DashboardPage();
    }

}
