package couponsProject.couponsProject_server.controllers;

import couponsProject.couponsProject_server.beans.Coupon;
import couponsProject.couponsProject_server.beans.Customer;
import couponsProject.couponsProject_server.services.CustomerServices;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@AllArgsConstructor
@RestController
@CrossOrigin
@RequestMapping("cus")
public class CustomerController {
    private CustomerServices customerServices;

    //int login(String email, String password);


    @GetMapping("/customer/{id}")
    public Customer getCustomer(@PathVariable int id) {
        return customerServices.getCustomer(id);
    }

    @GetMapping("/customer/{customerId}/coupons/")
    public List<Coupon> getCoupons(@PathVariable int customerId) {
        return customerServices.getCoupons(customerId);
    }

    @PostMapping("/customer/{customerId}/coupon/{couponId}")
    public void couponPurchase(@PathVariable int customerId, @PathVariable int couponId) {
        customerServices.couponPurchase(customerId, couponId);
    }

    //List<Coupon> getCoupons(int customerId, CategoryEnum category);

    //List<Coupon> getCoupons(int customerId, double maxPrice);
}
