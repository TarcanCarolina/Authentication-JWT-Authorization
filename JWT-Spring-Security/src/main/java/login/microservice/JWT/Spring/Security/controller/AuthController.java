package login.microservice.JWT.Spring.Security.controller;

import login.microservice.JWT.Spring.Security.config.jwt.JwtProvider;
import login.microservice.JWT.Spring.Security.entity.UserEntity;
import login.microservice.JWT.Spring.Security.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
public class AuthController {
    @Autowired
    private UserService userService;
    @Autowired
    private JwtProvider jwtProvider;

    /**
     * Used to register an user in the application database using the service layer (UserService)
     *
     * @param registrationRequest contains the username, email and password of the new user of the registration request
     * @return a message if register was successful or a REST error code
     */
    @PostMapping("/register")
    public String registerUser(@RequestBody @Valid RegistrationRequest registrationRequest) {

        UserEntity u = new UserEntity();
        u.setPassword(registrationRequest.getPassword());
        u.setLogin(registrationRequest.getLogin());
        //u.setEmail(registrationRequest.getEmail());

        userService.saveUser(u);

        return "OK";
    }

    /**
     * Used to authenticate an user.
     * The user is searched in DB (using service layer) based on his credentials (username & password).
     * At a valid authentication, JWT  token is generated. A filter will be used depends on the user's role.
     * Each role has various permissions.
     *
     * @param request contains the username and password of the new user of the registration request
     * @return a message if authentication was successful or a REST error code otherwise
     */
    @PostMapping("/authenticate")
    public AuthResponse auth(@RequestBody AuthRequest request) {

        UserEntity userEntity = userService.findByLoginAndPassword(request.getLogin(), request.getPassword());

        String token = jwtProvider.generateToken(userEntity.getLogin());

        return new AuthResponse(token);
    }
}
