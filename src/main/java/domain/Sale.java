/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.*;

/**
 *
 * @author anaruhudson
 */
public class Sale {
    private String saleID;
    private java.time.LocalDate date;
    private String status;
    private Customer customer;
    private final Collection<SaleItem> saleList = new HashSet<>();

    public Sale(String saleID, LocalDate date, String status, Customer customer) {
        this.saleID = saleID;
        this.date = date;
        this.status = status;
        this.customer = customer;
    }

    public String getSaleID() {
        return saleID;
    }

    public LocalDate getDate() {
        return date;
    }

    public String getStatus() {
        return status;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setSaleID(String saleID) {
        this.saleID = saleID;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }
    
    public void addItem(SaleItem saleItem){
        this.saleList.add(saleItem);
    }
    
    /**TODO: add getTotal() method */
    public BigDecimal getTotal(){
        BigDecimal total = BigDecimal.ZERO;
        for(SaleItem s: saleList){
            total = total.add(s.getItemTotal());
        }
        return total;
    }
}
