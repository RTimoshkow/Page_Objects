package ru.netology.test;

import com.codeborne.selenide.Condition;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import ru.netology.data.Data;
import ru.netology.page.DashboardPage;
import ru.netology.page.LoginPage;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.$;
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
            var dashboardPage = new DashboardPage();
            dashboardPage.validBalans();
        }

        @Test
        void replenishmentOfCard1FromCard2() {
            int transferAmount = 1000;
            var LoginPage = new LoginPage();
            var authInfo = Data.getAuthInfo();
            var verificationPage = LoginPage.validLogin(authInfo);
            var verificationCode = Data.getVerificationCode(authInfo);
            var dashboardPage = verificationPage.validCode(verificationCode);
            int expectedCardBalance1 = dashboardPage.getCard1Balance() + transferAmount;
            int expectedCardBalance2 = dashboardPage.getCard2Balance() - transferAmount;
            var manyTransferPage = dashboardPage.card1();
            var idCard = new Data.CardNumber();
            manyTransferPage.transfer(transferAmount, idCard.getCardId2());
            assertEquals(expectedCardBalance1, dashboardPage.getCard1Balance());
            assertEquals(expectedCardBalance2, dashboardPage.getCard2Balance());
        }

        @Test
        void replenishmentOfCard2FromCard1() {
            int transferAmount = 1500;
            var LoginPage = new LoginPage();
            var authInfo = Data.getAuthInfo();
            var verificationPage = LoginPage.validLogin(authInfo);
            var verificationCode = Data.getVerificationCode(authInfo);
            var dashboardPage = verificationPage.validCode(verificationCode);
            int expectedCardBalance1 = dashboardPage.getCard1Balance() - transferAmount;
            int expectedCardBalance2 = dashboardPage.getCard2Balance() + transferAmount;
            var manyTransferPage = dashboardPage.card2();
            var idCard = new Data.CardNumber();
            manyTransferPage.transfer(transferAmount, idCard.getCardId1());
            assertEquals(expectedCardBalance1, dashboardPage.getCard1Balance());
            assertEquals(expectedCardBalance2, dashboardPage.getCard2Balance());
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
        var idCard = new Data.CardNumber();
        manyTransferPage.transfer(transferAmount, idCard.getCardId2());
        $("[data-test-id=\"error-notification\"]").shouldBe(Condition.visible) ;
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
        var idCard = new Data.CardNumber();
        manyTransferPage.transfer(transferAmount, idCard.getCardId1());
        $("[data-test-id=\"error-notification\"]").shouldBe(Condition.visible) ;
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
        var idCard = new Data.CardNumber();
        manyTransferPage.transfer(transferAmount, idCard.getCardId1());
        $("[data-test-id=\"error-notification\"]").shouldBe(Condition.visible) ;
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
        var idCard = new Data.CardNumber();
        manyTransferPage.transfer(transferAmount, idCard.getCardId2());
        $("[data-test-id=\"error-notification\"]").shouldBe(Condition.visible) ;
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
        manyTransferPage.transfer(transferAmount, "0000 0000 0000 0000");
        $("[data-test-id='error-notification'] [class=\"notification__title\"]").shouldBe(visible);
    }
}
