/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.util.*;

/**
 *
 * @author hudan995
 */
public class ProductDao {
    private static Collection<domain.Product> productsList = new HashSet<>();
    private static Collection<String> categoryList = new HashSet<>();

    
    public void saveProduct(domain.Product product){
        productsList.add(product);
    }
    
    public void deleteProduct(domain.Product product){
        productsList.remove(product);
    }
    
    public Collection<domain.Product> getProducts(){
        return productsList;
    }
    
    public Collection<String> getCategories(){
        for(domain.Product p : productsList){
            categoryList.add(p.getCategory());
        }
        return categoryList;
    }
}
