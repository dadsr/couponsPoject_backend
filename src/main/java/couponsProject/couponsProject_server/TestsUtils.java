package couponsProject.couponsProject_server;

import couponsProject.couponsProject_server.beans.CategoryEnum;
import couponsProject.couponsProject_server.beans.Company;
import couponsProject.couponsProject_server.beans.Coupon;
import couponsProject.couponsProject_server.beans.Customer;

import java.sql.Date;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Random;
import java.util.function.Supplier;

public  class TestsUtils {
    static Random rand = new Random();
    /**
     * Returns the current date as a java.sql.Date object.
     * This method is typically used to set the start date for entities like coupons.
     *
     * @return A java.sql.Date object representing the current date
     */
    private static Date getStartDate() {
        return new Date(System.currentTimeMillis());
    }

    /**
     * Generates a random Date within a specified number of days from the current date.
     *
     * @param maxDays The maximum number of days from now for the random date
     * @return A random Date object within the specified range
     * @throws IllegalArgumentException if maxDays is less than or equal to 0
     */
    public static Date getRandomDateFromNow(int maxDays) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(new java.util.Date());
        cal.add(Calendar.DAY_OF_YEAR, rand.nextInt(maxDays));
        return new Date(cal.getTimeInMillis());
    }

    /**
     * A generic method to create a list of entities.
     *
     * @param <T> The type of entity to create
     * @param num The number of entities to create
     * @param entitySupplier A Supplier functional interface that provides new instances of the entity
     * @return A List of newly created entities of type T
     * @throws IllegalArgumentException if num is less than or equal to 0
     */
    public static <T> List<T> createEntities(int num, Supplier<T> entitySupplier) {
        List<T> entities = new ArrayList<>();
        for (int i = 0; i < num; i++) {
            entities.add(entitySupplier.get());
        }
        return entities;
    }

    /**
     * Generates a list of random CompanyDTO objects.
     *
     * @param num The number of CompanyDTO objects to create
     * @return A List of randomly generated CompanyDTO objects
     * @throws IllegalArgumentException if num is less than or equal to 0
     */
    public static List<Company> createCompanies(int num) {
        return createEntities(num, () -> Company.builder()
                .name("comp" + rand.nextInt(1,999999999)+rand.nextInt(1,999))
                .email(rand.nextInt(1,1000) + "@comp.co.il")
                .password(String.valueOf(rand.nextInt(1,999999999)+rand.nextInt(1,999)))
                .build());
    }

    /**
     * Generates a list of random CustomerDTO objects.
     *
     * @param num The number of CustomerDTO objects to create
     * @return A List of randomly generated CustomerDTO objects
     * @throws IllegalArgumentException if num is less than or equal to 0
     */
    public static List<Customer> createCustomers(int num) {
        return createEntities(num, () -> Customer.builder()
                .firstName("first" + rand.nextInt(1,999999999)+rand.nextInt(1,999))
                .lastName("last")
                .email(rand.nextInt(1,1000) + "@walla.co.il")
                .password(String.valueOf(rand.nextInt(1,999999999)+rand.nextInt(1,999)))
                .build());
    }

    /**
     * Generates a list of random CouponDTO objects associated with a given CompanyDTO.
     * This method does not update the company for the created coupons in the database.
     *
     * @param company The CompanyDTO object to associate with the created coupons
     * @param num The number of CouponDTO objects to create
     * @return A List of randomly generated CouponDTO objects
     */
    public static List<Coupon> createCoupons(Company company ,int num) {

        return createEntities(num,() -> Coupon.builder()
                .company(company)
                .category(CategoryEnum.fromId(rand.nextInt(CategoryEnum.values().length)))
                .title("title" + rand.nextInt(1,999999999)+rand.nextInt(1,999))
                .description("desc" + rand.nextInt(1,999999999)+rand.nextInt(1,999))
                .startDate(getStartDate())
                .endDate(getRandomDateFromNow(30))
                .amount(rand.nextInt(1, 30))
                .price(Math.round(rand.nextDouble(100.00)))
                .image(null)
                .build());
    }


}
