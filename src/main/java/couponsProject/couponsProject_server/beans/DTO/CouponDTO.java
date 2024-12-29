package couponsProject.couponsProject_server.beans.DTO;

import couponsProject.couponsProject_server.beans.CategoryEnum;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;

import java.sql.Date;

@Data
public class CouponDTO {
    private int id;
    @NotBlank(message = "title cannot be blank")
    private String title;
    private String description;
    @Min(value = 1, message = "companyId must be greater than 0")
    private int companyId;
    @NotNull(message = "category cannot be blank")
    private CategoryEnum category;
    @Positive(message = "price must be greater than 0")
    private double price;
    @Min(value = 0, message = "amount cannot be negative")
    private int amount;
    @NotNull(message = "startDate cannot be null")
    private Date startDate;
    @NotNull(message = "endDate cannot be null")
    private Date endDate;
    private String image;
}