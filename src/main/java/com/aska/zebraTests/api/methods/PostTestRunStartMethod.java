package com.aska.zebraTests.api.methods;

import com.qaprosoft.carina.core.foundation.utils.R;

public class PostTestRunStartMethod extends BaseApiMethod {
    public PostTestRunStartMethod() {
        super("api/test_run/_post/rq.json", "api/test_run/_post/rs.json");
        replaceUrlPlaceholder("base_url", R.CONFIG.get("api_url"));
        addUrlParameter("projectKey", R.CONFIG.get("project_key"));
        addProperty("projectName", R.CONFIG.get("project_name"));
        addProperty("startedAt", R.CONFIG.get("test_run_start_time"));
    }
}
