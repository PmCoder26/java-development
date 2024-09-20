package com.parimal.firstProject.SpringBootFirstProject.SpringBoot_MVC_And_RestAPIs.auth;

import org.springframework.data.domain.AuditorAware;

import java.util.Optional;

public class AuditorAwareImplementation implements AuditorAware<String> {           // for managing the user who creates or updates the data.

    @Override
    public Optional<String> getCurrentAuditor() {
        // get security context.
        // get authentication.
        // get the principle.
        // get the username.
        // we will look the above in the week of spring security.
        return Optional.of("Parimal Matte");        // by default returning the name.
    }
}
