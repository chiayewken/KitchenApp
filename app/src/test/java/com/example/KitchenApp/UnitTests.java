package com.example.KitchenApp;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class UnitTests {
    @Test
    public void addition_isCorrect() {
        assertEquals(4, 2 + 2);
    }
    @Test
    public void checkLoginTest() {
        String correctUsername = "username";
        String correctPassword = "password";
        String wrongUsername = "wrong_username";
        String wrongPassword = "wrong_password";
        String trueUsername = "username";
        String truePassword = "password";

        assertTrue(LoginActivity.checkLogin(correctUsername, correctPassword, trueUsername, truePassword));
        assertFalse(LoginActivity.checkLogin(wrongUsername, correctPassword, trueUsername, truePassword));
        assertFalse(LoginActivity.checkLogin(correctUsername, wrongPassword, trueUsername, truePassword));
        assertFalse(LoginActivity.checkLogin(wrongPassword, wrongPassword, trueUsername, truePassword));
    }
}
