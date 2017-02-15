package com.ccs.junittest;

import android.support.test.espresso.NoMatchingViewException;
import android.support.test.espresso.ViewAssertion;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.withClassName;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsAnything.anything;


/**
 * Created by javier1 on 14/2/17.
 */

@RunWith(AndroidJUnit4.class)
public class RecyclerViewTest {
    @Rule
    public final ActivityTestRule<com.ccs.junittest.rv.MainActivity> main
            =new ActivityTestRule(com.ccs.junittest.rv.MainActivity.class, true);

    @Test
    public void listCount() {
        onView(Matchers.<View>instanceOf(RecyclerView.class))
                .check(new AdapterCountAssertion(25));
    }

    @Test
    public void scrollToBottom() {
        onView(withClassName(is(RecyclerView.class.getCanonicalName())))
                .check(matches(anything()));
    }

    static class AdapterCountAssertion implements ViewAssertion {
        private final int count;

        AdapterCountAssertion(int count) {
            this.count=count;
        }

        @Override
        public void check(View view,
                          NoMatchingViewException noViewFoundException) {
            Assert.assertTrue(view instanceof RecyclerView);
            Assert.assertEquals(count,
                    ((RecyclerView)view).getAdapter().getItemCount());
        }
    }
}
