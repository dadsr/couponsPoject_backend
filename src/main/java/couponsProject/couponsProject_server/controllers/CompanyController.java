package couponsProject.couponsProject_server.controllers;

import couponsProject.couponsProject_server.beans.Company;
import couponsProject.couponsProject_server.beans.Coupon;
import couponsProject.couponsProject_server.services.CompanyServices;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@CrossOrigin
@RequestMapping("com")
public class CompanyController {
    private CompanyServices companyService;

  //  int login(String email, String password);


    @GetMapping("/company/{id}")
    public Company getCompany(@PathVariable int id) {
        return companyService.getCompanyDetails(id);
    }

    @GetMapping("/coupons/{companyId}")
    public List<Coupon> getCoupons(@PathVariable int companyId) {
        return companyService.getCompanyCoupons(companyId);
    }

    @PutMapping("/coupon")
    public void updateCoupon(@RequestBody Coupon coupon) {
        companyService.updateCoupon(coupon);
    }

    @PostMapping("/coupon/add")
    public void addCoupon(@RequestBody Coupon coupon) {
        companyService.addCoupon(coupon);
    }

    @GetMapping("/coupon/delete/{id}")
    public void deleteCoupon(@PathVariable int id) {
        companyService.deleteCoupon(id);
    }



}
