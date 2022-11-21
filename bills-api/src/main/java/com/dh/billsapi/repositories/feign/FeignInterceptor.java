package com.dh.billsapi.repositories.feign;

import feign.RequestInterceptor;
import feign.RequestTemplate;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientManager;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.security.web.server.authorization.AuthorizationContext;
import org.springframework.stereotype.Component;

import javax.ws.rs.core.SecurityContext;
import java.util.Objects;

@Component
public class FeignInterceptor implements RequestInterceptor {

    @Override
    public void apply(RequestTemplate requestTemplate) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (Objects.nonNull(authentication)) {
            try {
                String token = ((JwtAuthenticationToken) authentication).getToken().getTokenValue();
                requestTemplate.header("Authorization", "Bearer " + token);
            } catch (Exception ignored) {
                System.out.println("Unexpected error obtaining token");
            }
        }
    }
}
