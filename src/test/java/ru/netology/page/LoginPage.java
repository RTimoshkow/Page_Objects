package ru.netology.page;

import com.codeborne.selenide.SelenideElement;
import ru.netology.data.Data;

import static com.codeborne.selenide.Selenide.$;

public class LoginPage {
    protected SelenideElement loginField = $("[data-test-id=login] input");
    protected SelenideElement passwordField = $("[data-test-id=password] input");
    protected SelenideElement button = $("[data-test-id=action-login]");

    public VerificationPage validLogin(Data.AuthInfo info) {
        loginField.setValue(info.getLogin());
        passwordField.setValue(info.getPassword());
        button.click();
        return new VerificationPage();
    }
}
