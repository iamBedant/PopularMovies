package com.example.bebi2.popularmovies.test;

import android.test.suitebuilder.TestSuiteBuilder;

import junit.framework.Test;

/**
 * Created by bebi2 on 12/22/2015.
 */
public class FullTestSuite {

    public static Test suite(){
        return new TestSuiteBuilder(FullTestSuite.class)
                .includeAllPackagesUnderHere().build();

    }

    public FullTestSuite(){
        super();
    }
}
