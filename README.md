# 🔐 ZalandoLite V2 - Authentication

---
###   🔗 [ZalandoLite V2  🍀 Overview Repository ](https://github.com/Ochwada/ZalandoLiteV2-MicroservicesArchitecture)
 Microservices ⬇️ part of **ZalandoLite V2**
#### 🖇️ [Microservice 1: Authentication Service](https://github.com/Ochwada/ZalandoLiteV2-authentication)


---
##  About Authentication Service

The  **Authentication Service**, is a lightweight, modular microservice in the **ZalandoLite** ecosystem, purpose-built 
for handling user authentication via **OAuth2** with **Google** as the social login provider.

Developed with **Java 17+**, **Spring Boot**, and **Spring Security**, this service ensures a secure and extensible 
authentication flow, seamlessly integrable into modern microservice architectures.


##  Features

- OAuth2-based login using **Google**
- Secure, token-based authentication (**JWT-ready**)
- Modular and easily customizable for additional providers
- Plug-and-play integration with microservice ecosystems
- Built using **Spring Boot**, **Spring Security**, and **Java 17+**

## Project Structure
```yaml
authentication-service/
│
├── src/
│   ├── main/
│   │   ├── java/com/owr/authentication_service/
│   │   │   ├── config/             # Spring Security config
│   │   │   ├── controller/         # Auth controller endpoints
│   │   │   └── AuthenticationServiceApplication.java
│   │   └── resources/
│   │       ├── template/           # Thymeleaf templates
│   │       └── application.yml     # Configuration
│
├── .env                            # (optional) for secrets
├── README.md
├── pom.xml

```

## Tech Stack 
| Technology          | Purpose                                                                      |
|---------------------|------------------------------------------------------------------------------|
| **Java 17+**        | Modern programming language used to build the microservice                   |
| **Spring Boot**     | Framework for rapid application development and configuration                |
| **Spring Security** | Handles authentication, authorization, and protection against common attacks |
| **OAuth2 (OIDC)**   | Secure login flow using Google as a trusted identity provider                |
| **Thymeleaf**       | Server-side HTML template engine used for rendering login/dashboard UI       |
| **Maven**           | Dependency and build management tool for Java projects                       |

### Dependencies
| Dependency Artifact                 | Purpose                                                                 |
|-------------------------------------|-------------------------------------------------------------------------|
| `spring-boot-starter-oauth2-client` | Enables OAuth2 login (e.g. Google), and integrates with OpenID Connect  |
| `spring-boot-starter-security`      | Adds Spring Security for securing endpoints, sessions, and roles        |
| `spring-boot-starter-thymeleaf`     | Provides Thymeleaf support for rendering HTML views on the server side  |
| `spring-boot-starter-validation`    | Enables Java Bean Validation (e.g., `@Valid`, `@Email`, etc.)           |
| `spring-boot-starter-web`           | Core starter for RESTful web applications using Spring MVC              |
| `thymeleaf-extras-springsecurity6`  | Integrates Spring Security with Thymeleaf (e.g., `sec:authorize`)       |
| `lombok`                            | Reduces boilerplate by auto-generating code like getters, setters, etc. |
| `spring-boot-starter-test`          | Includes tools like JUnit, Mockito, AssertJ for unit/integration tests  |
| `spring-security-test`              | Adds support for testing Spring Security (e.g., mocking users/roles)    |
| `dotenv-java`                       | Loads environment variables from `.env` file at runtime                 |


### Environment Configurations
The variables are defined  in a file located at:
```.dotenv
authentication-service/.env
```
> These credentials are used to authenticate users via Google OAuth2/OpenID Connect.

```.dotenv
#-------------------------------------------
#  Configuration
#-------------------------------------------
GOOGLE_CLIENT_ID=google_client_id
GOOGLE_CLIENT_SECRET=google_secret
```

Variable Reference

| Variable               | Description                                      | Where to Get It                                    |
|------------------------|--------------------------------------------------|----------------------------------------------------|
| `GOOGLE_CLIENT_ID`     | OAuth2 client ID for Google login                | From Google Cloud Console under OAuth2 credentials |
| `GOOGLE_CLIENT_SECRET` | Secret used to authenticate your app with Google | Same place as above (keep this secure!)            |


## API Endpoints
| Method | Endpoint                       | Description                                                       |
|--------|--------------------------------|-------------------------------------------------------------------|
| `GET`  | `/` or `/home`                 | Public home page (e.g., `home.html`)                              |
| `GET`  | `/dashboard`                   | Protected user dashboard (e.g., `dashboard.html`); requires login |
| `GET`  | `/login`                       | Redirects to custom login page (`my_login.html`)                  |
| `GET`  | `/oauth2/authorization/google` | Starts the OAuth2 login flow using Google                         |



## Results Overview

---

## Credits
Made with ❤️ by [Linda Ochwada](https://www.linkedin.com/in/ochwada-l-66630a36/)

**ZalandoLite V2** is an educational microservice project focused on modern Java + Spring practices.