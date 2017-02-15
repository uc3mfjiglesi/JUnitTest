package com.ccs.junittest;

import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;
import android.test.ActivityInstrumentationTestCase2;
import android.test.TouchUtils;
import android.widget.ListView;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.InstrumentationRegistry.getInstrumentation;

/**
 * Created by javier1 on 14/2/17.
 */

@RunWith(AndroidJUnit4.class)
public class DemoActivityTest extends
        ActivityInstrumentationTestCase2<MainActivity> {
    private ListView list=null;

    public DemoActivityTest() {
        super(MainActivity.class);
    }

    @Before
    @Override
    public void setUp() throws Exception {
        super.setUp();

        injectInstrumentation(InstrumentationRegistry.getInstrumentation());
        setActivityInitialTouchMode(false);

        MainActivity activity=getActivity();

        list=(ListView)activity.findViewById(android.R.id.list);
    }

    @After
    public void tearDown() throws Exception {
        super.tearDown();
    }

    @Test
    public void listCount() {
        Assert.assertEquals(25, list.getAdapter().getCount());
    }

    @Test
    public void keyEvents() {
        sendKeys("4*DPAD_DOWN");
        Assert.assertEquals(4, list.getSelectedItemPosition());
    }

    @Test
    public void touchEvents() {
        TouchUtils.scrollToBottom(this, getActivity(), list);
        getInstrumentation().waitForIdleSync();
        Assert.assertEquals(24, list.getLastVisiblePosition());
    }
}
