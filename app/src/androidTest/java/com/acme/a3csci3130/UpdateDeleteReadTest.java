package com.acme.a3csci3130;

import android.content.Context;
import android.content.Intent;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.Rule;
import android.support.test.filters.LargeTest;
import android.support.test.rule.ActivityTestRule;

import com.google.firebase.database.FirebaseDatabase;

import static android.support.test.espresso.Espresso.*;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.matcher.ViewMatchers.*;
import static android.support.test.espresso.assertion.ViewAssertions.matches;


/**
 * Tests for Update, Read and Delete. These tests do not function as I can not determine how to interact with Firebase using expresso tests.
 *
 * @Author Dryden
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */

@RunWith(AndroidJUnit4.class)
@LargeTest
public class UpdateDeleteReadTest {

    @Rule
    public ActivityTestRule<DetailViewActivity> dActivityRule =
            new ActivityTestRule(DetailViewActivity.class);


    @Test
    public void ReadBusinessTest() {
        //Ideally, I would check whats displayed in Textfields and compare to what is recalled from the database.
        onView(withId(R.id.number)).check(matches(withText("")));
        onView(withId(R.id.name)).check(matches(withText("")));
        onView(withId(R.id.address)).check(matches(withText("")));
        onView(withId(R.id.province)).check(matches(withText("")));
        onView(withId(R.id.primaryBusiness)).check(matches(withText("")));
    }

    @Test
    public void UpdateBusinessTest() {
        onView(withId(R.id.number)).perform(typeText("123456789"));
        onView(withId(R.id.name)).perform(typeText("Fishing and CO"));
        onView(withId(R.id.primaryBusiness)).perform(typeText("Fisher"));
        onView(withId(R.id.address)).perform(typeText("23 Happy Lane"));
        onView(withId(R.id.province)).perform(typeText("NS"));

        onView(withId(R.id.updateButton)).perform(click());
    }

    @Test
    public void DeleteBusinessTest() {
        onView(withId(R.id.deleteButton)).perform(click());
    }
}
