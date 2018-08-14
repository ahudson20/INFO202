/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;

import java.math.BigDecimal;
import net.sf.oval.constraint.*;

/**
 *
 * @author hudan995
 */
public class Product {
    @NotNull(message = "ID must be provided.")
    @NotNegative(message = "ID must be zero or greater.")
    private Integer productID;
    
    @NotNull(message = "Name must be provided.")
    @NotBlank(message = "Name must be provided.")
    @Length(min=2, message="Name must contain at least two characters.")
    private String name;
    
    @NotNull(message = "Description must be provided.")
    @NotBlank(message = "Description must be provided.")
    @Length(min=2, message="Description must contain at least two characters.")
    private String description;
    
    @NotNull(message = "Category must be provided.")
    @NotBlank(message = "Category must be provided.")
    @Length(min=2, message="Category must contain at least two characters.")
    private String category;
    
    @NotNull(message = "Price must be provided.")
    @NotNegative(message = "Price must be zero or greater.")    
    private BigDecimal listPrice;
    
    @NotNull(message = "Quantity must be provided.")
    @NotNegative(message = "Quantity must be zero or greater.")
    private BigDecimal quantityInStock;
    
    public Product(){
    }

    public Product(Integer productID, String name, String description, String category, BigDecimal listPrice, BigDecimal quantityInStock) {
        this.productID = productID;
        this.name = name;
        this.description = description;
        this.category = category;
        this.listPrice = listPrice;
        this.quantityInStock = quantityInStock;
    }

    public Integer getProductID() {
        return productID;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getCategory() {
        return category;
    }

    public BigDecimal getListPrice() {
        return listPrice;
    }

    public BigDecimal getQuantityInStock() {
        return quantityInStock;
    }

    public void setProductID(Integer productID) {
        this.productID = productID;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public void setListPrice(BigDecimal listPrice) {
        this.listPrice = listPrice;
    }

    public void setQuantityInStock(BigDecimal quantityInStock) {
        this.quantityInStock = quantityInStock;
    }
    
    @Override
    public String toString(){
        return "ID: " + productID +" Name: " + name;
    }
}
