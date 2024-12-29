package couponsProject.couponsProject_server.repository;

import couponsProject.couponsProject_server.beans.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
/**
 * Repository interface for CustomerDTO entity operations.
 * Extends JpaRepository for basic CRUD operations.
 */

public interface CustomerRepository extends JpaRepository<Customer, Integer> {


    //@Query("select c from CustomerDTO c where c.id = ?1")
    Customer getCustomerById(int customerID);

    //@Query("select c from CustomerDTO c where c.email = ?1 and c.password = ?2")
    Customer findCustomerByEmailAndPassword(String email, String password);

    //@Query("select (count(c) > 0) from CustomerDTO c where c.email = ?1")
    boolean existsByEmail(String email);

    @Query("select c.id from Customer c where c.email = ?1 and c.password = ?2")
    Integer getCustomerByEmailAndPassword(String email, String password);

}