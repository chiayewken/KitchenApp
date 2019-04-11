package com.example.KitchenApp;

import android.content.SharedPreferences;
import android.support.test.espresso.intent.Intents;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.clearText;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.intent.Intents.intended;
import static android.support.test.espresso.intent.matcher.IntentMatchers.hasComponent;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

@RunWith(AndroidJUnit4.class)
public class BlockedActivityTest {
    @Rule
    public final ActivityTestRule<BlockedActivity> activityTestRule = new ActivityTestRule<>(BlockedActivity.class);
    @Before
    public void init(){
        activityTestRule.getActivity()
                .getSupportFragmentManager().beginTransaction();
        Intents.init();
    }
    @After
    public void release() {
        Intents.release();
    }
    @Test
    public void testLoginSuccess(){
        onView(withId(R.id.editUsername)).perform(typeText("username"), closeSoftKeyboard());
        onView(withId(R.id.editPassword)).perform(typeText("password"), closeSoftKeyboard());
        onView(withId(R.id.buttonLogin)).perform(click());
        intended(hasComponent(MainActivity.class.getName()));
        assertTrue(LoginActivity.loginSuccess);
        assertFalse(LoginActivity.loginLocked);
    }
    @Test
    public void testLoginFail(){
        onView(withId(R.id.editUsername)).perform(typeText("hello"), closeSoftKeyboard());
        onView(withId(R.id.editPassword)).perform(typeText("hello"), closeSoftKeyboard());
        onView(withId(R.id.buttonLogin)).perform(click());
        assertFalse(LoginActivity.loginSuccess);
        assertFalse(LoginActivity.loginLocked);
    }
    @Test
    public void testLoginLocked() {
        for (int i = 0; i < LoginActivity.failCountLimit; i++) {
            assertFalse(LoginActivity.loginLocked);
            onView(withId(R.id.editUsername)).perform(clearText());
            onView(withId(R.id.editPassword)).perform(clearText());
            onView(withId(R.id.editUsername)).perform(typeText("hello"), closeSoftKeyboard());
            onView(withId(R.id.editPassword)).perform(typeText("hello"), closeSoftKeyboard());
            onView(withId(R.id.buttonLogin)).perform(click());
            assertFalse(LoginActivity.loginSuccess);
        }
        onView(withId(R.id.buttonLogin)).perform(click());
        assertTrue(LoginActivity.loginLocked);
    }
}
