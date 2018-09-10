/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import dao.JdbcProductDao;
import dao.ProductDao;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import domain.Product;
import gui.helpers.SimpleListModel;
import java.math.BigDecimal;
import java.util.Collection;
import java.util.HashSet;
import java.util.TreeSet;
import org.assertj.swing.core.BasicRobot;
import org.assertj.swing.core.Robot;
import org.assertj.swing.fixture.DialogFixture;
import org.junit.After;
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 *
 * @author anaruhudson
 */
public class guiTest {
    
    private ProductDao dao;
    private DialogFixture fixture;
    private Robot robot;
    
    private Product prodOne;
    private Product prodTwo;
    private Product prodThree;
    
    public guiTest() {
    }
    
    @Before
    public void setUp() {
        robot = BasicRobot.robotWithNewAwtHierarchy();
 
	// Slow down the robot a little bit - default is 30 (milliseconds).
	// Do NOT make it less than 5 or you will have thread-race problems.
	robot.settings().delayBetweenEvents(100);
        
	// add some products for testing with
        this.prodOne = new Product(1, "name1", "cat1", "desc1",
        new BigDecimal("11.00"), new BigDecimal("22.00"));
    
        this.prodTwo = new Product(2, "name2", "cat2", "desc2",
        new BigDecimal("33.00"), new BigDecimal("44.00"));
    
        this.prodThree = new Product(3, "name3", "cat3", "desc3",
        new BigDecimal("55.00"), new BigDecimal("66.00"));
        
	Collection<Product> pList = new HashSet<>();
        pList.add(prodOne);
        pList.add(prodTwo);
        pList.add(prodThree);
 
	// create a mock for the DAO
	dao = mock(ProductDao.class);
 
	// stub the getProducts method to return the test majors
	when(dao.getProducts()).thenReturn(pList);
    }
    
    @After
    public void tearDown() {
        fixture.cleanUp();
    }
    
    @Test
	public void testEdit() {
		// a product to edit
		Product prodFour = new Product(4, "name4", "cat4", "desc4", new BigDecimal("100.00"), new BigDecimal("99.99"));
 
		// create dialog passing in student and mocked DAO
		ProductEditor dialog = new ProductEditor(null, true, prodFour, dao);
 
		// use AssertJ to control the dialog
		fixture = new DialogFixture(robot, dialog);
 
		// show the dialog on the screen, and ensure it is visible
		fixture.show().requireVisible();
                
 
		// verify that the UI componenents contains the product's details
		//fixture.textBox("txtId").requireText("4");
		fixture.textBox("txtName").requireText(prodFour.getName());
                fixture.textBox("txtareaDescription").requireText(prodFour.getDescription());
		fixture.comboBox("comboBoxCategory").requireSelection(prodFour.getCategory());
                fixture.textBox("txtPrice").requireText(prodFour.getListPrice().toString());
                fixture.textBox("txtQuantity").requireText(prodFour.getQuantityInStock().toString());
 
		//edit name and category
		fixture.textBox("txtName").selectAll().deleteText().enterText("Magazine");
		fixture.comboBox("comboBoxCategory").selectItem("cat1");
 
		// click the save button
		fixture.button("buttonSave").click();
 
		// create a Mockito argument captor to use to retrieve the passed product from the mocked DAO
		ArgumentCaptor<Product> argument = ArgumentCaptor.forClass(Product.class);
 
		// verify that the DAO.save method was called, and capture the passed product
		verify(dao).saveProduct(argument.capture());
 
		// retrieve the passed student from the captor
		Product editedProduct = argument.getValue();
 
		// check that the changes were saved
		assertEquals("Ensure the name was changed", "Magazine", editedProduct.getName());
		assertEquals("Ensure the category was changed", "cat1", editedProduct.getCategory());
	}

        @Test
	public void testSave() {
		// create the dialog passing in the mocked DAO
		ProductEditor dialog = new ProductEditor(null, true, dao);
 
		// use AssertJ to control the dialog
		fixture = new DialogFixture(robot, dialog);
		fixture.show().requireVisible();
 
		// enter some details into the UI components
		fixture.textBox("txtID").requireText("99");
		fixture.textBox("txtName").requireText("phone");
                fixture.textBox("txtareaDescription").requireText("square");
		fixture.comboBox("comboBoxCategory").requireSelection("Misc");
                fixture.textBox("txtPrice").requireText("1.00");
                fixture.textBox("txtQuantity").requireText("1.00");
 
		// click the save button
		fixture.button("btnSave").click();
 
		// create a Mockito argument captor to use to retrieve the passed student from the mocked DAO
		ArgumentCaptor<Product> argument = ArgumentCaptor.forClass(Product.class);
 
		// verify that the DAO.save method was called, and capture the passed student
		verify(dao).saveProduct(argument.capture());
 
		// retrieve the passed student from the captor
		Product savedProduct = argument.getValue();
 
		// test that the student's details were properly saved
		assertEquals("Ensure the ID was saved", new Integer(99), savedProduct.getProductID());
		assertEquals("Ensure the name was saved", "phone", savedProduct.getName());
		assertEquals("Ensure the description was saved", "square", savedProduct.getDescription());
                assertEquals("Ensure the category was saved", "Misc", savedProduct.getCategory());
                assertEquals("Ensure the price was saved", "1.00", savedProduct.getListPrice().toString());
                assertEquals("Ensure the quantity was saved", "1.00", savedProduct.getQuantityInStock().toString());
	}
        
        @Test
        public void testView(){
            ProductViewer dialog = new ProductViewer(null, true, dao);
            
            // use AssertJ to control the dialog
            fixture = new DialogFixture(robot, dialog);
            fixture.show().requireVisible();
            
            SimpleListModel model = (SimpleListModel) fixture.list("productsList").target().getModel();
            Product testProduct = prodOne;
            // check the contents
            assertTrue("list contains the expected product", model.contains(testProduct));
            assertEquals("list contains the correct number of products", 3, model.getSize());              
        }
}
