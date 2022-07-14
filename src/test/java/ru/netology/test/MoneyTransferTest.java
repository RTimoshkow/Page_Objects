package ru.netology.test;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import ru.netology.data.Data;
import ru.netology.page.DashboardPage;
import ru.netology.page.LoginPage;
import ru.netology.page.MoneyTransferPage;

import static com.codeborne.selenide.Selenide.open;
import static org.junit.jupiter.api.Assertions.assertEquals;


public class MoneyTransferTest {


    @BeforeEach
    void openBrowser() {
        open("http://localhost:9999");
    }


    @Nested
    public class SuccessfulTranslations {
        @AfterEach
        void alignmentOfTheBalance() {
            DashboardPage dashboardPage = new DashboardPage();
            int balans = 10000;
            int cardBalance1 = dashboardPage.getCardBalance(Data.getRegisteredCard1().getDataTestId());
            int cardBalance2 = dashboardPage.getCardBalance(Data.getRegisteredCard2().getDataTestId());
            int remains;
            if (cardBalance1 > balans) {
                remains = cardBalance1 - balans;
                dashboardPage.card2();
                var transfer = new MoneyTransferPage();
                var idCard = Data.getRegisteredCard1();
                transfer.transfer(remains, idCard.getCardId());
            }
            if (cardBalance2 > balans) {
                remains = cardBalance2 - balans;
                dashboardPage.card1();
                var transfer = new MoneyTransferPage();
                var idCard = Data.getRegisteredCard2();
                transfer.transfer(remains, idCard.getCardId());
            }

        }

        @Test
        void replenishmentOfCard1FromCard2() {

            int transferAmount = 1000;
            var LoginPage = new LoginPage();
            var authInfo = Data.getAuthInfo();
            var verificationPage = LoginPage.validLogin(authInfo);
            var verificationCode = Data.getVerificationCode(authInfo);
            verificationPage.validCode(verificationCode);
            var dashboardPage = new DashboardPage();
            int expectedCardBalance1 = dashboardPage.getCardBalance(Data.getRegisteredCard1().getDataTestId()) + transferAmount;
            int expectedCardBalance2 = dashboardPage.getCardBalance(Data.getRegisteredCard2().getDataTestId()) - transferAmount;
            var manyTransferPage = dashboardPage.card1();
            var idCard = Data.getRegisteredCard2();
            manyTransferPage.transfer(transferAmount, idCard.getCardId());
            assertEquals(expectedCardBalance1, dashboardPage.getCardBalance(Data.getRegisteredCard1().getDataTestId()));
            assertEquals(expectedCardBalance2, dashboardPage.getCardBalance(Data.getRegisteredCard2().getDataTestId()));
        }


        @Test
        void replenishmentOfCard2FromCard1() {
            int transferAmount = 1500;
            var LoginPage = new LoginPage();
            var authInfo = Data.getAuthInfo();
            var verificationPage = LoginPage.validLogin(authInfo);
            var verificationCode = Data.getVerificationCode(authInfo);
            verificationPage.validCode(verificationCode);
            var dashboardPage = new DashboardPage();
            int expectedCardBalance1 = dashboardPage.getCardBalance(Data.getRegisteredCard1().getDataTestId()) - transferAmount;
            int expectedCardBalance2 = dashboardPage.getCardBalance(Data.getRegisteredCard2().getDataTestId()) + transferAmount;
            var manyTransferPage = dashboardPage.card2();
            var idCard = Data.getRegisteredCard1();
            manyTransferPage.transfer(transferAmount, idCard.getCardId());
            assertEquals(expectedCardBalance1, dashboardPage.getCardBalance(Data.getRegisteredCard1().getDataTestId()));
            assertEquals(expectedCardBalance2, dashboardPage.getCardBalance(Data.getRegisteredCard2().getDataTestId()));
        }
    }


    @Test
    void transferOfInvalidAmountFromCard1FromCard2() {
        int transferAmount = 15000;
        var LoginPage = new LoginPage();
        var authInfo = Data.getAuthInfo();
        var verificationPage = LoginPage.validLogin(authInfo);
        var verificationCode = Data.getVerificationCode(authInfo);
        var dashboardPage = verificationPage.validCode(verificationCode);
        var manyTransferPage = dashboardPage.card1();
        var idCard = Data.getRegisteredCard2();
        manyTransferPage.transfer(transferAmount, idCard.getCardId());
        manyTransferPage.error();
    }


    @Test
    void transferOfInvalidAmountFromCard2FromCard1() {
        int transferAmount = 15000;
        var LoginPage = new LoginPage();
        var authInfo = Data.getAuthInfo();
        var verificationPage = LoginPage.validLogin(authInfo);
        var verificationCode = Data.getVerificationCode(authInfo);
        var dashboardPage = verificationPage.validCode(verificationCode);
        var manyTransferPage = dashboardPage.card2();
        var idCard = Data.getRegisteredCard1();
        manyTransferPage.transfer(transferAmount, idCard.getCardId());
        manyTransferPage.error();
    }

    @Test
    void transferOfInvalidAmountFromCard1FromCard1() {
        int transferAmount = 1000;
        var LoginPage = new LoginPage();
        var authInfo = Data.getAuthInfo();
        var verificationPage = LoginPage.validLogin(authInfo);
        var verificationCode = Data.getVerificationCode(authInfo);
        var dashboardPage = verificationPage.validCode(verificationCode);
        var manyTransferPage = dashboardPage.card1();
        var idCard = Data.getRegisteredCard1();
        manyTransferPage.transfer(transferAmount, idCard.getCardId());
        manyTransferPage.error();
    }

    @Test
    void transferOfInvalidAmountFromCard2FromCard2() {
        int transferAmount = 1000;
        var LoginPage = new LoginPage();
        var authInfo = Data.getAuthInfo();
        var verificationPage = LoginPage.validLogin(authInfo);
        var verificationCode = Data.getVerificationCode(authInfo);
        var dashboardPage = verificationPage.validCode(verificationCode);
        var manyTransferPage = dashboardPage.card2();
        var idCard = Data.getRegisteredCard2();
        manyTransferPage.transfer(transferAmount, idCard.getCardId());
        manyTransferPage.error();
    }

    @Test
    void shouldInvalidCard() {
        int transferAmount = 1500;
        var LoginPage = new LoginPage();
        var authInfo = Data.getAuthInfo();
        var verificationPage = LoginPage.validLogin(authInfo);
        var verificationCode = Data.getVerificationCode(authInfo);
        var dashboardPage = verificationPage.validCode(verificationCode);
        var manyTransferPage = dashboardPage.card2();
        var idCard = Data.getNotRegisteredCard();
        manyTransferPage.transfer(transferAmount, idCard.getCardId());
        manyTransferPage.error();
    }


}