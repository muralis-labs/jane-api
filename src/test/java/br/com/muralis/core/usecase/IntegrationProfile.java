package br.com.muralis.core.usecase;

import io.quarkus.test.junit.QuarkusTestProfile;

import java.util.Map;

public class IntegrationProfile implements QuarkusTestProfile {

    @Override
    public Map<String, String> getConfigOverrides() {
        return Map.of("quarkus.devservices.enabled", "true");
    }

}
