package dao;

import domain.Customer;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class JdbcCustomerDao implements CustomerDao {
    private String url = "jdbc:h2:tcp://localhost:9021/project;IFEXISTS=TRUE";

    public JdbcCustomerDao() {
    }

    public JdbcCustomerDao(String url){
        this.url = url;
    }

    @Override
    public void save(Customer customer) {
        //String sql="insert into student (id, name, mark) values (?,?,?)";
        String sql = "merge into Customer (Customer_UserName, First_Name, Last_Name, Email, Address, Credit_Card_Details ,Password) values(?,?,?,?,?,?,?)";

        try (
                // get connection to database
                Connection dbCon = JdbcConnection.getConnection(url);

                // create the statement
                PreparedStatement stmt = dbCon.prepareStatement(sql);
        ) {
            // copy the data from the student domain object into the SQL parameters
            stmt.setString(1, customer.getUserName());
            stmt.setString(2, customer.getFirstname());
            stmt.setString(3, customer.getSurname());
            stmt.setString(4, customer.getEmailAddress());
            stmt.setString(5, customer.getShippingAddress());
            stmt.setString(6, customer.getCreditCardDetails());
            stmt.setString(7, customer.getPassword());

            stmt.executeUpdate();  // execute the statement

        } catch (SQLException ex) {  // we are forced to catch SQLException
            // don't let the SQLException leak from our DAO encapsulation
            //throw new RuntimeException(ex);
            throw new DAOException(ex.getMessage(), ex);
        }
    }

    @Override
    public Customer getCustomer(String username) {
        String sql = "select * from Customer where Customer_UserName = ?";

        try (
                // get connection to database
                Connection connection = JdbcConnection.getConnection(url);

                // create the statement
                PreparedStatement stmt = connection.prepareStatement(sql);
        ) {
            // set the parameter
            stmt.setString(1, username);

            // execute the query
            ResultSet rs = stmt.executeQuery();

            // query only returns a single result, so use 'if' instead of 'while'
            if (rs.next()) {
                Integer cid = rs.getInt("Customer_ID");
                String userName = rs.getString("Customer_UserName");
                String firstName = rs.getString("First_Name");
                String lastName = rs.getString("Last_Name");
                String email = rs.getString("Email");
                String address = rs.getString("Address");
                String creditCard = rs.getString("Credit_Card_Details");
                String password = rs.getString("Password");

                return new Customer(cid, userName, firstName, lastName, email, address, creditCard ,password);

            } else {
                // no product matches given ID so return null
                return null;
            }

        } catch (SQLException ex) {  // we are forced to catch SQLException
            // don't let the SQLException leak from our DAO encapsulation
            //throw new RuntimeException(ex);
            throw new DAOException(ex.getMessage(), ex);
        }
    }

    @Override
    public Boolean validateCredentials(String username, String password) {
        String sql = "select * from Customer where Customer_UserName = ? and Password = ?";
        try (
                // get connection to database
                Connection connection = JdbcConnection.getConnection(url);

                // create the statement
                PreparedStatement stmt = connection.prepareStatement(sql);
        ) {
            // set the parameter
            stmt.setString(1, username);
            stmt.setString(2, password);

            // execute the query
            ResultSet rs = stmt.executeQuery();

            // query only returns a single result, so use 'if' instead of 'while'
            if (rs.next()) {
                return true;
            } else {
                // no product matches given ID so return null
                return false;
            }

        } catch (SQLException ex) {  // we are forced to catch SQLException
            // don't let the SQLException leak from our DAO encapsulation
            //throw new RuntimeException(ex);
            throw new DAOException(ex.getMessage(), ex);
        }
    }
}
