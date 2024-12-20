package couponsProject.couponsProject_server.controllers;

import couponsProject.couponsProject_server.beans.Company;
import couponsProject.couponsProject_server.beans.Coupon;
import couponsProject.couponsProject_server.beans.Customer;
import couponsProject.couponsProject_server.services.AdminServices;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("ad")
public class AdminController {
    private AdminServices adminServices;

    // int login(String email, String password);

    @GetMapping("/companies")
    public ResponseEntity<List<Company>> getCompanies() {
        return ResponseEntity.ok(adminServices.getAllCompanies());
    }

    @GetMapping("/customers")
    public ResponseEntity<List<Customer>> getCustomers() {
        return ResponseEntity.ok(adminServices.getAllCustomers());
    }

    @GetMapping("/company/{id}")
    public ResponseEntity<Company> getCompany(@PathVariable int id) {
        return ResponseEntity.ok(adminServices.getOneCompany(id));
    }

    @GetMapping("/customer/{id}")
    public ResponseEntity<Customer> getCustomer(@PathVariable int id) {
        return ResponseEntity.ok(adminServices.getOneCustomer(id));
    }

    @DeleteMapping("/company/{id}/delete")
    public ResponseEntity<String> deleteCompany(@PathVariable int id) {
        adminServices.deleteCompany(id);
        return ResponseEntity.ok("Company deleted");
    }

    @DeleteMapping("/customer/{id}/delete")
    public ResponseEntity<String> deleteCustomer(@PathVariable int id) {
        adminServices.deleteCustomer(id);
        return ResponseEntity.ok("Customer deleted");
    }

    @PutMapping("/company/update")
    public ResponseEntity<String> updateCompany(@RequestBody Company company) {
        adminServices.updateCompany(company);
        return ResponseEntity.ok("Company updated");
    }

    @PutMapping("/customer/update")
    public ResponseEntity<String> updateCustomer(@RequestBody Customer customer) {
        adminServices.updateCustomer(customer);
        return ResponseEntity.ok("Customer updated");
    }

    @PostMapping("/company/add")
    public ResponseEntity<String> addCompany(@RequestBody Company company) {
        adminServices.addCompany(company);
        return ResponseEntity.ok("Company added");
    }

    @PostMapping("/customer/add")
    public ResponseEntity<String> addCustomer(@RequestBody Customer customer) {
        adminServices.addCustomer(customer);
        return ResponseEntity.ok("Customer added");
    }

}




