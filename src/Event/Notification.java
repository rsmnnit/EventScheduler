/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Event;

import com.sun.rowset.CachedRowSetImpl;
import java.awt.BorderLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;

/**
 *
 * @author radhe
 */
public class Notification extends javax.swing.JPanel {

    /**
     * Creates new form Notification
     */
    DefaultListModel newnoti, all;
    JList eventList, alleventlist;
    JScrollPane listscroll, alllistscroll;
    DbHandler db;
    Connection con;
    PreparedStatement ps;
    ClientConnect cc;
    CachedRowSetImpl crs;
    
    
    public Notification(final ClientConnect cc) {
        initComponents();
        this.cc = cc;
        
        
        newnoti = new DefaultListModel();
        all = new DefaultListModel();
        
        eventList = new JList(newnoti);
        eventList.setCellRenderer(new DefaultListCellRenderer());
        eventList.setVisibleRowCount(4);
        
        listscroll = new JScrollPane(eventList);
        //listscroll.setSize(200, 200);
        listscroll.setBounds(125, 125, 600, 300);
        
        alleventlist = new JList(all);
        alleventlist.setCellRenderer(new DefaultListCellRenderer());
        alleventlist.setVisibleRowCount(4);
        
        alllistscroll = new JScrollPane(alleventlist);
        //alllistscroll.setSize(200, 200);
        alllistscroll.setBounds(125, 125, 600, 300);
        
       
        
        
       
        eventList.addMouseListener(new MouseAdapter() {
      @Override
      public void mouseClicked(MouseEvent mouseEvent) {
        JList theList = (JList) mouseEvent.getSource();
        if (mouseEvent.getClickCount() == 2) {
          int index = theList.locationToIndex(mouseEvent.getPoint());
          
          int hindex = eventList.getSelectedIndex();
          String arr = eventList.getModel().getElementAt(hindex).toString();
          String str[] = arr.split(" ");
          System.out.println(arr.split(" ")[0]);//+"   "+str[0].split("$")+"  ");
          int id = Integer.parseInt(str[0]);
          
          if (index >= 0) {
            Object o = theList.getModel().getElementAt(index);
            JFrame frame = new JFrame("DialogDemo");
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
 
        //Create and set up the content pane.
        
                EventView ev = new EventView(frame,id,cc);
                
                ev.setOpaque(true); //content panes must be opaque
                frame.setContentPane(ev);
 
        //Display the window.
                frame.pack();
               // frame.setVisible(true);
                //JOptionPane.showOptionDialog(frame, "", "", 0, 0,null, null, null);
                JDialog dialog = new JDialog(frame, "Event Viewer");
                dialog.setModal(true);
                
                dialog.setContentPane(ev);
                dialog.pack();
                dialog.setLocationRelativeTo(null);
                dialog.setVisible(true);
                frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
            //JDialog.setDefaultLookAndFeelDecorated(true);
            //int response = JOptionPane.showConfirmDialog(null, "Do you want to add this event?", "Confirm",
            //JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
            if (ev.acceptClick == true || ev.declineClick == true ) {
                String q = "";

                if(ev.acceptClick==true)
                    q = "update notification set seen=2 where noti_user='"+cc.uname+"' and event_id="+id+";";
                else
                    q = "update notification set seen=1 where noti_user='"+cc.uname+"' and event_id="+id+";";
                
                
                
                
                cc.getQuerry(q,1,cc);
                try {
                    Thread.sleep(100);
                } catch (InterruptedException ex) {
                    Logger.getLogger(Searching.class.getName()).log(Level.SEVERE, null, ex);
                }
                if(cc.connected){
                    while(!cc.update){
                        System.out.print("");
                    }
                    
                    cc.update = false;
                    
                }
                
                
                
                
                System.out.println(index+"   "+hindex+"login calliing while there"+eventList.getSize());
                newnoti.remove(hindex);
            } else {
            }
            
            
          }
        }
      }
    });
        
        
        
        
        alleventlist.addMouseListener(new MouseAdapter() {
      @Override
      public void mouseClicked(MouseEvent mouseEvent) {
        JList theList = (JList) mouseEvent.getSource();
        if (mouseEvent.getClickCount() == 2) {
          int index = theList.locationToIndex(mouseEvent.getPoint());
          int hindex = alleventlist.getSelectedIndex();
          String str[] = eventList.getModel().getElementAt(hindex).toString().split(" ");
          int id = Integer.parseInt(str[0]);
          if (index >= 0) {
            Object o = theList.getModel().getElementAt(index);
            
            JDialog.setDefaultLookAndFeelDecorated(true);
            int response = JOptionPane.showConfirmDialog(null, "Do you want to delete this event?", "Confirm",
            JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
            if (response == JOptionPane.OK_OPTION) {
                String q = "delete from notification where noti_user='jackjay' and event_id="+id+";";
                
                cc.getQuerry(q,1,cc);
                try {
                    Thread.sleep(100);
                } catch (InterruptedException ex) {
                    Logger.getLogger(Searching.class.getName()).log(Level.SEVERE, null, ex);
                }
                if(cc.connected){
                    while(!cc.update){
                        System.out.print("");
                    }
                    
                    cc.update = false;
                    
                    
                }
                
                System.out.println(index+"   "+hindex+"login calliing while there"+eventList.getSize());
                all.remove(hindex);
            } else {
            }
            
            
          }
        }
      }
    });
        
        
        
        
        
        
        
        
        
        
        
            //System.out.println("login calliing"+m.uName.getText()+" "+m.pass.getText());
            db = new DbHandler();
            con = db.retcon();
            System.out.println("login calliing");
            //Querry q = new Querry(con);
            //rs = q.retrieve(qur);
        
        
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        allNoti = new javax.swing.JLabel();
        newNoti = new javax.swing.JLabel();

        setBackground(new java.awt.Color(101, 207, 165));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/notification.png"))); // NOI18N

        allNoti.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/all.png"))); // NOI18N
        allNoti.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        allNoti.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                allNotiMouseClicked(evt);
            }
        });

        newNoti.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/new.png"))); // NOI18N
        newNoti.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        newNoti.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                newNotiMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 256, Short.MAX_VALUE)
                .addComponent(newNoti)
                .addGap(18, 18, 18)
                .addComponent(allNoti)
                .addGap(26, 26, 26))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(27, 27, 27)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(newNoti, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(allNoti, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(19, 19, 19)
                        .addComponent(jLabel1)))
                .addContainerGap(366, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void newNotiMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_newNotiMouseClicked
        // TODO add your handling code here:
        
        newnoti.clear();
        
        
        String qur = "select event.event_id, event.title from notification,event where notification.event_id=event.event_id and  notification.noti_user='"+cc.uname+"' and notification.seen=0;";

        try {
            //ps = con.prepareStatement(qur);
            //ps.setInt(1,0);
            //ps.setString(2,m.pass.getText());
            //System.out.println("login calliing");
            //ResultSet rs = (ResultSet) ps.executeQuery();
            //System.out.println("login calliing result");
            
            cc.getQuerry(qur,2,cc);
            
                try {
                    Thread.sleep(100);
                } catch (InterruptedException ex) {
                    Logger.getLogger(Searching.class.getName()).log(Level.SEVERE, null, ex);
                }
            
            
            if(cc.connected){
                while(!cc.resultOk){
                System.out.print("");
                }
                
                crs = cc.sendResult();
                
                
                 while(crs.next()){
                //m.login.setText("Logged in");
                //  System.out.println("login calliing while");
                try {
                    //    System.out.println("login callin bkcvjDkSing"+" "+rs.getString("noti_user"));
                    if(!newnoti.contains(crs.getString("event_id")+" "+crs.getString("title"))){
                        newnoti.addElement(crs.getString("event_id")+" "+crs.getString("title"));
                    }

                } catch (SQLException ex) {
                    Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
                    System.out.println("login calliing while here");
                }
            }
                
            
            }
            
    
        } catch (SQLException ex) {
            Logger.getLogger(Searching.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("login calliing while there");
        }

        if(alleventlist.isVisible()){

            System.out.println("removing");
            this.remove(alllistscroll);
            this.repaint();
        }

        this.add(listscroll, BorderLayout.CENTER);
        System.out.println("added");
        //this.repaint();
        //this.setVisible(true);
        
        
    }//GEN-LAST:event_newNotiMouseClicked

    
    
    
    
    
    
    
    private void allNotiMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_allNotiMouseClicked
        // TODO add your handling code here:
        all.clear();
        
        
        String qur = "select event.event_id, event.title from notification,event where notification.event_id=event.event_id and  notification.noti_user='"+cc.uname+"';";

        try {
            //ps = con.prepareStatement(qur);
            //ps.setString(1,m.uName.getText());
            //ps.setString(2,m.pass.getText());
            //System.out.println("login calliing");
            //ResultSet rs = (ResultSet) ps.executeQuery();
            //System.out.println("login calliing result");
            
            
            
            cc.getQuerry(qur,2,cc);
            
                try {
                    Thread.sleep(100);
                } catch (InterruptedException ex) {
                    Logger.getLogger(Searching.class.getName()).log(Level.SEVERE, null, ex);
                }
            
            
            if(cc.connected){
                while(!cc.resultOk){
                System.out.print("");
                }
                
                crs = cc.sendResult();
                
                 while(crs.next()){
                //m.login.setText("Logged in");
                //System.out.println("login calliing while");
                try {
                    //System.out.println("login callin bkcvjDkSing"+" "+rs.getString("noti_user"));
                    if(!all.contains(crs.getString("event_id")+" "+crs.getString("title"))){
                        all.addElement(crs.getString("event_id")+" "+crs.getString("title"));
                    }

                } catch (SQLException ex) {
                    Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
                    System.out.println("login calliing while here");
                }
            }
                
                
                
            }
           
            
           
        } catch (SQLException ex) {
            Logger.getLogger(Searching.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("login calliing while there");
        }

        if(eventList.isVisible()){
            System.out.println("removing");
            this.remove(listscroll);
            this.repaint();
        }
        this.add(alllistscroll, BorderLayout.CENTER);
        System.out.println("added");
        // this.repaint();
        //this.setVisible(true);
        
    }//GEN-LAST:event_allNotiMouseClicked
    
    int i=0;
    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel allNoti;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel newNoti;
    // End of variables declaration//GEN-END:variables
}
