package pl.altkom.shop.lib;

import org.keycloak.KeycloakPrincipal;
import org.keycloak.KeycloakSecurityContext;
import org.keycloak.adapters.springsecurity.token.KeycloakAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
public class UserContext {
	public String getLogin() {
		return SecurityContextHolder.getContext().getAuthentication().getName();
	}

	public String getToken() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		KeycloakAuthenticationToken a = (KeycloakAuthenticationToken) authentication;
		KeycloakPrincipal<KeycloakSecurityContext> principal = (KeycloakPrincipal<KeycloakSecurityContext>) a
				.getPrincipal();
		return principal.getKeycloakSecurityContext().getTokenString();
	}
}
