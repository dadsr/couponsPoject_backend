package couponsProject.couponsProject_server.controllers;

import couponsProject.couponsProject_server.beans.Coupon;
import couponsProject.couponsProject_server.beans.Customer;
import couponsProject.couponsProject_server.services.CustomerServices;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@AllArgsConstructor
@RestController
@RequestMapping("customer")
public class CustomerController {
    private CustomerServices customerServices;

    //int login(String email, String password);


    @GetMapping("/{customerId}")
    public ResponseEntity <Customer> getCustomer(@PathVariable int customerId) {
        return ResponseEntity.ok(customerServices.getCustomer(customerId));
    }

    @GetMapping("/{customerId}/coupons/")
    public ResponseEntity <List<Coupon>> getCoupons(@PathVariable int customerId) {
        return ResponseEntity.ok(customerServices.getCoupons(customerId));
    }

    @GetMapping("/{customerId}/purchase_coupons/")
    public ResponseEntity <List<Coupon>> purchaseCoupons(@PathVariable int customerId) {
        return ResponseEntity.ok(customerServices.getPurchaseCoupons(customerId));
    }

    @PostMapping("/{customerId}/coupon/{couponId}")
    public ResponseEntity <String> couponPurchase(@PathVariable int customerId, @PathVariable int couponId) {
        customerServices.couponPurchase(customerId, couponId);
        return ResponseEntity.ok("purchase accepted");
    }


    //List<CouponDTO> getCoupons(int customerId, CategoryEnum category);

    //List<CouponDTO> getCoupons(int customerId, double maxPrice);
}
