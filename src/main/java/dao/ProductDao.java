/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;
import domain.Product;
import java.util.*;

/**
 *
 * @author hudan995
 */
public class ProductDao implements ProductInterface{
    //private static Collection<Product> productsList = new HashSet<>();
    //private static Collection<String> categoryList = new HashSet<>();
    //private static Map<Integer, Product> idList = new HashMap<>();
    //private static Multimap<String,Product> mm = HashMultimap.create();

    
    @Override
    public void saveProduct(Product product){
        productsList.add(product);
        idList.put(product.getProductID(), product);
        //mm.put(product.getProductID(), product);
    }
    
    @Override
    public void deleteProduct(Product product){
        productsList.remove(product);
        categoryList.remove(product.getCategory());
        idList.remove(product.getProductID());
    }
    
    @Override
    public Collection<Product> getProducts(){
        return productsList;
    }
    
    @Override
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
    @Override
    public Product getProductById(Integer id){
        Product p = idList.get(id);
        if(p == null){
            return null;
        }
        return p;
    }
    
    @Override
    public Collection<Product> filterByCategory(String category){
        Multimap<String,Product> mm = HashMultimap.create();
        for(Product p : productsList){
            mm.put(p.getCategory(), p);
        }
        Collection<Product> elements = mm.get(category);
        return elements;
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
