package couponsProject.couponsProject_server.controllers;

import couponsProject.couponsProject_server.beans.Company;
import couponsProject.couponsProject_server.beans.Coupon;
import couponsProject.couponsProject_server.beans.Customer;
import couponsProject.couponsProject_server.services.AdminServices;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@RestController
@CrossOrigin
@RequestMapping("ad")
public class AdminController {
    private AdminServices adminServices;

    // int login(String email, String password);



    @GetMapping("/companies")
    public List<Company> getCompanies() {
        return adminServices.getAllCompanies();
    }

    @GetMapping("/customers")
    public List<Customer> getCustomers() {
        return adminServices.getAllCustomers();
    }

    @GetMapping("/company/{id}")
    public Company getCompany(@PathVariable int id) {
        return adminServices.getOneCompany(id);
    }

    @GetMapping("/customer/{id}")
    public Customer getCustomer(@PathVariable int id) {
        return adminServices.getOneCustomer(id);
    }

    @GetMapping("/company/delete/{id}")
    public void delete(@PathVariable int id) {
        adminServices.deleteCompany(id);
    }


    @GetMapping("/customer/delete/{id}")
    public void deleteCustomer(@PathVariable int id) {
        adminServices.deleteCustomer(id);
    }


    @PutMapping("/company/update")
    public void updateCompany(@RequestBody Company company) {
        adminServices.updateCompany(company);
    }

    @PutMapping("/customer/update")
    public void updateCustomer(@RequestBody Customer customer) {
        adminServices.updateCustomer(customer);
    }

    @PostMapping("/company/add")
    public void addCompany(@RequestBody Company company) {
        adminServices.addCompany(company);
    }

    @PostMapping("/customer/add")
    public void addCustomer(@RequestBody Customer customer) {
        adminServices.addCustomer(customer);
    }

}




