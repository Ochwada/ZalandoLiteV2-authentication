package com.owr.authentication_service.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.server.ResponseStatusException;
/*=================================================================================
 * Project: authentication-service
 * File: AuthController
 * Created by: Ochwada
 * Created on: 06, 8/6/2025, 4:09 PM
 * Description: Controller for handling authentication-related routes such as login, dashboard, and home.
 * - Utilizes Spring Security with OIDC (OpenID Connect) for user authentication.
 =================================================================================*/

@Slf4j
@Controller
public class AuthController {

    /**
     * Handles the login page request.
     *
     * @return the name of the login view template.
     */
    @GetMapping("/my_login")
    public String loginPage() {
        return "login";
    }

    /**
     * Handles the dashboard page request after successful authentication.
     * Extracts user details from the OIDC user and adds them to the model.
     *
     * @param model the model to pass data to the view.
     * @param user  the authenticated OIDC user containing user attributes.
     * @return the name of the dashboard view template.
     */
    @GetMapping("/dashboard")
    public String dashboard(Model model, @AuthenticationPrincipal OidcUser user) {

        // Get the token value
        String idToken = user.getIdToken().getTokenValue();
        // log the token in terminal
        log.info("ID Token: {}", idToken);

        // Use the token for backend logic here (e.g. API call)
        model.addAttribute("name", user.getName());
        model.addAttribute("email", user.getEmail());
        model.addAttribute("picture", user.getPicture());

        return "dashboard";
    }

    /**
     * Handles the home page request.
     * This route is public and does not require authentication.
     *
     * @return the name of the home view template ("home.html").
     */
    @GetMapping("/")
    public String home() {
        return "home";
    }

    /**
     * Exposes the authenticated user's raw ID token (JWT) via a simple HTTP GET endpoint.
     * *
     * This internal endpoint is intended for use by trusted services that need access to the currently authenticated
     * user's ID token.
     *
     * @param user   the authenticated {@link OidcUser} injected by Spring Security
     * @param userId (optional) the expected user ID (subject); can be used for validation
     * @return the raw ID token string issued by the identity provider (e.g. Google)
     * @throws ResponseStatusException if userId is provided and does not match the authenticated user's subject
     */
    @GetMapping("/internal/token")
    @ResponseBody
    public String getToken(@AuthenticationPrincipal OidcUser user,
                           @RequestParam(name = "userId", required = false) String userId) {

        // log the userId and also validate it
        log.info("Requested token for userId: {}", userId);

        if (userId != null && !user.getSubject().equals(userId)) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Unauthorized access");
        }

        return user.getIdToken().getTokenValue();
    }

}
