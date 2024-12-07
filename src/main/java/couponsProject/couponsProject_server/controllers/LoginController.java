package couponsProject.couponsProject_server.controllers;

import couponsProject.couponsProject_server.beans.Company;
import couponsProject.couponsProject_server.beans.Customer;
import couponsProject.couponsProject_server.exseptions.AuthenticationException;
import couponsProject.couponsProject_server.security.JwtTokenUtil;
import couponsProject.couponsProject_server.services.*;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@RestController
public class LoginController {
    private LoginManager loginManager;
    @Autowired
    private JwtTokenUtil jwtTokenUtil;


    @PutMapping("/login?email=${email}&password=${password}&role=${role}")
    public String Login(@PathVariable String email, @PathVariable String password,@PathVariable String role) {

        String token =null;

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
        }
        //exist token
        if(token == null || !jwtTokenUtil.addToken(token))
            throw new AuthenticationException("user already logged in");
        return token;
    }

    @PutMapping("/logout?token=${token}")
    public void Logout(@PathVariable String token) {
        jwtTokenUtil.removeToken(token);
    }

}
