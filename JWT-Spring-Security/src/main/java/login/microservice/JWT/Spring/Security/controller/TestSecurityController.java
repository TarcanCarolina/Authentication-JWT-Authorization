package login.microservice.JWT.Spring.Security.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestSecurityController {

    @GetMapping("/admin/get")
    public String getAdmin() {

        return "the admin!";
    }

    @GetMapping("/user/get")
    public String getStudent() {

        return "an user!";
    }

    @GetMapping("/secretary/get")
    public String getSecretary() {
        return "the secretary!";
    }

}