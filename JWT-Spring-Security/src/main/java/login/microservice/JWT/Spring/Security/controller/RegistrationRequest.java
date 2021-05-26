package login.microservice.JWT.Spring.Security.controller;

import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
public class RegistrationRequest {

    /**
     * the username
     */
    @NotEmpty
    private String login;

    /**
     * user's password
     */
    @NotEmpty
    private String password;


   // @NotEmpty
   // private String email;

   /* public String getEmail() {
        return email;
    }
    //  @NotEmpty
   // private int role_id;

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    //public String getRoleId() {
    //    return role_id;
   // }*/
}