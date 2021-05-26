package login.microservice.JWT.Spring.Security.controller;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AuthResponse {
    /**
     * After a valid authentication a token is generated.
     */
    private String token;

}