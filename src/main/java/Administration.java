
import dao.JdbcProductDao;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author hudan995
 */
public class Administration {
    public static void main(String[] args){
        JdbcProductDao productDao = new JdbcProductDao();
        gui.MainMenu main = new gui.MainMenu(productDao);
        main.setLocationRelativeTo(null);
        main.setVisible(true);  
    }
}
