package login.microservice.JWT.Spring.Security.controller;

import lombok.Data;

@Data
public class AuthRequest {
    /**
     * the username
     */
    private String login;

    /**
     * the user's password
     * the password will be persisted encoded
     */
    private String password;

    /*public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }*/
}