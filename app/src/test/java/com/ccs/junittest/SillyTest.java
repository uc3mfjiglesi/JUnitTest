package com.ccs.junittest;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;



/**
 * Created by javier1 on 14/2/17.
 */

@RunWith(JUnit4.class)
public class SillyTest {
    @BeforeClass
    static public void doThisFirstOnlyOnce() {
        // do initialization here, run once for all SillyTest tests
    }

    @Before
    public void doThisFirst() {
        // do initialization here, run on every test method
    }

    @After
    public void doThisLast() {
        // do termination here, run on every test method
    }

    @AfterClass
    static public void doThisLastOnlyOnce() {
        // do termination here, run once for all SillyTest tests
    }

    @Test
    public void thisIsReallySilly() {
        Assert.assertEquals("bit got flipped by cosmic rays", 1, 1);
    }
}
