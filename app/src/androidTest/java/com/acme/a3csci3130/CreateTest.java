package com.acme.a3csci3130;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.Rule;
import android.support.test.filters.LargeTest;
import android.support.test.rule.ActivityTestRule;
import static android.support.test.espresso.Espresso.*;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.matcher.ViewMatchers.*;


/**
 * Test for Create functionality. Broken, as Firebase gets pretty angry when this test is run.
 * I am unsure how to properly setup the application context required for this test to work, and I couldn't find helpful resources online.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 * @author Dryden Pick
 */

@RunWith(AndroidJUnit4.class)
@LargeTest
public class CreateTest {

    @Rule
    public ActivityTestRule<CreateBusinessAcitivity> cActivityRule =
            new ActivityTestRule(CreateBusinessAcitivity.class);
    @Test
    public void CreateBusinessTest() {
      //  Context appContext = InstrumentationRegistry.getTargetContext().getApplicationContext().startActivity();

        onView(withId(R.id.number)).perform(typeText("123456789"));
        onView(withId(R.id.name)).perform(typeText("Fishing and CO"));
        onView(withId(R.id.primaryBusiness)).perform(typeText("Fisher"));
        onView(withId(R.id.address)).perform(typeText("23 Happy Lane"));
        onView(withId(R.id.province)).perform(typeText("NS"));

        onView(withId(R.id.submitButton)).perform(click());

    }
}
