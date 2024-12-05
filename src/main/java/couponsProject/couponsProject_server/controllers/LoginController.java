package couponsProject.couponsProject_server.controllers;

import couponsProject.couponsProject_server.services.ClientServices;
import couponsProject.couponsProject_server.services.ClientTypeEnum;
import couponsProject.couponsProject_server.services.LoginManager;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@AllArgsConstructor
@RestController
@CrossOrigin
public class LoginController {
    private ClientServices clientServices;
    private LoginManager loginManager;

    @PostMapping("/login?username=${email}&password=${password}&role=${role}")
    public String LoginController(@PathVariable String email, @PathVariable String password,@PathVariable String role) {
        clientServices = loginManager.login(email,password,ClientTypeEnum.valueOf(role));

    }
}
