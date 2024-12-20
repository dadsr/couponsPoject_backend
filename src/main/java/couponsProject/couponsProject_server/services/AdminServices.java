package couponsProject.couponsProject_server.services;

import couponsProject.couponsProject_server.beans.Company;
import couponsProject.couponsProject_server.beans.Coupon;
import couponsProject.couponsProject_server.beans.Customer;
import couponsProject.couponsProject_server.exseptions.CompanyException;

import java.util.ArrayList;
import java.util.List;

public interface AdminServices  extends ClientServices {
    int login(String email, String password);

    void addCompany(Company company);

    void updateCompany(Company company);

    void deleteCompany(int companyID);

    Company getOneCompany(int companyID);

    ArrayList<Company> getAllCompanies();

    void addCustomer(Customer customer);

    void updateCustomer(Customer customer);

    void deleteCustomer(int customerID);

    Customer getOneCustomer(int customerID);

    List<Customer> getAllCustomers();

    List<Coupon> findByEndDateBefore(java.sql.Date date);
}
