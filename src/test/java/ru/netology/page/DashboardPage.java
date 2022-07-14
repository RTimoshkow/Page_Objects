package ru.netology.page;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import lombok.val;
import ru.netology.data.Data;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class DashboardPage {

    protected SelenideElement heading = $("[data-test-id=dashboard]");
    protected SelenideElement fieldFirstCard = $("[data-test-id=\"92df3f1c-a033-48e6-8390-206f6b1f56c0\"]");
    protected SelenideElement fieldSecondCard = $("[data-test-id=\"0f3f5c2a-249e-4c3d-8287-09f7a039391d\"]");
    protected ElementsCollection button = $$("[data-test-id=\"action-deposit\"]");

    private final String balanceStart = "баланс: ";
    private final String balanceFinish = " р.";

    public DashboardPage() {
        heading.shouldBe(visible);
        fieldFirstCard.shouldBe(visible);
        fieldSecondCard.shouldBe(visible);
    }

    public MoneyTransferPage card1() {
        button.first().click();
        return new MoneyTransferPage();
    }

    public MoneyTransferPage card2() {
        button.last().click();
        return new MoneyTransferPage();
    }

    public int getCardBalance(String id) {
        return extractBalance($("[data-test-id='" + id + "']").getText());
    }

    private int extractBalance(String text) {
        val start = text.indexOf(balanceStart);
        val finish = text.indexOf(balanceFinish);
        val value = text.substring(start + balanceStart.length(), finish);
        return Integer.parseInt(value);
    }


}
