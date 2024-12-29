package couponsProject.couponsProject_server.controllers;

import couponsProject.couponsProject_server.beans.Company;
import couponsProject.couponsProject_server.beans.Customer;
import couponsProject.couponsProject_server.exseptions.AuthenticationException;
import couponsProject.couponsProject_server.security.JwtTokenUtil;
import couponsProject.couponsProject_server.services.*;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@AllArgsConstructor
@Slf4j
@RestController
public class LoginController {
    private LoginManager loginManager;
    @Autowired
    private JwtTokenUtil jwtTokenUtil;


    @PostMapping("/login")
    public ResponseEntity<String> Login(@RequestBody Map<String, String> loginDetails) {
        String email = loginDetails.get("email");
        String password = loginDetails.get("password");
        String role = loginDetails.get("role");
        String token =null;
        log.info("Attempting to log in using email:{} and password:{} and role:{}",email, password,role);



        switch (role){
            case "ADMINISTRATOR": {
                AdminServices services = (AdminServices) loginManager.login(email, password, ClientTypeEnum.ADMINISTRATOR);
                token = jwtTokenUtil.createToken(0,"Admin",email,role);
            }
            break;
            case "COMPANY": {
                CompanyServices services = (CompanyServices) loginManager.login(email, password, ClientTypeEnum.COMPANY);
                Company company = services.getCompanyDetails(services.login(email,password));
                token = jwtTokenUtil.createToken(company.getId(),company.getName(),email,role);
            }
            break;
            case "CUSTOMER": {
                CustomerServices services = (CustomerServices) loginManager.login(email, password, ClientTypeEnum.CUSTOMER);
                Customer customer = services.getCustomer(services.login(email,password));
                token = jwtTokenUtil.createToken(customer.getId(), customer.getFirstName() + " " + customer.getLastName(),email,role);
            }
            break;
            default:
                token = null;
        }
        //exist token
        if(token == null)
            throw new AuthenticationException("Invalid role specified");
        else if (!jwtTokenUtil.addToken(token)) {
            throw new AuthenticationException("user already logged in");
        }
        return ResponseEntity.ok(token);    }

    @DeleteMapping("/logout")
    public ResponseEntity<String> Logout(@RequestParam String token) {
        log.info("Attempting to Logout using token:{}",token);
        jwtTokenUtil.removeToken(token);
        return ResponseEntity.ok("Logout succseded");
    }

}
