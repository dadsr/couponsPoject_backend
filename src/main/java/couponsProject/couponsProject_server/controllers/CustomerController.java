package couponsProject.couponsProject_server.controllers;

import couponsProject.couponsProject_server.beans.Coupon;
import couponsProject.couponsProject_server.beans.Customer;
import couponsProject.couponsProject_server.services.CustomerServices;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@AllArgsConstructor
@RestController
@RequestMapping("customer")
public class CustomerController {
    private CustomerServices customerServices;

    //int login(String email, String password);


    @GetMapping("/{id}")
    public ResponseEntity <Customer> getCustomer(@PathVariable int id) {
        return ResponseEntity.ok(customerServices.getCustomer(id));
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


    //List<Coupon> getCoupons(int customerId, CategoryEnum category);

    //List<Coupon> getCoupons(int customerId, double maxPrice);
}
