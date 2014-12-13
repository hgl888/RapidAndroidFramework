package com.soi.rapidandroidapp.managers;

import android.test.InstrumentationTestCase;
import android.util.Log;

import com.soi.rapidandroidapp.BuildConfig;
import com.soi.rapidandroidapp.models.common.Environment;
import com.soi.rapidandroidapp.utilities.Constants;

import retrofit.RestAdapter;

/**
 * Created by Spiros I. Oikonomakis on 11/4/14.
 */
public class EnviromentManagerTest extends InstrumentationTestCase {

    public void testEnviroment()
    {
        Environment environment = EnvironmentManager.getInstance().getEnvironment();
        assertNotNull(environment);
        assertEquals(environment.toString(), BuildConfig.ENVIRONMENT);
    }

    public void testApiEnvironment()
    {
        String apiUrl = EnvironmentManager.getInstance().getEnviromentApiUrl();
        assertNotNull(apiUrl);

        switch (EnvironmentManager.getInstance().getEnvironment()) {
            case LIVE:
                assertEquals(apiUrl, Constants.API_LIVE_URL);
                break;
            case STAGING:
                assertEquals(apiUrl, Constants.API_STAGING_URL);
                break;
            case UAT:
                assertEquals(apiUrl, Constants.API_STAGING_URL);
                break;
        }
    }

    public void testApiLogLevel()
    {
        RestAdapter.LogLevel logLevel = EnvironmentManager.getInstance().getEnvironmentApiLogLevel();
        assertNotNull(logLevel);

        switch (EnvironmentManager.getInstance().getEnvironment()) {
            case LIVE:
                assertEquals(logLevel, RestAdapter.LogLevel.BASIC);
                break;
            case STAGING:
                assertEquals(logLevel, RestAdapter.LogLevel.FULL);
                break;
            case UAT:
                assertEquals(logLevel, RestAdapter.LogLevel.FULL);
                break;
        }
    }

    public void testLogLevel()
    {
        int logLevel = EnvironmentManager.getInstance().getEnvironmentLogLevel();
        assertNotNull(logLevel);

        switch (EnvironmentManager.getInstance().getEnvironment()) {
            case LIVE:
                assertEquals(logLevel, Log.INFO);
                break;
            case STAGING:
                assertEquals(logLevel, Log.DEBUG);
                break;
            case UAT:
                assertEquals(logLevel, Log.VERBOSE);
                break;
        }
    }


    public void testCanTrackGA()
    {
        boolean logLevel = EnvironmentManager.getInstance().canTrackGA();
        assertNotNull(logLevel);

        switch (EnvironmentManager.getInstance().getEnvironment()) {
            case LIVE:
                assertEquals(logLevel, true);
                break;
            case STAGING:
                assertEquals(logLevel, false);
                break;
            case UAT:
                assertEquals(logLevel, true);
                break;
        }
    }
}