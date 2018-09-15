/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package web;

import dao.CustomerCollectionsDAO;
import dao.JdbcCustomerDao;
import dao.JdbcProductDao;
import domain.Customer;
import org.jooby.Err;
import org.jooby.Jooby;
import org.jooby.Status;

/**
 *
 * @author anaruhudson
 */
public class CustomerModule extends Jooby{
    
    public CustomerModule(JdbcCustomerDao custDao){
        get("api/customers/:username",  (req) -> {
        String username = req.param("username").value();
        //return custDao.getCustomer(username);
            if(custDao.getCustomer(username) == null){
                throw new Err(Status.NOT_FOUND);
            }else{
                return custDao.getCustomer(username);
            }
        });
        
        post("/api/register", (req, rsp) -> {
        Customer customer = req.body().to(Customer.class);
        custDao.save(customer);
        rsp.status(Status.CREATED);
        }); 
    }
}
