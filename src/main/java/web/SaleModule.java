package web;

import dao.SaleJdbcDAO;
import domain.Customer;
import domain.Product;
import domain.Sale;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.concurrent.CompletableFuture;
import java.util.logging.Level;
import java.util.logging.Logger;

import domain.SaleItem;
import org.apache.commons.mail.Email;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.SimpleEmail;
import org.jooby.Jooby;
import org.jooby.Status;

public class SaleModule extends Jooby {
    public SaleModule(SaleJdbcDAO saleDao){
        post("/api/sales", (req, rsp) -> {
            Sale sale = req.body().to(Sale.class);
            System.out.println(sale);
            saleDao.save(sale);
            rsp.status(Status.CREATED);
            
            Customer c = sale.getCustomer();
            String customerEmail = c.getEmailAddress();

            BigDecimal total = sale.getTotal();

            Date date = sale.getDate();
            DateFormat dateFormat = new SimpleDateFormat("dd MMMM yyyy");
            String strDate = dateFormat.format(date);

            Collection<SaleItem> saleList = sale.getItems();

            String fname = c.getFirstname();
            String lname = c.getSurname();

            String test = "";
            test += "Customer: " + fname + " " + lname + "\n" + "Date: " + strDate + "\n";
            test+="------------------------------------" + "\n";
            for(SaleItem s : saleList){
                Product p = s.getProduct();
                test+= "Product: " + p.getName() + "\t" +"Quantity: " + Integer.toString(s.getQuantityPurchased()) + "\n";
            }
            test+="------------------------------------" + "\n";
            test+="Total Cost: $" + sale.getTotal().toString() + "\n";
            String finalTest = test;

            CompletableFuture.runAsync(() -> {
            try {
                Email email = new SimpleEmail();
                email.setHostName("localhost");
                email.setSmtpPort(2525);
                email.setFrom("user@gmail.com");
                email.setSubject("Order");
//                email.setMsg("Customer: " + fname + " " + lname + "\n" + "Date: " + strDate + "\n");
                email.setMsg(finalTest);
                email.addTo(customerEmail);
                //email.addTo("foo@bar.com");
                email.send();
            } catch (EmailException ex) {
                Logger.getLogger(SaleModule.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        });
    }
}
