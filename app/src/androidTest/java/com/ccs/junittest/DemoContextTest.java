package com.ccs.junittest;

import android.support.test.InstrumentationRegistry;
import android.view.LayoutInflater;
import android.view.View;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class DemoContextTest {
    private View field=null;
    private View root=null;

    @Before
    public void init() {
        InstrumentationRegistry.getInstrumentation().runOnMainSync(new Runnable() {
            @Override
            public void run() {
                LayoutInflater inflater=LayoutInflater
                        .from(InstrumentationRegistry.getTargetContext());

                root=inflater.inflate(R.layout.add, null);
            }
        });

        root.measure(800, 480);
        root.layout(0, 0, 800, 480);

        field=root.findViewById(R.id.title);
    }

    @Test
    public void exists() {
        Assert.assertNotNull(field);
    }

    @Test
    public void position() {
        Assert.assertEquals(0, field.getTop());
        Assert.assertEquals(0, field.getLeft());
    }
}
