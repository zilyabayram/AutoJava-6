package ru.netology.page;


import com.codeborne.selenide.SelenideElement;
import ru.netology.data.DataHelper;

import static com.codeborne.selenide.Selenide.$x;

public class LogInPage {

    private SelenideElement logIn = $x("//input[@name='login']");
    private SelenideElement password = $x("//input[@name='password']");
    private SelenideElement button = $x("//button[@data-test-id='action-login']");

    public VerificationPage sucessfullLogIn(DataHelper.AuthInfo info) {
        logIn.setValue(info.getLogin());
        password.setValue(info.getPassword());
        button.click();
        return new VerificationPage();
    }
}
