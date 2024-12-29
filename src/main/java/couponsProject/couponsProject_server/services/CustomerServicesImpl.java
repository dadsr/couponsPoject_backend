package couponsProject.couponsProject_server.services;

import couponsProject.couponsProject_server.beans.CategoryEnum;
import couponsProject.couponsProject_server.beans.Coupon;
import couponsProject.couponsProject_server.beans.Customer;
import couponsProject.couponsProject_server.exseptions.CouponException;
import couponsProject.couponsProject_server.repository.CouponRepository;
import couponsProject.couponsProject_server.repository.CustomerRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@Slf4j
@Service
@Scope("singleton")
public class CustomerServicesImpl implements CustomerServices {
    private CustomerRepository customerRepository;
    private CouponRepository couponRepository;


    /**
     * Authenticates a customer using their email and password.
     *
     * @param email    The email address of the customer
     * @param password The password of the customer
     * @return An integer representing the result of the login attempt:
     *         Typically, a positive value (e.g., customer ID) indicates successful login,
     *         while 0 or a negative value might indicate failed login
     * @throws "SomeSpecificException" if there's an issue with the database connection or query execution
     */
    @Override
    public int login(String email, String password){
        log.info("Entering login using Email: {} Password: {}", email, password);
        return customerRepository.getCustomerByEmailAndPassword(email,password);
    }

    /**
     * Retrieves a customer by their ID.
     *
     * @param customerId The ID of the customer to retrieve
     * @return The CustomerDTO object if found, or null if no customer exists with the given ID
     * @Override Overrides the getCustomer method from a parent class or interface
     */
    @Override
    public Customer getCustomer(int customerId){
        log.info("Entering getCustomer using customerId: {}", customerId);
        return customerRepository.getCustomerById(customerId);
    }

    /**
     * Processes a coupon purchase for a customer.
     *
     * @param customerId The ID of the customer making the purchase
     * @param couponId The ID of the coupon being purchased
     * @throws CouponException if the purchase is not possible (e.g., coupon already purchased or out of stock)
     * @Transactional Ensures that all operations within the method are part of a single transaction
     * @Override Overrides the couponPurchase method from a parent class or interface
     */
    @Transactional
    @Override
    public void couponPurchase(int customerId, int couponId){
        log.info("Entering couponPurchase using customerId: {} couponId: {}", customerId, couponId);
        Coupon coupon = couponRepository.getCouponById(couponId);
        if((!couponRepository.existsPurchase(customerId,couponId)) && coupon.getAmount() > 0){
            Customer customer = customerRepository.getCustomerById(customerId);
            coupon.addCustomer(customer);
            coupon.setAmount(coupon.getAmount()-1);
            customer.addCoupon(coupon);
            couponRepository.save(coupon);
            customerRepository.save(customer);
        }else {
            log.error("Purchase is not possible for customerId: {} couponId: {} amount: {}", customerId, couponId,coupon.getAmount());
            throw new CouponException("Purchase is not possible");
        }

    }

    /**
     * Retrieves all coupons purchased by a specific customer.
     *
     * @param customerId The ID of the customer whose coupons are to be retrieved
     * @return A List of CouponDTO objects purchased by the specified customer
     * @throws CouponException if the customer doesn't exist
     * @Override Overrides the getCoupons method from a parent class or interface
     */
    @Override
    public List<Coupon> getCoupons(int customerId) {
        log.info("Entering getCoupons using customerId: {}", customerId);
        Customer customer = customerRepository.getCustomerById(customerId);
        if(customer.getId() == customerId ){
            return customer.getCoupons();
        }else{
            throw new CouponException("customer doesn't exists");
        }

    }

    /**
     * Retrieves all coupons of a specific category purchased by a customer.
     *
     * @param customerId The ID of the customer whose coupons are to be retrieved
     * @param category The category of coupons to filter by
     * @return A List of CouponDTO objects matching the specified category and purchased by the customer
     *         Returns an empty list if the customer doesn't exist
     * @Override Overrides the getCoupons method from a parent class or interface
     */
    @Override
    public List<Coupon> getCoupons(int customerId, CategoryEnum category) {
        log.info("Entering getCoupons using customerId: {} and category: {}", customerId, category);
        Customer customer = customerRepository.getCustomerById(customerId);
        if (customer != null) {
            return customer.getCoupons().stream()
                    .filter(coupon -> coupon.getCategory().equals(category))
                    .collect(Collectors.toList());
        } else {
            return Collections.emptyList();
        }
    }

    /**
     * Retrieves all coupons purchased by a customer with a price less than or equal to the specified maximum.
     *
     * @param customerId The ID of the customer whose coupons are to be retrieved
     * @param maxPrice The maximum price of coupons to include
     * @return A List of CouponDTO objects purchased by the customer and priced at or below the specified maximum
     *         Returns an empty list if the customer doesn't exist
     * @Override Overrides the getCoupons method from a parent class or interface
     */
    @Override
    public List<Coupon> getCoupons(int customerId, double maxPrice) {
        log.info("Entering getCoupons using customerId: {} and max price of: {}", customerId, maxPrice);
        Customer customer = customerRepository.getCustomerById(customerId);
        if (customer != null) {
            return customer.getCoupons().stream()
                    .filter(coupon -> coupon.getPrice()<=maxPrice)
                    .collect(Collectors.toList());
        } else {
            return Collections.emptyList();
        }
    }

    /**
     * Retrieves a list of available coupons for purchase by a specific customer.
     * This method filters out coupons that are either already purchased by the customer
     * or have an amount of zero, indicating they are not available for purchase.
     *
     * @param customerId the unique identifier of the customer for whom the coupons are being retrieved
     * @return a list of {@code CouponDTO} objects that are available for purchase by the customer.
     *         If the customer does not exist, returns an empty list.
     */
    @Override
    public List<Coupon> getPurchaseCoupons(int customerId) {
        log.info("Entering getPurchaseCoupons using customerId: {} ", customerId);
        Customer customer = customerRepository.getCustomerById(customerId);
        if (customer != null) {
           return couponRepository.findAll().stream()
                    .filter(coupon -> coupon.getAmount() > 0)
                    .filter(coupon -> !customer.getCoupons().contains(coupon))
                    .collect(Collectors.toList());
        } else {
            return Collections.emptyList();
        }
    }

}

