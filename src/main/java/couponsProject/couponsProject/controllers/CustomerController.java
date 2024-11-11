package couponsProject.couponsProject.controllers;

import couponsProject.couponsProject.beans.CategoryEnum;
import couponsProject.couponsProject.beans.Coupon;
import couponsProject.couponsProject.beans.Customer;
import couponsProject.couponsProject.services.CustomerServices;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@Slf4j
@RequestMapping("customer")
@ControllerAdvice
@RestController
public class CustomerController {
    private CustomerServices customerServices;

    int login(String email, String password);

    @GetMapping("customer/details")
    Customer getCustomer(int customerId) {
        return customerServices.getCustomer(customerId);
    }

    @PostMapping("customer/purchase")
    void couponPurchase(int customerId, int couponId){
        customerServices.couponPurchase(customerId, couponId);
    }

    @GetMapping("customer/coupons")
    List<Coupon> getCoupons(int customerId){
        return customerServices.getCoupons(customerId);
    }

    @GetMapping("customer/coupons")
    List<Coupon> getCoupons(int customerId, CategoryEnum category){
        return customerServices.getCoupons(customerId, category);
    }

    @GetMapping("customer/coupons")
    List<Coupon> getCoupons(int customerId, double maxPrice){
        return customerServices.getCoupons(customerId, maxPrice);
    }

}
