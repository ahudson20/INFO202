/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package web;

import dao.CustomerCollectionsDAO;
import dao.JdbcCustomerDao;
import dao.JdbcProductDao;
import java.util.concurrent.CompletableFuture;
import org.jooby.Jooby;
import org.jooby.json.Gzon;

/**
 *
 * @author anaruhudson
 */
public class Server extends Jooby{
    private JdbcProductDao productDao = new JdbcProductDao();
    private JdbcCustomerDao custDao = new JdbcCustomerDao();

//    private JdbcCustomerDao custDao = new JdbcCustomerDao();
    
    public Server(){
        port(8080);
//        get("/api/products", () -> productDao.getProducts());
//        get("/api/products/:id", (req) -> {
//        Integer id = req.param("id").intValue();
//        return productDao.getProductById(id);
//        });
    use(new Gzon());
    use(new ProductModule(productDao));
    use(new CustomerModule(custDao));
    use(new SaleModule(/*TODO: saleDAO*/));
    use(new AssetModule());
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
