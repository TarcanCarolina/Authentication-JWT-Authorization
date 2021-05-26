package login.microservice.JWT.Spring.Security.controller;

import lombok.Data;

@Data
public class UpdateRoleRequest {

    /**
     * the username
     */
    private String login;

    /**
     * Role's name
     * e.g. admin, user
     */
    private String role;


}
