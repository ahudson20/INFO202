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

import static org.assertj.swing.core.matcher.DialogMatcher.withTitle;
import static org.assertj.swing.core.matcher.JButtonMatcher.withText;
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
import org.mockito.Mockito;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

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
	robot.settings().delayBetweenEvents(250);
        
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

        Collection<String> cList = new HashSet<>();
        cList.add(prodOne.getCategory());
        cList.add(prodTwo.getCategory());
        cList.add(prodThree.getCategory());
 
	// create a mock for the DAO
	dao = mock(ProductDao.class);
 
	// stub the getProducts method to return the test products
	when(dao.getProducts()).thenReturn(pList);

	// stub the getProductsById method to return single product
        when(dao.getProductById(1)).thenReturn(prodOne);

        //stub the getCategories method
        when(dao.getCategories()).thenReturn(cList);

	// stub the deleteProduct method
        Mockito.doAnswer(new Answer<Void>() {
            @Override
            public Void answer(InvocationOnMock invocation) throws Throwable {
                pList.remove(prodOne);
                return null;
            }
        }).when(dao).deleteProduct(prodOne);
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

        // get the model
        //SimpleListModel model = (SimpleListModel) fixture.list("comboBoxCategory").target().getModel();
                
 
		// verify that the UI componenents contains the product's details
		fixture.textBox("txtName").requireText(prodFour.getName());
		fixture.textBox("txtareaDescription").requireText(prodFour.getDescription());
		fixture.comboBox("comboBoxCategory").requireSelection("desc4");
		fixture.textBox("txtPrice").requireText(prodFour.getListPrice().toString());
		fixture.textBox("txtQuantity").requireText(prodFour.getQuantityInStock().toString());
 
		//edit name and description
		fixture.textBox("txtName").selectAll().deleteText().enterText("Magazine");
		fixture.textBox("txtareaDescription").selectAll().deleteText().enterText("Changed");
 
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
		assertEquals("Ensure the category was changed", "Changed", editedProduct.getDescription());
	}

        @Test
	public void testSave() {
		// create the dialog passing in the mocked DAO
		ProductEditor dialog = new ProductEditor(null, true, dao);
 
		// use AssertJ to control the dialog
		fixture = new DialogFixture(robot, dialog);
		fixture.show().requireVisible();
 
		// enter some details into the UI components
		fixture.textBox("txtID").enterText("99");
		fixture.textBox("txtName").enterText("phone");
		fixture.textBox("txtareaDescription").enterText("square");
		fixture.comboBox("comboBoxCategory").enterText("Misc");
		fixture.textBox("txtPrice").enterText("1");
		fixture.textBox("txtQuantity").enterText("1");
 
		// click the save button
		fixture.button("buttonSave").click();
 
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
		assertEquals("Ensure the price was saved", "1", savedProduct.getListPrice().toString());
		assertEquals("Ensure the quantity was saved", "1", savedProduct.getQuantityInStock().toString());
	}
        
        @Test
        public void testView(){
            ProductViewer dialog = new ProductViewer(null, true, dao);
            
            // use AssertJ to control the dialog
            fixture = new DialogFixture(robot, dialog);
            fixture.show().requireVisible();

            SimpleListModel model = (SimpleListModel) fixture.list("productsList").target().getModel();
//            SimpleListModel cModel = (SimpleListModel) fixture.list("comboFilter").target().getModel();

            Product testProduct = prodOne;
            // check the contents, check that prodOne is there, and that there is a total of 3 products present
            assertTrue("list contains the expected product", model.contains(testProduct));
            assertEquals("list contains the correct number of products", 3, model.getSize());

            //enter 1 into search by id box
            fixture.textBox("txtSearch").enterText("1");
            // click the search button
            fixture.button("buttonSearch").click();

            verify(dao).getProductById(1);

            model = (SimpleListModel) fixture.list("productsList").target().getModel();
            // check the contents, check that prodOne is there, and that there is 1 prodcut present
            assertTrue("list contains the expected product", model.contains(testProduct));
            assertEquals("list contains the correct number of products", 1, model.getSize());

            fixture.list("productsList").selectItem(testProduct.toString());
            fixture.button("buttonEdit").click();

            // find the data entry dialog that appears and ensure it is correct
            DialogFixture editDialog = fixture.dialog("productDialog");
            editDialog.textBox("txtID").requireText(testProduct.getProductID().toString());
            editDialog.textBox("txtName").requireText(testProduct.getName());
            editDialog.textBox("txtareaDescription").requireText(testProduct.getDescription());
            editDialog.comboBox("comboBoxCategory").requireSelection(testProduct.getCategory());
            editDialog.textBox("txtPrice").requireText(testProduct.getListPrice().toString());
            editDialog.textBox("txtQuantity").requireText(testProduct.getQuantityInStock().toString());

            //return back to view all prodcuts window
            editDialog.button("buttonCancel").click();
            fixture.show().requireVisible();

            //select product one to delete
            fixture.list("productsList").selectItem(testProduct.toString());
            fixture.button("deleteButton").click();

            // ensure a confirmation dialog is displayed
            DialogFixture confirmDialog = fixture.dialog(withTitle("Confirmation").andShowing()).requireVisible();

            // click the Yes button on the confirmation dialog
            confirmDialog.button(withText("Yes")).click();

            model = (SimpleListModel) fixture.list("productsList").target().getModel();
            // check the contents, check that prodOne is there, and that there is 1 prodcut present
            assertTrue("list contains the expected product", !(model.contains(testProduct)));
            assertEquals("list contains the correct number of products", 2, model.getSize());


//            fixture.comboBox("comboFilter").selectItem("desc2");
//
//            model = (SimpleListModel) fixture.list("productsList").target().getModel();
//            // check the contents, check that prodOne is there, and that there is 1 prodcut present
//            assertTrue("list contains the expected product", model.contains(prodTwo));
//            assertEquals("list contains the correct number of products", 1, model.getSize());

        }
}
