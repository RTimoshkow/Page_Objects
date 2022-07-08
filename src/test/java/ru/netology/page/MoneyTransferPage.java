package ru.netology.page;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.Keys;
import ru.netology.data.Data;

import static com.codeborne.selenide.Selenide.$;

public class MoneyTransferPage {

    protected SelenideElement heading = $("[data-test-id=\"dashboard\"]");
    protected SelenideElement sum = $("[data-test-id=\"amount\"] input");
    protected SelenideElement from = $("[data-test-id=\"from\"] input");
    protected SelenideElement to = $("[data-test-id=\"to\"]");
    protected SelenideElement resumeButton = $("[data-test-id=\"action-transfer\"]");

    public MoneyTransferPage() {
        heading.shouldBe(Condition.visible);
        sum.shouldBe(Condition.visible);
        from.shouldBe(Condition.visible);
        to.shouldBe(Condition.visible);
        resumeButton.shouldBe(Condition.visible);
    }

    public void transfer(int amount, String cardNumber) {
        sum.sendKeys(Keys.CONTROL, "a", Keys.DELETE);
        sum.val(String.valueOf(amount));
        from.sendKeys(Keys.CONTROL, "a", Keys.DELETE);
        from.val(cardNumber);
        resumeButton.click();
    }

}
