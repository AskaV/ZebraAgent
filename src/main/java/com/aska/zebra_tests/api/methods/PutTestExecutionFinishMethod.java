package com.aska.zebra_tests.api.methods;

import com.qaprosoft.carina.core.foundation.utils.R;

public class PutTestExecutionFinishMethod extends BaseApiMethod {
    public PutTestExecutionFinishMethod(String testRunId, String testId, String result) {
        super("api/test_execution/_put/rq.json", "api/test_execution/_put/rs.json");
        replaceUrlPlaceholder("base_url", R.CONFIG.get("api_url"));
        replaceUrlPlaceholder("testRunId", testRunId);
        replaceUrlPlaceholder("testId", testId);
        addProperty("result", result);
        addProperty("projectName", R.CONFIG.get("project_name"));
        addProperty("startedAt", R.CONFIG.get("test_run_start_time"));
        addProperty("finishedAt", R.CONFIG.get("test_run_end_time"));

    }
}
