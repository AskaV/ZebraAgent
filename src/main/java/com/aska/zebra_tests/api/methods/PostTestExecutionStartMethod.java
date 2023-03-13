package com.aska.zebra_tests.api.methods;

import com.qaprosoft.carina.core.foundation.utils.R;

public class PostTestExecutionStartMethod extends BaseApiMethod {
    public PostTestExecutionStartMethod(String testRunId) {
        super("api/test_execution/_post/rq.json", "api/test_execution/_post/rs.json");
        replaceUrlPlaceholder("base_url", R.CONFIG.get("api_url"));
        replaceUrlPlaceholder("testRunId", testRunId);
        addProperty("projectKey", R.CONFIG.get("project_key"));
        addProperty("projectName", R.CONFIG.get("project_name"));
        addProperty("startedAt", R.CONFIG.get("test_run_start_time"));
    }
}
