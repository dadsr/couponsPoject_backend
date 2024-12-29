package couponsProject.couponsProject_server.beans;


import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a CompanyDTO entity in the system.
 * <p>
 * Annotations:
 * - @NoArgsConstructor: Generates a no-argument constructor.
 * - @Getter/@Setter: Generates getter/setter methods for fields.
 * - @Entity: Marks this class as a persistent entity.
 * - @Table(name = "companies"): Specifies the database table name.
 * <p>
 * Fields:
 * - id: Unique identifier (primary key, auto-generated).
 * - name: Name of the company (setter access restricted).
 * - email: CompanyDTO's email address.
 * - password: CompanyDTO's account password.
 * - childEntities: One-to-many relationship with associated entities.
 *   Fetches eagerly, cascades all operations, and removes orphaned entities.
 */
@Slf4j
@NoArgsConstructor

@Getter
@Setter
@Entity
@Table(name = "companies")
public class  Company {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    @Setter(AccessLevel.NONE)
    private int id;
    private String name;
    private String email;
    private String password;
    @OneToMany(
            fetch = FetchType.EAGER,
            mappedBy = "company",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    @JsonManagedReference
    private List<Coupon> coupons ;

    public Company(String name, String email, String password, List<Coupon> coupons) {
        this.name = name;
        this.email = email;
        this.password = password;
        if (coupons != null) {
            this.coupons = coupons;
            coupons.forEach(coupon -> coupon.setCompany(this));//just in case
        }else{
            this.coupons = new ArrayList<>();
        }
    }

    public Company(int id, String name, String email, String password, List<Coupon> coupons) {

        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
        if (coupons != null)
            this.coupons = coupons;
        else this.coupons = new ArrayList<>();
    }

    /**
     * @Builder Enables builder pattern for flexible object creation
     * <p>
     * Factory method for creating CompanyDTO instances.
     * @param name CompanyDTO name
     * @param email CompanyDTO email
     * @param password Account password
     * @param coupons List of associated coupons
     * @return A new CompanyDTO instance
     */
    @Builder
    public static Company createInstance(String name, String email, String password, List<Coupon> coupons) {
        return new Company(name, email, password, coupons);
    }
    /**
     * Adds a coupon to the company and sets the company reference in the coupon.
     * @param coupon The coupon to be added
     */
    public void addCoupon(Coupon coupon) {
        log.info("Entering addCoupon using coupon id: {}", coupon.getId());
        this.coupons.add(coupon);
        coupon.setCompany(this);
    }
    /**
     * Removes a coupon from the company and clears the company reference in the coupon.
     * @param coupon The coupon to be removed
     */
    public void removeCoupon(Coupon coupon) {
        log.info("Entering removeCoupon using coupon id: {}", coupon.getId());
        this.coupons.remove(coupon);
        coupon.setCompany(null);
    }
}