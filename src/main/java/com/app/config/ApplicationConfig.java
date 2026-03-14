package com.app.config;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.faces.annotation.FacesConfig;
import jakarta.security.enterprise.authentication.mechanism.http.CustomFormAuthenticationMechanismDefinition;
import jakarta.security.enterprise.authentication.mechanism.http.LoginToContinue;
import jakarta.security.enterprise.identitystore.InMemoryIdentityStoreDefinition;
import jakarta.security.enterprise.identitystore.InMemoryIdentityStoreDefinition.Credentials;
import jakarta.security.enterprise.identitystore.IdentityStore;
import jakarta.security.enterprise.identitystore.CredentialValidationResult;
import jakarta.security.enterprise.credential.Credential;
import jakarta.security.enterprise.credential.UsernamePasswordCredential;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import jakarta.annotation.security.DeclareRoles;

@FacesConfig
@ApplicationScoped
@CustomFormAuthenticationMechanismDefinition(
        loginToContinue = @LoginToContinue(
                loginPage = "/login.xhtml",
                errorPage = "/login.xhtml?error=true",
                useForwardToLogin = false
        )
)
@InMemoryIdentityStoreDefinition({
    @InMemoryIdentityStoreDefinition.Credentials(
        callerName = "admin", 
        password = "admin123", 
        groups = {"ADMIN", "USER"}
    ),
    @InMemoryIdentityStoreDefinition.Credentials(
        callerName = "pepe", 
        password = "pepe123",
        groups = {"USER"}
    )
})
public class ApplicationConfig {

}