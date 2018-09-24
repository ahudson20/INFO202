/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;

import java.math.BigDecimal;
import java.util.Date;
import java.util.*;

/**
 *
 * @author anaruhudson
 */
public class Sale {
    private Integer saleID;
    private Date date;
    private String status;
    private Customer customer;
    private final Collection<SaleItem> saleList = new HashSet<>();

    public Sale(Integer saleID, Date date, String status, Customer customer) {
        this.saleID = saleID;
        this.date = date;
        this.status = status;
        this.customer = customer;
    }

    public Integer getSaleID() {
        return saleID;
    }

    public Date getDate() {
        return date;
    }

    public String getStatus() {
        return status;
    }

    public Customer getCustomer() {
        return customer;
    }

    public Collection<SaleItem> getItems(){ return saleList; }

    public void setSaleID(Integer saleID) {
        this.saleID = saleID;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public void addItem(SaleItem saleItem) {
        this.saleList.add(saleItem);
    }

    /**
     * TODO: add getTotal() method
     */
    public BigDecimal getTotal() {
        BigDecimal total = BigDecimal.ZERO;
        for (SaleItem s : saleList) {
            total = total.add(s.getItemTotal());
        }
        return total;
    }

    @Override
    public String toString() {
        return "Sale{" +
                "saleID='" + saleID + '\'' +
                ", date=" + date +
                ", status='" + status + '\'' +
                ", customer=" + customer +
                ", saleList=" + saleList +
                '}';
    }
}
