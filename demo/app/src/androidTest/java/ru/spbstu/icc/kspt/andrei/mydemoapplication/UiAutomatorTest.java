package ru.spbstu.icc.kspt.andrei.mydemoapplication;

import android.graphics.Point;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner;
import androidx.test.platform.app.InstrumentationRegistry;
import androidx.test.uiautomator.UiDevice;
import androidx.test.uiautomator.UiObject;
import androidx.test.uiautomator.UiSelector;

@RunWith(AndroidJUnit4ClassRunner.class)
public class UiAutomatorTest {
    @Test
    public void testIcons() throws Exception {
        UiDevice device = UiDevice.getInstance(InstrumentationRegistry.getInstrumentation());
        device.pressHome();

        device.waitForIdle();
        Point sz = device.getDisplaySizeDp();
        device.swipe(sz.x / 2, sz.y / 2 + sz.y / 4, sz.x / 2, sz.y / 2 - sz.y / 4, 5);

        device.waitForIdle();
        UiObject obj = device.findObject(new UiSelector().description("ListActivity"));
//        UiObject obj = device.findObject(new UiSelector().description("Calculator"));
//        UiScrollable launcherItem = new UiScrollable(new UiSelector().className("android.support.v7.widget.RecyclerView"));
//        launcherItem.scrollForward();
//        launcherItem.scrollDescriptionIntoView("YouTube");
//        UiObject obj = device.findObject(new UiSelector().description("YouTube"));
        obj.clickAndWaitForNewWindow();

        Assert.assertTrue(
                device.findObject(new UiSelector().packageName("ru.spbstu.icc.kspt.andrei.mydemoapplication")).exists()
        );
    }
}
