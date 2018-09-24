package web;

import domain.Customer;
import domain.Sale;
import org.jooby.Jooby;
import org.jooby.Status;

public class SaleModule extends Jooby {
    public SaleModule(/*TODO: amazing*/){
        post("/api/sales", (req, rsp) -> {
            Sale sale = req.body().to(Sale.class);
            System.out.println(sale);
//            custDao.save(customer);
            rsp.status(Status.CREATED);
        });
    }
}
