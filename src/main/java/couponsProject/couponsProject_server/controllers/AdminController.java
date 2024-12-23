package couponsProject.couponsProject_server.controllers;

import couponsProject.couponsProject_server.beans.Company;
import couponsProject.couponsProject_server.beans.Coupon;
import couponsProject.couponsProject_server.beans.Customer;
import couponsProject.couponsProject_server.services.AdminServices;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@AllArgsConstructor
@RestController
@RequestMapping("admin")
public class AdminController {
    private AdminServices adminServices;

    // int login(String email, String password);

    @GetMapping("/companies")
    public ResponseEntity<List<Company>> getCompanies() {
        log.info("entering @GetMapping getCompanies");
        return ResponseEntity.ok(adminServices.getAllCompanies());
    }

    @GetMapping("/customers")
    public ResponseEntity<List<Customer>> getCustomers() {
        log.info("entering @GetMapping getCustomers");
        return ResponseEntity.ok(adminServices.getAllCustomers());
    }

    @GetMapping("/company/{id}")
    public ResponseEntity<Company> getCompany(@PathVariable int id) {
        log.info("entering @GetMapping getCompany company id:{}",id);
        return ResponseEntity.ok(adminServices.getOneCompany(id));
    }

    @GetMapping("/customer/{id}")
    public ResponseEntity<Customer> getCustomer(@PathVariable int id) {
        log.info("entering @GetMapping getCustomer customer id:{}",id);
        return ResponseEntity.ok(adminServices.getOneCustomer(id));
    }

    @DeleteMapping("/company/{id}/delete")
    public ResponseEntity<String> deleteCompany(@PathVariable int id) {
        log.info("entering @DeleteMapping deleteCompany company id:{}",id);
        adminServices.deleteCompany(id);
        return ResponseEntity.ok("Company deleted");
    }

    @DeleteMapping("/customer/{id}/delete")
    public ResponseEntity<String> deleteCustomer(@PathVariable int id) {
        log.info("entering @DeleteMapping deleteCustomer customer id:{}",id);
        adminServices.deleteCustomer(id);
        return ResponseEntity.ok("Customer deleted");
    }

    @PutMapping("/company/update")
    public ResponseEntity<String> updateCompany(@RequestBody Company company) {
        log.info("entering @PutMapping updateCompany company id:{}",company.getId());
        adminServices.updateCompany(company);
        return ResponseEntity.ok("Company updated");
    }

    @PutMapping("/customer/update")
    public ResponseEntity<String> updateCustomer(@RequestBody Customer customer) {
        log.info("entering @PutMapping updateCustomer customer id:{}",customer.getId());
        adminServices.updateCustomer(customer);
        return ResponseEntity.ok("Customer updated");
    }

    @PostMapping("/company/add")
    public ResponseEntity<String> addCompany(@RequestBody Company company) {
        log.info("entering @PutMapping addCompany company id:{}",company.getId());
        adminServices.addCompany(company);
        return ResponseEntity.ok("Company added");
    }

    @PostMapping("/customer/add")
    public ResponseEntity<String> addCustomer(@RequestBody Customer customer) {
        log.info("entering @PostMapping addCustomer customer id:{}",customer.getId());
        adminServices.addCustomer(customer);
        return ResponseEntity.ok("Customer added");
    }

}




