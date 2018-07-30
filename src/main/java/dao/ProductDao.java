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
    private static ArrayList<domain.Product> productsList = new ArrayList<>();
    private static ArrayList<String> categoryList = new ArrayList<>();

    
    public void saveProduct(domain.Product product){
        productsList.add(product);
    }
    
    public Collection<domain.Product> getProducts(){
        return productsList;
    }
    
    public ArrayList<String> getCategories(){
        for(domain.Product p : productsList){
            categoryList.add(p.getCategory());
        }
        return categoryList;
    }
}
