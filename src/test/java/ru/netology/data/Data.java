package ru.netology.data;

import lombok.Value;

public class Data {
    protected Data() {
    }

    @Value
    public static class AuthInfo {
        protected String login;
        protected String password;
    }

    public static AuthInfo getAuthInfo() {
        return new AuthInfo("vasya", "qwerty123");
    }

    @Value
    public static class VerificationCode {
        protected String code;
    }

    public static VerificationCode getVerificationCode(AuthInfo authInfo) {
        return new VerificationCode("12345");
    }

    @Value
    public static class CardNumber {
        protected String cardId1 = "5559 0000 0000 0001";
        protected String cardId2 = "5559 0000 0000 0002";
    }
}
