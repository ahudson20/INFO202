/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package web;

import dao.JdbcProductDao;
import org.jooby.Jooby;

/**
 *
 * @author anaruhudson
 */
public class ProductModule extends Jooby{
    public ProductModule(JdbcProductDao productDao){
        get("/api/products", () -> productDao.getProducts());
        get("/api/products/:id", (req) -> {
        Integer id = req.param("id").intValue();
        return productDao.getProductById(id);
        });
    }
    
}
