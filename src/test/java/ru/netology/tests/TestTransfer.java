package ru.netology.tests;

import org.junit.jupiter.api.Test;
import ru.netology.data.DataHelper;
import ru.netology.page.LogInPage;

import static com.codeborne.selenide.Selenide.open;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestTransfer {


    public class TransferTest {
        private int card1 = 0;
        private int card2 = 1;

        @Test
        void shouldTransferMoneyFromCard1ToCard2() {
            open("http://localhost:9999");
            var loginPage = new LogInPage();
            var authInfo = DataHelper.getAuthInfo();
            var verificationPage = loginPage.sucessfullLogIn(authInfo);
            var verificationCode = DataHelper.getVerificationCodeFor(authInfo);
            var dashboardPage = verificationPage.typeCode(verificationCode);

            int balanceOfCard1 = dashboardPage.getCardBalance(0);
            int balanceOfCard2 = dashboardPage.getCardBalance(1);
            var transferPage = dashboardPage.replenish(card2);
            int amount = 5000;
            transferPage.transferMoney(DataHelper.getFirstCardInfo().getCardNumber(), amount);
            assertEquals(balanceOfCard1 - amount, dashboardPage.getCardBalance(card1));
            assertEquals(balanceOfCard2 + amount, dashboardPage.getCardBalance(card2));
        }

        @Test
        void shouldTransferMoneyFromCard2ToCard1() {
            open("http://localhost:9999");
            var loginPage = new LogInPage();
            var authInfo = DataHelper.getAuthInfo();
            var verificationPage = loginPage.sucessfullLogIn(authInfo);
            var verificationCode = DataHelper.getVerificationCodeFor(authInfo);
            var dashboardPage = verificationPage.typeCode(verificationCode);

            int balanceOfCard1 = dashboardPage.getCardBalance(0);
            int balanceOfCard2 = dashboardPage.getCardBalance(1);
            var transferPage = dashboardPage.replenish(card1);
            int amount = 5000;
            transferPage.transferMoney(DataHelper.getFirstCardInfo().getCardNumber(), amount);
            assertEquals(balanceOfCard1 - amount, dashboardPage.getCardBalance(card2));
            assertEquals(balanceOfCard2 + amount, dashboardPage.getCardBalance(card1));
        }

    }
}
