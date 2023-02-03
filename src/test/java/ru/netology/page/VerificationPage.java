package ru.netology.page;

import com.codeborne.selenide.SelenideElement;
import ru.netology.data.DataHelper;

import static com.codeborne.selenide.Selenide.$x;

public class VerificationPage {
    private SelenideElement title = $x("//h2[contains(.,'Интернет Банк')]");
    private SelenideElement input = $x("//input[@name='code']");
    private SelenideElement button = $x("//span[@class='button__content']");

    public DashboardPage typeCode(DataHelper.VerificationCode info){
        input.setValue(info.getCode());
        button.click();
        return new DashboardPage();
    }
}
