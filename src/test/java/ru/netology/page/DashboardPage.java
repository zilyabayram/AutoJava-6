package ru.netology.page;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import lombok.val;

import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$$;
import static com.codeborne.selenide.Selenide.$x;

public class DashboardPage {
    private SelenideElement title = $x("//h2[@data-test-id='dashboard']");
    private SelenideElement button = $x("//button[@data-test-id='action-reload']");
    private ElementsCollection cards = $$(".list__item div");
    private final String balanceStart = "баланс: ";
    private final String balanceFinish = " р.";

    public DashboardPage(){
        title.shouldBe(Condition.visible);
    }

    public int getCardBalance(int card) {
        val text = cards.get(card).text();
        return extractBalance(text);
    }

    private int extractBalance(String text) {
        val start = text.indexOf(balanceStart);
        val finish = text.indexOf(balanceFinish);
        val value = text.substring(start + balanceStart.length(), finish);
        return Integer.parseInt(value);
    }

    public TransferPage replenish(int id) {
        cards.get(id).$(byText("Пополнить")).click();
        return new TransferPage();
    }
}
