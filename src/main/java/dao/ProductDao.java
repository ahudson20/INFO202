/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import domain.Product;
import java.util.*;

/**
 *
 * @author hudan995
 */
public class ProductDao {
    private static Collection<Product> productsList = new HashSet<>();
    private static Collection<String> categoryList = new HashSet<>();
    private static Map<String, Product> idList = new HashMap<>();

    
    public void saveProduct(Product product){
        productsList.add(product);
        idList.put(product.getProductID(), product);
    }
    
    public void deleteProduct(Product product){
        productsList.remove(product);
    }
    
    public Collection<Product> getProducts(){
        return productsList;
    }
    
    public Collection<String> getCategories(){
        for(Product p : productsList){
            categoryList.add(p.getCategory());
        }
        return categoryList;
    }
//    
//    public int getIdListSize(){
//        return idList.size();
//    }
//    
    public Product getProductById(String id){
        Product p = idList.get(id);
        if(p == null){
            return null;
        }
        return p;
    }
    
    public Collection<Product> filterByCategory(){
        //Multimap<String,Product> mm = HashMultimap.create();
        return null;
    }
//    
//    public void deleteById(String id){
//        idList.remove(id);
//    }
//    
//    public boolean checkProductExists(String id){
//        boolean doesExist = idList.containsKey(id);
//        return doesExist;   
//    }
//    
//    public Set<String> getAllIds(){
//        Set<String> productIDs = idList.keySet();
//        return productIDs;
//    }
//    
//    public Collection<Product> getAllProducts(){
//        Collection<Product> values  = idList.values();
//        return values;
//    }
}
