/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import domain.Product;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 *
 * @author anaruhudson
 */
public class JdbcProductDao implements ProductInterface{
    private String url = "jdbc:h2:tcp://localhost:9021/project;IFEXISTS=TRUE";

    public JdbcProductDao() {
    }
    
    public JdbcProductDao(String url){
        this.url = url;
    }

    @Override
    public void saveProduct(Product product) {
        //String sql="insert into student (id, name, mark) values (?,?,?)";
        String sql = "insert into Product (Product_ID, Product_Name, Description, Category, List_Price, Quantity_In_Stock) values(?,?,?,?,?,?)";

    try (
        // get connection to database
        Connection dbCon = JdbcConnection.getConnection(url);

        // create the statement
        PreparedStatement stmt = dbCon.prepareStatement(sql);
    ) {
        // copy the data from the student domain object into the SQL parameters
        stmt.setInt(1, product.getProductID());
        stmt.setString(2, product.getName());
        stmt.setString(3, product.getDescription());
        stmt.setString(4, product.getCategory());
        stmt.setBigDecimal(5, product.getListPrice());
        stmt.setBigDecimal(6, product.getQuantityInStock());

        stmt.executeUpdate();  // execute the statement

    } catch (SQLException ex) {  // we are forced to catch SQLException
        // don't let the SQLException leak from our DAO encapsulation
        throw new RuntimeException(ex);
    }
    }

    @Override
    public void deleteProduct(Product product) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Collection<Product> getProducts() {
        String sql = "select * from Product";

    try (
        // get a connection to the database
        Connection dbCon = JdbcConnection.getConnection(url);

        // create the statement
        PreparedStatement stmt = dbCon.prepareStatement(sql);
    ) {
        // execute the query
        ResultSet rs = stmt.executeQuery();

        // Using a List to preserve the order in which the data was returned from the query.
        List<Product> products = new ArrayList<>();

        // iterate through the query results
        while (rs.next()) {

            // get the data out of the query
            Integer id = rs.getInt("Product_ID");
            String name = rs.getString("Product_Name");
            String description = rs.getString("Description");
            String category = rs.getString("Category");
            BigDecimal listPrice = rs.getBigDecimal("List_Price");
            BigDecimal quantityInStock = rs.getBigDecimal("Quantity_In_Stock");
            
            //BigDecimal mark = rs.getBigDecimal("mark");

            // use the data to create a student object
            Product p = new Product(id, name, description, category, listPrice, quantityInStock);

            // and put it in the collection
            products.add(p);
        }

        return products;

    } catch (SQLException ex) {
        throw new RuntimeException(ex);
        }
    }

    @Override
    public Collection<String> getCategories() {
         String sql = "select distinct category from Product";

    try (
        // get a connection to the database
        Connection dbCon = JdbcConnection.getConnection(url);

        // create the statement
        PreparedStatement stmt = dbCon.prepareStatement(sql);
    ) {
        // execute the query
        ResultSet rs = stmt.executeQuery();

        // Using a List to preserve the order in which the data was returned from the query.
        List<String> cList = new ArrayList<>();

        // iterate through the query results
        while (rs.next()) {

            // get the data out of the query
            String c = rs.getString("category");

            // and put it in the collection
            cList.add(c);
        }

        return cList;

    } catch (SQLException ex) {
        throw new RuntimeException(ex);
        }
    }

    @Override
    public Product getProductById(String id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Collection<Product> filterByCategory(String category) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
