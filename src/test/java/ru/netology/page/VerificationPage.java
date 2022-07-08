package ru.netology.page;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import ru.netology.data.Data;

import static com.codeborne.selenide.Selenide.$;

public class VerificationPage {
    protected SelenideElement codeField  = $("[data-test-id=code] input");
    protected SelenideElement button = $("[data-test-id=action-verify]");

    public VerificationPage() {
        codeField.shouldBe(Condition.visible);
    }

    public DashboardPage validCode(Data.VerificationCode verificationCode) {
        codeField.setValue(verificationCode.getCode());
        button.click();
        return new DashboardPage();
    }
}
