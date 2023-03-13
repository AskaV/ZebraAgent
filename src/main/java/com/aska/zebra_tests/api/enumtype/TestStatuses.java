package com.aska.zebra_tests.api.enumtype;

public enum TestStatuses {

    IN_PROGRESS("IN_PROGRESS"),
    PASSED("PASSED"),
    FAILED("FAILED"),
    SKIPPED("SKIPPED"),
    ABORTED("ABORTED");

    private final String statusName;

    private TestStatuses(String statusName) {
        this.statusName = statusName;
    }

    public String getStatusName() {
        return this.statusName;
    }
}
