package couponsProject.couponsProject_server.controllers;

import couponsProject.couponsProject_server.beans.Company;
import couponsProject.couponsProject_server.beans.Coupon;
import couponsProject.couponsProject_server.beans.DTO.CouponDTO;
import couponsProject.couponsProject_server.services.CompanyServices;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
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


    @GetMapping("/{companyId}")
    public ResponseEntity<Company> getCompany(@PathVariable int companyId) {
        log.info("entering @GetMapping getCompany customer id:{}",companyId);
        return ResponseEntity.ok(companyService.getCompanyDetails(companyId));
    }

    @GetMapping("/coupons/{companyId}")
    public ResponseEntity<List<Coupon>> getCoupons(@PathVariable int companyId) {
        log.info("entering @GetMapping getCoupons company id:{}",companyId);
        return ResponseEntity.ok(companyService.getCompanyCoupons(companyId));
    }

    @PutMapping(value = "/coupon/update")
    public ResponseEntity<String> updateCoupon(@Valid @RequestBody CouponDTO couponDTO) {
        log.info("entering @PutMapping updateCoupon coupon id:{}",couponDTO.getId());
        Coupon coupon = companyService.getCouponById(couponDTO.getId());
        coupon.setTitle(couponDTO.getTitle());
        coupon.setDescription(couponDTO.getDescription());
        coupon.setCompany(companyService.getCompanyDetails(couponDTO.getCompanyId()));
        coupon.setCategory(couponDTO.getCategory());
        coupon.setPrice(couponDTO.getPrice());
        coupon.setAmount(couponDTO.getAmount());
        coupon.setStartDate(couponDTO.getStartDate());
        coupon.setEndDate(couponDTO.getEndDate());
        companyService.updateCoupon(coupon);
        return ResponseEntity.ok("coupon updated");
    }

    @PostMapping("/coupon/add")
    public ResponseEntity <String> addCoupon(@Valid @RequestBody CouponDTO couponDTO) {
        log.info("entering @PostMapping addCoupon coupon id:{}",couponDTO.getId());
        Coupon coupon = Coupon.builder()
                .title(couponDTO.getTitle())
                .description(couponDTO.getDescription())
                .company(companyService.getCompanyDetails(couponDTO.getCompanyId()))
                .category(couponDTO.getCategory())
                .price(couponDTO.getPrice())
                .amount(couponDTO.getAmount())
                .startDate(couponDTO.getStartDate())
                .endDate(couponDTO.getEndDate())
                .image(couponDTO.getImage())
                .build();
        companyService.addCoupon(coupon);
        return ResponseEntity.ok("coupon added");

    }

    @DeleteMapping("/coupon/{couponId}/delete")
    public ResponseEntity<String> deleteCoupon(@PathVariable int couponId) {
        log.info("entering @DeleteMapping deleteCoupon coupon id:{}",couponId);
        companyService.deleteCoupon(couponId);
        return ResponseEntity.ok("coupon deleted");
    }

}
