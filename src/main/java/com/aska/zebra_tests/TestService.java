package com.aska.zebra_tests;
import api.ReportingService;
import api.ResponseService;
import com.aska.zebra_tests.api.enumtype.*;
import com.aska.zebra_tests.api.methods.*;

public class TestService {

    public static void startTest(TestStatuses testStatus) {
        AuthService.refreshAuthToken();
        testRunStart();
        testExecutionStart();
        sendTestExecutionLogs();
        attachTestExecutionLabels();
        testExecutionFinish(testStatus);
        testRunExecutionFinish();
    }

    public static void testRunStart() {
        ExecutionService executor = new ExecutionService();
        PostTestRunStartMethod testRunStartMethod = new PostTestRunStartMethod();
        executor.expectStatus(testRunStartMethod, HTTPStatusCodeType.OK);
        String rs = executor.callApiMethod(testRunStartMethod);
        ResponseService responseService = new ResponseService();
        responseService.setId(rs);
        ReportingService.getInstance().setTestRunId(responseService.getId());
        responseService.setTestStatus(rs);
        ReportingService.getInstance().setTestRunResult(responseService.getTestStatus());
        testRunStartMethod.validateResponse();
    }

    public static void testExecutionStart() {
        ExecutionService executor = new ExecutionService();
        PostTestExecutionStartMethod testExecutionStartMethod =
                new PostTestExecutionStartMethod(ReportingService.getInstance().getTestRunId());
        executor.expectStatus(testExecutionStartMethod, HTTPStatusCodeType.OK);
        String rs = executor.callApiMethod(testExecutionStartMethod);
        ResponseService responseService = new ResponseService();
        responseService.setId(rs);
        ReportingService.getInstance().setTestId(responseService.getId());
        responseService.setResult(rs);
        ReportingService.getInstance().setTestExecutionResult(responseService.getResult());
        testExecutionStartMethod.validateResponse();
    }

    public static void testExecutionFinish(TestStatuses testStatus) {
        ExecutionService executor = new ExecutionService();
        PutTestExecutionFinishMethod testExecutionFinishMethod = new PutTestExecutionFinishMethod(ReportingService
                .getInstance().getTestRunId(), ReportingService.getInstance().getTestId(), testStatus.getStatusName());
        executor.expectStatus(testExecutionFinishMethod, HTTPStatusCodeType.OK);
        ResponseService responseService = new ResponseService();
        responseService.setResult(executor.callApiMethod(testExecutionFinishMethod));
        ReportingService.getInstance().setTestExecutionResult(responseService.getResult());
        testExecutionFinishMethod.validateResponse();
    }

    public static void testRunExecutionFinish() {
        ExecutionService executor = new ExecutionService();
        PutTestRunExecutionFinishMethod testRunExecutionFinishMethod =
                new PutTestRunExecutionFinishMethod(ReportingService.getInstance().getTestRunId());
        executor.expectStatus(testRunExecutionFinishMethod, HTTPStatusCodeType.OK);
        ResponseService responseService = new ResponseService();
        responseService.setTestStatus(executor.callApiMethod(testRunExecutionFinishMethod));
        ReportingService.getInstance().setStatus(responseService.getTestStatus());
        testRunExecutionFinishMethod.validateResponse();
    }

    public static void sendTestExecutionLogs() {
        ExecutionService executor = new ExecutionService();
        PostTestExecutionLogsMethod testExecutionLogsMethod =
                new PostTestExecutionLogsMethod(ReportingService.getInstance().getTestRunId(), ReportingService
                        .getInstance().getTestId());
        executor.expectStatus(testExecutionLogsMethod, HTTPStatusCodeType.ACCEPTED);
        executor.callApiMethod(testExecutionLogsMethod);
    }

    public static void attachTestExecutionLabels() {
        ExecutionService executor = new ExecutionService();
        PutTestExecutionLabelsMethod testExecutionLabelsMethod = new PutTestExecutionLabelsMethod(ReportingService
                .getInstance().getTestRunId(), ReportingService.getInstance().getTestId());
        executor.expectStatus(testExecutionLabelsMethod, HTTPStatusCodeType.OK_NO_CONTENT);
        executor.callApiMethod(testExecutionLabelsMethod);
    }
}
