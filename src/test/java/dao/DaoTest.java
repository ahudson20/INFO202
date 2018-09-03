package dao;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import domain.Product;
import java.math.BigDecimal;
import java.util.Collection;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author anaruhudson
 */
public class DaoTest {
    private ProductDao productDao = new ProductDao();
    //private JdbcProductDao productDao = new JdbcProductDao("jdbc:h2:tcp://localhost:9021/project-testing");
    
    private Product prodOne;
    private Product prodTwo;
    private Product prodThree;
    public DaoTest() {
    }
    
    @Before
    public void setUp() {
    this.prodOne = new Product(1, "name1", "cat1", "desc1",
    new BigDecimal("11.00"), new BigDecimal("22.00"));
    
    this.prodTwo = new Product(2, "name2", "cat2", "desc2",
    new BigDecimal("33.00"), new BigDecimal("44.00"));
    
    this.prodThree = new Product(3, "name3", "cat3", "desc3",
    new BigDecimal("55.00"), new BigDecimal("66.00"));
    
    // save the products
    productDao.saveProduct(prodOne);
    productDao.saveProduct(prodTwo);
    // Note: Intentionally not saving prodThree
    }
    
    @After
    public void tearDown() {
    productDao.deleteProduct(prodOne);
    productDao.deleteProduct(prodTwo);
    productDao.deleteProduct(prodThree);
    }

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    // @Test
    // public void hello() {}
    //save, delete, getporducts, getbyid: done
    //getcategories, 
    //filterbycategory.
    //
    @Test
    public void testDaoSave() {
        // save the product using DAO
        productDao.saveProduct(prodThree);
        // retrieve the same product via DAO
        Product retrieved = productDao.getProductById(3);
        // ensure that the product we saved is the one we got back
        assertEquals("Retrieved product should be the same",
        prodThree, retrieved);
    }
    
    @Test
    public void testDaoDelete() {
        // delete the product via the DAO
        productDao.deleteProduct(prodOne);
        // try to retrieve the deleted product
        Product retrieved = productDao.getProductById(1);
        // ensure that the student was not retrieved (should be null)
        assertNull("Product should no longer exist", retrieved);
    }
    
    @Test
    public void testDaoGetAll() {
        Collection<Product> products = productDao.getProducts();
        // ensure the result includes the two saved products
        assertTrue("prodOne should exist", products.contains(prodOne));
        assertTrue("prodTwo should exist", products.contains(prodTwo));
        // ensure the result ONLY includes the two saved products
        assertEquals("Only 2 products in result", 2, products.size());
        // find prodOne - result is not a map, so we have to scan for it
        for (Product p : products) {
            if (p.equals(prodOne)) {
            // ensure that all of the details were correctly retrieved
            assertEquals(prodOne.getProductID(), p.getProductID());
            assertEquals(prodOne.getName(), p.getName());
            assertEquals(prodOne.getDescription(), p.getDescription());
            assertEquals(prodOne.getCategory(), p.getCategory());
            assertEquals(prodOne.getListPrice(), p.getListPrice());
            assertEquals(prodOne.getQuantityInStock(), p.getQuantityInStock());
            }
        }
    }
    
    @Test
    public void testDaoGetAllCategories() {
        Collection<String> categories = productDao.getCategories();
        // ensure the result includes the two saved categories
        assertTrue("prodOne should exist", categories.contains(prodOne.getCategory()));
        assertTrue("prodTwo should exist", categories.contains(prodTwo.getCategory()));
        // ensure the result ONLY includes the two saved categories
        assertEquals("Only 2 categories in result", 2, categories.size());
    }
    
//    @Test
//    public void testDaoFilterByCategory() {
//        
//    }
    
    @Test
    public void testDaoEdit() {
        String newName = "newName1";
        this.prodOne.setName(newName);
        productDao.saveProduct(prodOne);
        productDao.getProductById(1);
        assertEquals("name should now be updated", prodOne.getName(), newName);
    }

    
    
    @Test
    public void testDaoFindById() {
        // get prodOne using findById method
        Product p = productDao.getProductById(1);
        // assert that you got back prodOne, and not another product
        assertEquals("Should have got prodcut 1", prodOne, p);
        // assert that prodOne's details were properly retrieved
        assertEquals(prodOne.getProductID(), p.getProductID());
        assertEquals(prodOne.getName(), p.getName());
        assertEquals(prodOne.getDescription(), p.getDescription());
        assertEquals(prodOne.getCategory(), p.getCategory());
        assertEquals(prodOne.getListPrice(), p.getListPrice());
        assertEquals(prodOne.getQuantityInStock(), p.getQuantityInStock());
        // call getById using a non-existent ID
        Product n = productDao.getProductById(99);
        // assert that the result is null
        assertNull("Product shouldnt exist", n);
        
    }
}
