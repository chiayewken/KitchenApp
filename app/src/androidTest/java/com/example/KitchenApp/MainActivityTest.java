package com.example.KitchenApp;

import android.support.test.espresso.intent.Intents;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static java.lang.Thread.sleep;
import static org.junit.Assert.assertEquals;

@RunWith(AndroidJUnit4.class)
public class MainActivityTest {
    @Rule
    public final ActivityTestRule<MainActivity> activityTestRule = new ActivityTestRule<>(MainActivity.class);
    @Before
    public void init(){
        activityTestRule.getActivity()
                .getSupportFragmentManager().beginTransaction();
        Intents.init();
    }
    @Test
    public void testCreateOrders(){
        MainActivity.clearOrders(MainActivity.dbDeliveryList);
        MainActivity.createExampleOrders(MainActivity.dbDeliveryList);
        try {
            sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        assertEquals(MainActivity.foodOrders.size(), 3);
    }
    @After
    public void release() {
        Intents.release();
    }

}
