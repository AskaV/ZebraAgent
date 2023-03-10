package com.aska.zebra_tests.api.methods;

import com.aska.zebra_tests.AuthService;
import com.qaprosoft.carina.core.foundation.api.AbstractApiMethodV2;

import java.util.Properties;

public abstract class BaseApiMethod extends AbstractApiMethodV2 {

    public BaseApiMethod(String rqPath, String rsPath, String propertiesPath) {
        super(rqPath, rsPath, propertiesPath);
    }

    public BaseApiMethod(String rqPath, String rsPath, Properties properties) {
        super(rqPath, rsPath, properties);
        setAuth();
    }

    public BaseApiMethod(String rqPath, String rsPath) {
        super(rqPath, rsPath);
        setAuth();
    }

    private void setAuth() {
        setHeaders("Authorization=Bearer " + AuthService.getAuthToken());
    }
}
