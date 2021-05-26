package login.microservice.JWT.Spring.Security.controller;

import login.microservice.JWT.Spring.Security.config.jwt.JwtProvider;
import login.microservice.JWT.Spring.Security.entity.UserEntity;
import login.microservice.JWT.Spring.Security.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AdminController {

    @Autowired
    private UserService userService;

    @Autowired
    private JwtProvider jwtProvider;

    /**
     * Used to update a role for a user identified by his username
     * call service layer
     * and
     * find login from the request
     * find the id for the role from request
     * modify role_id  from user_table with id that was found
     * update the user with modified values
     *
     * @param request the request contains username & the new role's name
     * @return a message if update was successful or a REST error code otherwise
     */
    @PutMapping("/admin/role/update")
    public String updateRole(@RequestBody UpdateRoleRequest request) {

        UserEntity userEntity = userService.updateRoleUser(request.getLogin(), request.getRole());

        return "Role was updated";
    }
}
