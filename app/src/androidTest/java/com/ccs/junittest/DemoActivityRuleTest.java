package com.ccs.junittest;

/**
 * Created by javier1 on 14/2/17.
 */

import android.support.test.espresso.NoMatchingViewException;
import android.support.test.espresso.ViewAssertion;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.view.KeyEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import junit.framework.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import static android.support.test.espresso.Espresso.onData;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.pressKey;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.anything;

@RunWith(AndroidJUnit4.class)
public class DemoActivityRuleTest {
    @Rule public final ActivityTestRule<MainActivity> main
            =new ActivityTestRule(MainActivity.class, true);

    @Test
    public void listCount() {
        onView(withId(android.R.id.list))
                .check(new AdapterCountAssertion(25));
    }

    @Test
    public void keyEvents() {
        onView(withId(android.R.id.list))
                .perform(pressKey(KeyEvent.KEYCODE_DPAD_DOWN),
                        pressKey(KeyEvent.KEYCODE_DPAD_DOWN),
                        pressKey(KeyEvent.KEYCODE_DPAD_DOWN),
                        pressKey(KeyEvent.KEYCODE_DPAD_DOWN))
                .check(new ListSelectionAssertion(3));
    }

    @Test
    public void scrollToBottom() {
        onData(anything())
                .inAdapterView(withId(android.R.id.list))
                .atPosition(24)
                .check(matches(withText("purus")));
    }

    static class AdapterCountAssertion implements ViewAssertion {
        private final int count;

        AdapterCountAssertion(int count) {
            this.count=count;
        }

        @Override
        public void check(View view,
                          NoMatchingViewException noViewFoundException) {
            Assert.assertTrue(view instanceof AdapterView);
            Assert.assertEquals(count,
                    ((AdapterView)view).getAdapter().getCount());
        }
    }

    static class ListSelectionAssertion implements ViewAssertion {
        private final int position;

        ListSelectionAssertion(int position) {
            this.position=position;
        }

        @Override
        public void check(View view,
                          NoMatchingViewException noViewFoundException) {
            Assert.assertTrue(view instanceof ListView);
            Assert.assertEquals(position,
                    ((ListView)view).getSelectedItemPosition());
        }
    }
}