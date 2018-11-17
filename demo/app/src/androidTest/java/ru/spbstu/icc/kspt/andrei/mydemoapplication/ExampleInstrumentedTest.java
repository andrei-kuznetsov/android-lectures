package ru.spbstu.icc.kspt.andrei.mydemoapplication;

import android.os.RemoteException;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import androidx.test.espresso.Espresso;
import androidx.test.espresso.assertion.ViewAssertions;
import androidx.test.espresso.matcher.ViewMatchers;
import androidx.test.rule.ActivityTestRule;
import androidx.test.runner.AndroidJUnit4;
import androidx.test.uiautomator.UiDevice;

import static androidx.test.espresso.Espresso.onData;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.isRoot;
import static androidx.test.platform.app.InstrumentationRegistry.getInstrumentation;
import static org.hamcrest.Matchers.is;

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class ExampleInstrumentedTest {
    @Rule
    public ActivityTestRule<ListActivity> mActivityRule
            = new ActivityTestRule<>(ListActivity.class);

    @Test
    public void rotationTest() throws InterruptedException, RemoteException {
        UiDevice device = UiDevice.getInstance(getInstrumentation());
        device.setOrientationNatural();

        onView(ViewMatchers.withId(R.id.list_fragment)).check(ViewAssertions.matches(isDisplayed()));
        onData(is("Item1")).perform(click());

        onView(ViewMatchers.withId(R.id.detailsText)).check(ViewAssertions.matches(ViewMatchers.withText("Item1")));

        device.setOrientationLeft();

        getInstrumentation().waitForIdleSync();
        onView(ViewMatchers.withId(R.id.list_fragment)).check(ViewAssertions.matches(isDisplayed()));
    }
}
