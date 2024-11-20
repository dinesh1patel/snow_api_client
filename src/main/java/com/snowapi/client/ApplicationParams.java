package com.snowapi.client;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ApplicationParams {

    public String getSnowUsername() {
        return snowUsername;
    }

    public String getSnowPassword() {
        return snowPassword;
    }

    @Value("${snow.api.username}")
    private String snowUsername;

    @Value("${snow.api.password}")
    private String snowPassword;

}