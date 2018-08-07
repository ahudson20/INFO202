/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import domain.Product;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

/**
 *
 * @author anaruhudson
 */
public interface ProductInterface {
    public static Collection<Product> productsList = new HashSet<>();
    public static Collection<String> categoryList = new HashSet<>();
    public static Map<String, Product> idList = new HashMap<>();
    
    
    public void saveProduct(Product product);
    public void deleteProduct(Product product);
    public Collection<Product> getProducts();
    public Collection<String> getCategories();
    public Product getProductById(String id);
    public Collection<Product> filterByCategory(String category);
}
