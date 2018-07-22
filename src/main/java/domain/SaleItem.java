/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;

/**
 *
 * @author anaruhudson
 */
public class SaleItem {
    private int quantityPurchased;
    private double salePrice;
    private Product product;
    private Sale sale;

    public SaleItem(int quantityPurchased, double salePrice, Product product, Sale sale) {
        this.quantityPurchased = quantityPurchased;
        this.salePrice = salePrice;
        this.product = product;
        this.sale = sale;
    }

    public int getQuantityPurchased() {
        return quantityPurchased;
    }

    public double getSalePrice() {
        return salePrice;
    }

    public Product getProduct() {
        return product;
    }

    public Sale getSale() {
        return sale;
    }

    public void setQuantityPurchased(int quantityPurchased) {
        this.quantityPurchased = quantityPurchased;
    }

    public void setSalePrice(double salePrice) {
        this.salePrice = salePrice;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public void setSale(Sale sale) {
        this.sale = sale;
    }
    
    public double getItemTotal(){
        double total = (double)this.quantityPurchased * this.salePrice;
        return total;
    }
}
