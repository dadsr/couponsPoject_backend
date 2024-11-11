package couponsProject.couponsProject.controllers;

import couponsProject.couponsProject.beans.CategoryEnum;
import couponsProject.couponsProject.beans.Company;
import couponsProject.couponsProject.beans.Coupon;
import couponsProject.couponsProject.beans.Customer;
import couponsProject.couponsProject.services.CompanyServices;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.util.List;

@AllArgsConstructor
@Slf4j
@RequestMapping("company")
@ControllerAdvice
@RestController
public class CompanyController {
    private CompanyServices companyServices;


    int login(String email, String password);

    @GetMapping("company/details")
    Company getCompanyDetails(int companyId) {
        return companyServices.getCompanyDetails(companyId);
    }
    @PostMapping("company/add")
    void addCoupon(Company company, CategoryEnum category, String title, String description, Date startDate, Date endDate, int amount, double price, String image) {
        companyServices.addCoupon(coupon);
    }

    @PostMapping("company/update")
    void updateCoupon(Company company, CategoryEnum category, String title, String description, Date startDate, Date endDate, int amount, double price, String image) {
        companyServices.updateCoupon(coupon);
    }
    @Transactional
    @DeleteMapping("company/delete")
    void deleteCoupon(int couponID) {
        companyServices.deleteCoupon(couponID);
    }
    @GetMapping("company/all")
    List<Coupon> getCompanyCoupons(int companyId){
        return companyServices.getCompanyCoupons(companyId);
    }
}
