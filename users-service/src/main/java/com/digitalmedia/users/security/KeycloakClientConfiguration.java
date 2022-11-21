package com.digitalmedia.users.security;

import org.keycloak.OAuth2Constants;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.admin.client.KeycloakBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;


@Component
@ConfigurationProperties(prefix = "keycloak")
public class KeycloakClientConfiguration {

    private String realm;
    private String clientId;
    private String clientSecret;
    private String serverUrl;
    private String scope;


    @Bean
    public Keycloak getInstance(){
        return KeycloakBuilder.builder()
                .serverUrl(this.serverUrl)
                .realm(this.realm)
                .clientId(this.clientId)
                .clientSecret(this.clientSecret)
                .scope(this.scope)
                .grantType(OAuth2Constants.CLIENT_CREDENTIALS)
                .build();
    }

    public String getRealm() {
        return realm;
    }

    public void setRealm(String realm) {
        this.realm = realm;
    }

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public String getClientSecret() {
        return clientSecret;
    }

    public void setClientSecret(String clientSecret) {
        this.clientSecret = clientSecret;
    }

    public String getServerUrl() {
        return serverUrl;
    }

    public void setServerUrl(String serverUrl) {
        this.serverUrl = serverUrl;
    }

    public String getScope() {
        return scope;
    }

    public void setScope(String scope) {
        this.scope = scope;
    }
}
