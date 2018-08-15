/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import dao.JdbcProductDao;
import domain.Product;
import gui.helpers.SimpleListModel;
import gui.helpers.ValidationHelper;
import java.awt.*;
import java.util.*;
import javax.swing.JOptionPane;

/**
 *
 * @author hudan995
 */
public class ProductViewer extends javax.swing.JDialog {

    /**
     * Create instance of ProductDAO
     */
    private JdbcProductDao productDao = new JdbcProductDao();

    private gui.helpers.SimpleListModel myModel = new gui.helpers.SimpleListModel();
    
    private ValidationHelper validation = new ValidationHelper();

    /**
     * Creates new form ProductViewer
     */
    public ProductViewer(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        Collection<Product> products = productDao.getProducts();
        myModel.updateItems(products);
        productsList.setModel(myModel);
        
        //set values in combobox
        comboFilter.setEditable(false);
        SimpleListModel newModel = new SimpleListModel();
        Collection<String> categories = productDao.getCategories();
        newModel.updateItems(categories);
        comboFilter.setModel(newModel);
        
        validation.addTypeFormatter(txtSearch, "#0", Integer.class);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        productsList = new javax.swing.JList<>();
        closeButton = new javax.swing.JButton();
        deleteButton = new javax.swing.JButton();
        buttonEdit = new javax.swing.JButton();
        labelSearch = new javax.swing.JLabel();
        labelFilter = new javax.swing.JLabel();
        buttonSearch = new javax.swing.JButton();
        comboFilter = new javax.swing.JComboBox<>();
        txtSearch = new javax.swing.JFormattedTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jScrollPane1.setViewportView(productsList);

        closeButton.setText("Close");
        closeButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                closeButtonActionPerformed(evt);
            }
        });

        deleteButton.setText("Delete");
        deleteButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deleteButtonActionPerformed(evt);
            }
        });

        buttonEdit.setText("Edit");
        buttonEdit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonEditActionPerformed(evt);
            }
        });

        labelSearch.setText("Search by ID");

        labelFilter.setText("Category Filter");

        buttonSearch.setText("Search");
        buttonSearch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonSearchActionPerformed(evt);
            }
        });

        comboFilter.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        comboFilter.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboFilterActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 400, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(buttonEdit)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(deleteButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(closeButton, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(63, 63, 63))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(labelFilter)
                    .addComponent(labelSearch))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(txtSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 182, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(buttonSearch))
                    .addComponent(comboFilter, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelSearch)
                    .addComponent(buttonSearch)
                    .addComponent(txtSearch, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 10, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelFilter)
                    .addComponent(comboFilter, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 159, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(deleteButton)
                    .addComponent(closeButton)
                    .addComponent(buttonEdit))
                .addGap(25, 25, 25))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void closeButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_closeButtonActionPerformed
        // TODO add your handling code here:
        this.dispose();
    }//GEN-LAST:event_closeButtonActionPerformed

    private void deleteButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deleteButtonActionPerformed
        // TODO add your handling code here:
        domain.Product product = (domain.Product)productsList.getSelectedValue();
        if(productsList.isSelectionEmpty()){
            JOptionPane.showMessageDialog(this, "Please select a Product before deleting!", "Warning", JOptionPane.INFORMATION_MESSAGE);
        }else{
            int result = JOptionPane.showConfirmDialog(this, "Are you sure you wish to delete this Product?");
            if (result == JOptionPane.YES_OPTION) {
                productDao.deleteProduct(product);
                Collection<domain.Product> products = productDao.getProducts();
                myModel.updateItems(products);
                productsList.setModel(myModel);
            }
        }
    }//GEN-LAST:event_deleteButtonActionPerformed

    private void buttonEditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonEditActionPerformed
        Product product = (Product)productsList.getSelectedValue();

        if(productsList.isSelectionEmpty()){
            JOptionPane.showMessageDialog(this, "Please select a Product before editing!", "Warning", JOptionPane.INFORMATION_MESSAGE);
        }else{
            ProductEditor dialog = new ProductEditor(this, true, product);

            // centre the dialog on the parent window
            dialog.setLocationRelativeTo(this);

            // make the dialog visible
            dialog.setVisible(true);

            //will only run after dialog is closed
            Collection<domain.Product> products = productDao.getProducts();
            myModel.updateItems(products);
            productsList.setModel(myModel);
        }
    }//GEN-LAST:event_buttonEditActionPerformed

    private void buttonSearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonSearchActionPerformed
        Integer id = (Integer) txtSearch.getValue();
        if(id == null){
            JOptionPane.showMessageDialog(this, "Please enter an ID to search!", "Warning", JOptionPane.WARNING_MESSAGE);
            return;
        }
        Product p = productDao.getProductById(id);
        myModel.updateItems(p);
        productsList.setModel(myModel);
        
    }//GEN-LAST:event_buttonSearchActionPerformed

    private void comboFilterActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboFilterActionPerformed
        String category = (String)comboFilter.getSelectedItem();
        Collection<Product> filteredProducts = productDao.filterByCategory(category);
        myModel.updateItems(filteredProducts);
        productsList.setModel(myModel);
    }//GEN-LAST:event_comboFilterActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(ProductViewer.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ProductViewer.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ProductViewer.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ProductViewer.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                ProductViewer dialog = new ProductViewer(new javax.swing.JFrame(), true);
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton buttonEdit;
    private javax.swing.JButton buttonSearch;
    private javax.swing.JButton closeButton;
    private javax.swing.JComboBox<String> comboFilter;
    private javax.swing.JButton deleteButton;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel labelFilter;
    private javax.swing.JLabel labelSearch;
    private javax.swing.JList<Product> productsList;
    private javax.swing.JFormattedTextField txtSearch;
    // End of variables declaration//GEN-END:variables
}
