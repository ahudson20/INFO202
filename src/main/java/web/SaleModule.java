package web;

import dao.SaleJdbcDAO;
import domain.Customer;
import domain.Sale;
import java.util.concurrent.CompletableFuture;
import java.util.logging.Level;
import java.util.logging.Logger;
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
            
            CompletableFuture.runAsync(() -> {
            try {
                Email email = new SimpleEmail();
                email.setHostName("localhost");
                email.setSmtpPort(2525);
                email.setFrom("user@gmail.com");
                email.setSubject("TestMail");
                email.setMsg("This is a test mail ... :-)");
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
