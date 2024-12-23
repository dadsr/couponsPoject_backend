package couponsProject.couponsProject_server.controllers;

import couponsProject.couponsProject_server.beans.Company;
import couponsProject.couponsProject_server.beans.Coupon;
import couponsProject.couponsProject_server.services.CompanyServices;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@AllArgsConstructor
@RestController
@RequestMapping("company")
public class CompanyController {
    private CompanyServices companyService;

  //  int login(String email, String password);


    @GetMapping("/{id}")
    public ResponseEntity<Company> getCompany(@PathVariable int id) {
        log.info("entering @GetMapping getCompany customer id:{}",id);
        return ResponseEntity.ok(companyService.getCompanyDetails(id));
    }

    @GetMapping("/coupons/{companyId}")
    public ResponseEntity<List<Coupon>> getCoupons(@PathVariable int companyId) {
        log.info("entering @GetMapping getCoupons company id:{}",companyId);
        return ResponseEntity.ok(companyService.getCompanyCoupons(companyId));
    }

    @PutMapping("/coupon/update")
    public ResponseEntity<String> updateCoupon(@RequestBody Coupon coupon) {
        log.info("entering @PutMapping updateCoupon coupon id:{}",coupon.getId());
        companyService.updateCoupon(coupon);
        return ResponseEntity.ok("coupon updated");

    }

    @PostMapping("/coupon/add")
    public ResponseEntity <String> addCoupon(@RequestBody Coupon coupon) {
        log.info("entering @PostMapping addCoupon coupon id:{}",coupon.getId());
        companyService.addCoupon(coupon);
        return ResponseEntity.ok("coupon added");

    }

    @DeleteMapping("/coupon/{id}/delete")
    public ResponseEntity<String> deleteCoupon(@PathVariable int id) {
        log.info("entering @DeleteMapping deleteCoupon coupon id:{}",id);
        companyService.deleteCoupon(id);
        return ResponseEntity.ok("coupon deleted");
    }

}
