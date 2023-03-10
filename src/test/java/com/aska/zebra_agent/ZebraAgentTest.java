package com.aska.zebra_agent;

import api.ReportingService;
import com.aska.zebra_tests.AuthService;
import com.aska.zebra_tests.TestService;
import com.aska.zebra_tests.api.enumtype.TestStatuses;
import com.qaprosoft.carina.core.foundation.utils.ownership.MethodOwner;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.lang.invoke.MethodHandles;


public class ZebraAgentTest {
    private static final Logger LOGGER = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

    @Test()
    public void zebraAgentTest() {
        LOGGER.info("Hello World!");
            }

    @Test()
    @MethodOwner(owner = "avoz")
    public void testRunStartTest() {
        LOGGER.info("Test Run start");
        AuthService.refreshAuthToken();
        TestService.testRunStart();
        Assert.assertTrue(ReportingService.getInstance().getTestRunResult().equalsIgnoreCase(TestStatuses.IN_PROGRESS.getStatusName()), "Actual test status differs from the expected one");
    }

    @Test()
    @MethodOwner(owner = "avoz")
    public void testExecutionStartTest() {
        LOGGER.info("Test Execution start");
        AuthService.refreshAuthToken();
        TestService.testRunStart();
        TestService.testExecutionStart();
        Assert.assertTrue(ReportingService.getInstance().getTestExecutionResult().equalsIgnoreCase(TestStatuses.IN_PROGRESS.getStatusName()), "Actual test result differs from the expected one");
    }

    @Test()
    @MethodOwner(owner = "avoz")
    public void testExecutionFinishTest() {
        LOGGER.info("Test Execution finish");
        AuthService.refreshAuthToken();
        TestService.testRunStart();
        TestService.testExecutionStart();
        TestService.testExecutionFinish(TestStatuses.PASSED);
        Assert.assertTrue(ReportingService.getInstance().getTestExecutionResult().equalsIgnoreCase(TestStatuses.PASSED.getStatusName()), "Actual test result differs from the expected one");
    }

    @Test()
    @MethodOwner(owner = "avoz")
    public void successTest() {
        LOGGER.info("Success test started");
        TestService.startTest(TestStatuses.PASSED);
        String testStatus = ReportingService.getInstance().getStatus();
        LOGGER.info("Success test finished");
        LOGGER.info(testStatus);
        Assert.assertTrue(testStatus.equalsIgnoreCase(TestStatuses.PASSED.getStatusName()), "Actual test status differs from the expected one");
    }

    @Test()
    @MethodOwner(owner = "avoz")
    public void failTest() {
        LOGGER.info("Failed test started");
        TestService.startTest(TestStatuses.FAILED);
        String testStatus = ReportingService.getInstance().getStatus();
        LOGGER.info("Failed test finished");
        LOGGER.info(testStatus);
        Assert.assertTrue(testStatus.equalsIgnoreCase(TestStatuses.FAILED.getStatusName()), "Actual test status differs from the expected one");
    }

    @Test()
    @MethodOwner(owner = "avoz")
    public void skippedTest() {
        LOGGER.info("Skipped test started");
        TestService.startTest(TestStatuses.SKIPPED);
        String testStatus = ReportingService.getInstance().getStatus();
        LOGGER.info("Skipped test finished");
        LOGGER.info(testStatus);
        Assert.assertTrue(testStatus.equalsIgnoreCase(TestStatuses.FAILED.getStatusName()), "Actual test status differs from the expected one");
    }

}