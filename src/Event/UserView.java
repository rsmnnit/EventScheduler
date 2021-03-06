/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Event;

import java.awt.BorderLayout;
import java.sql.SQLException;

/**
 *
 * @author radhe
 */
public class UserView extends javax.swing.JPanel {

    /**
     * Creates new form UserView
     */
    Notification t2;
    Searching t1;
    Setting t3;
    Event t4;
    ClientConnect cc;
    
    public UserView(ClientConnect cc) throws SQLException {
        initComponents();
        this.cc = cc;
        this.setLayout(new BorderLayout());
        
        t4 = new Event(cc);
        //t4.intro.setVisible(true);
        t1 = new Searching(tabber,t4, cc);
        t2 = new Notification(cc);
        t3 = new Setting(cc);
        
        
        tabber.addTab("Search", t1);
        tabber.addTab("Notification", t2);
        tabber.addTab("Setting", t3);
        tabber.addTab("Create Event",t4);
        
        this.add(tabber, BorderLayout.CENTER);
        
        
         
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        tabber = new javax.swing.JTabbedPane();

        setBackground(new java.awt.Color(69, 149, 104));
        setMaximumSize(new java.awt.Dimension(800, 450));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(tabber, javax.swing.GroupLayout.DEFAULT_SIZE, 800, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(tabber, javax.swing.GroupLayout.DEFAULT_SIZE, 450, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.JTabbedPane tabber;
    // End of variables declaration//GEN-END:variables
}
