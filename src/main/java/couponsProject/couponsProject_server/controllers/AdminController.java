package couponsProject.couponsProject_server.controllers;

import couponsProject.couponsProject_server.beans.Company;
import couponsProject.couponsProject_server.beans.Coupon;
import couponsProject.couponsProject_server.beans.Customer;
import couponsProject.couponsProject_server.beans.DTO.CompanyDTO;
import couponsProject.couponsProject_server.beans.DTO.CustomerDTO;
import couponsProject.couponsProject_server.services.AdminServices;
import jakarta.validation.Valid;
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

    @GetMapping("/company/{companyId}")
    public ResponseEntity<Company> getCompany(@PathVariable int companyId) {
        log.info("entering @GetMapping getCompany company id:{}", companyId);
        return ResponseEntity.ok(adminServices.getOneCompany(companyId));
    }

    @GetMapping("/customer/{customerId}")
    public ResponseEntity<Customer> getCustomer(@PathVariable int customerId) {
        log.info("entering @GetMapping getCustomer customer id:{}", customerId);
        return ResponseEntity.ok(adminServices.getOneCustomer(customerId));
    }

    @DeleteMapping("/company/{companyId}/delete")
    public ResponseEntity<String> deleteCompany(@PathVariable int companyId) {
        log.info("entering @DeleteMapping deleteCompany company id:{}", companyId);
        adminServices.deleteCompany(companyId);
        return ResponseEntity.ok("CompanyDTO deleted");
    }

    @DeleteMapping("/customer/{customerId}/delete")
    public ResponseEntity<String> deleteCustomer(@PathVariable int customerId) {
        log.info("entering @DeleteMapping deleteCustomer customer id:{}", customerId);
        adminServices.deleteCustomer(customerId);
        return ResponseEntity.ok("CustomerDTO deleted");
    }

    @PutMapping("/company/update")
    public ResponseEntity<String> updateCompany(@Valid @RequestBody CompanyDTO companyDTO) {
        log.info("entering @PutMapping updateCompany company id:{}", companyDTO.getId());
        Company company = adminServices.getOneCompany(companyDTO.getId());
        company.setName(companyDTO.getName());
        company.setEmail(companyDTO.getEmail());
        adminServices.updateCompany(company);
        return ResponseEntity.ok("Company updated");
    }

    @PutMapping("/customer/update")
    public ResponseEntity<String> updateCustomer(@Valid @RequestBody CustomerDTO customerDTO) {
        log.info("entering @PutMapping updateCustomer customer id:{}", customerDTO.getId());
        Customer customer = adminServices.getOneCustomer(customerDTO.getId());
        customer.setFirstName(customerDTO.getFirstName());
        customer.setLastName(customerDTO.getLastName());
        customer.setEmail(customerDTO.getEmail());
        adminServices.updateCustomer(customer);
        return ResponseEntity.ok("Customer updated");
    }

    @PostMapping("/company/add")
    public ResponseEntity<String> addCompany(@Valid @RequestBody CompanyDTO companyDTO) {
        log.info("entering @PutMapping addCompany company id:{}", companyDTO.getId());
        Company company = Company.builder()
                .name(companyDTO.getName())
                .email(companyDTO.getEmail())
                .password(companyDTO.getPassword())
                .build();
        adminServices.addCompany(company);
        return ResponseEntity.ok("Company added");
    }

    @PostMapping("/customer/add")
    public ResponseEntity<String> addCustomer(@Valid @RequestBody CustomerDTO customerDTO) {
        log.info("entering @PutMapping addCustomer company id:{}", customerDTO.getId());
        Customer customer = Customer.builder()
                .firstName(customerDTO.getFirstName())
                .lastName(customerDTO.getLastName())
                .email(customerDTO.getEmail())
                .password(customerDTO.getPassword())
                .build();
        adminServices.addCustomer(customer);
        return ResponseEntity.ok("customer added");
    }


}




