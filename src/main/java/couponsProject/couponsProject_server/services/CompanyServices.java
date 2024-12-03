package couponsProject.couponsProject_server.services;

import couponsProject.couponsProject_server.beans.Company;
import couponsProject.couponsProject_server.beans.Coupon;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface CompanyServices extends ClientServices {
    int login(String email, String password);

    Company getCompanyDetails(int companyId);

    void addCoupon(Coupon coupon);

    void updateCoupon(Coupon coupon);
    @Transactional
    void deleteCoupon(int couponID);

    List<Coupon> getCompanyCoupons(int companyId);

}
