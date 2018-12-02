package ru.spbstu.icc.kspt.andrei.mydemoapplication;

import android.os.RemoteException;

import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import androidx.test.espresso.Espresso;
import androidx.test.espresso.action.ViewActions;
import androidx.test.espresso.assertion.ViewAssertions;
import androidx.test.espresso.matcher.ViewMatchers;
import androidx.test.platform.app.InstrumentationRegistry;
import androidx.test.rule.ActivityTestRule;
import androidx.test.runner.AndroidJUnit4;
import androidx.test.uiautomator.UiDevice;

import static org.hamcrest.Matchers.is;

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class ExampleInstrumentedTest {

    @Rule
    public ActivityTestRule<ArticleListActivity> mActivityRule
            = new ActivityTestRule<>(ArticleListActivity.class);

    @Test
    public void itemSelectedPort() throws RemoteException {
        UiDevice.getInstance(InstrumentationRegistry.getInstrumentation()).setOrientationNatural();
        Espresso.onData(is("Item1"))
                .perform(ViewActions.click());

        Espresso.onView(ViewMatchers.withId(R.id.details_text))
                .check(ViewAssertions.matches(ViewMatchers.withText("Item1")));
    }

    @Test
    public void itemSelectedLand() throws RemoteException {
        UiDevice.getInstance(InstrumentationRegistry.getInstrumentation()).setOrientationLeft();
        Espresso.onData(is("Item1"))
                .perform(ViewActions.click());

        Espresso.onView(ViewMatchers.withId(R.id.details_text))
                .check(ViewAssertions.matches(ViewMatchers.withText("Item1")));
    }

    @Test
    public void rotationTest() throws RemoteException {
        UiDevice.getInstance(InstrumentationRegistry.getInstrumentation()).setOrientationNatural();

        Espresso.onData(is("Item1"))
                .perform(ViewActions.click());

        UiDevice.getInstance(InstrumentationRegistry.getInstrumentation()).setOrientationLeft();

        Espresso.onView(ViewMatchers.withId(R.id.details_text))
                .check(ViewAssertions.matches(ViewMatchers.isDisplayed()));

        Espresso.onView(ViewMatchers.withId(android.R.id.list))
                .check(ViewAssertions.matches(ViewMatchers.isDisplayed()));

        Espresso.pressBackUnconditionally();

        Assert.assertTrue(mActivityRule.getActivity().isFinishing());
    }
}
