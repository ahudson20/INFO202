package web;

import dao.SaleJdbcDAO;
import domain.Customer;
import domain.Sale;
import org.jooby.Jooby;
import org.jooby.Status;

public class SaleModule extends Jooby {
    public SaleModule(SaleJdbcDAO saleDao){
        post("/api/sales", (req, rsp) -> {
            Sale sale = req.body().to(Sale.class);
            System.out.println(sale);
            //saleDao.save(sale);
            rsp.status(Status.CREATED);
        });
    }
}
