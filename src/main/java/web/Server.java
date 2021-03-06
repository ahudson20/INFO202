/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package web;

import dao.CustomerCollectionsDAO;
import dao.JdbcCustomerDao;
import dao.JdbcProductDao;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CompletableFuture;

import dao.SaleJdbcDAO;
import org.jooby.Jooby;
import org.jooby.json.Gzon;
import web.auth.BasicHttpAuthenticator;

/**
 *
 * @author anaruhudson
 */
public class Server extends Jooby{
    private JdbcProductDao productDao = new JdbcProductDao();
    private JdbcCustomerDao custDao = new JdbcCustomerDao();
    private SaleJdbcDAO saleDao = new SaleJdbcDAO();
    
    public Server(){
        port(8080);
//        get("/api/products", () -> productDao.getProducts());
//        get("/api/products/:id", (req) -> {
//        Integer id = req.param("id").intValue();
//        return productDao.getProductById(id);
//        });
    use(new Gzon());
    use(new AssetModule());

    List<String> noAuth = Arrays.asList("/api/register");
    use(new BasicHttpAuthenticator(custDao, noAuth));

    use(new ProductModule(productDao));
    use(new CustomerModule(custDao));
    use(new SaleModule(saleDao));
    }
    
    public static void main(String[] args) throws Exception {
    System.out.println("\nStarting Server.");
    Server server = new Server();
    CompletableFuture.runAsync(() -> {
    server.start();
    });
    server.onStarted(() -> {
    System.out.println("\nPress Enter to stop the server.");
    });
    // wait for user to hit the Enter key
    System.in.read();
    System.exit(0);
    }
}
