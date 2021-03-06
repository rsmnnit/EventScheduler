/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Event;

import com.sun.rowset.CachedRowSetImpl;
import java.awt.BorderLayout;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 *
 * @author Jayant Kumar Yadav
 */
public class EventView extends javax.swing.JPanel {

    
    public JFrame frame;
    public DbHandler db;
    public Connection con;
    public PreparedStatement ps;
    public boolean acceptClick;
    public boolean declineClick;
    ClientConnect cc;
    private CachedRowSetImpl crs;
    
    /**
     * Creates new form EventView
     * @param frame
     */
    public EventView(JFrame frame, int evid, ClientConnect cc) {
        super(new BorderLayout());
        initComponents();
        this.cc = cc;
        this.frame = frame;
        acceptClick = false;
        declineClick = true;
        
        
        
        String qur = "select * from event where event_id="+evid+";";
        
        try {
            //ps = con.prepareStatement(qur);
            //ps.setInt(1,evid);
            //ps.setString(2,m.pass.getText());
            //System.out.println("login calliing");
            //ResultSet rs = (ResultSet) ps.executeQuery();
            //System.out.println("login calliing result");
            
            
            cc.getQuerry(qur,3,cc);
            
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
                    
            if(crs.next()){
                //m.login.setText("Logged in");
              //  System.out.println("login calliing while");
                try {
                //    System.out.println("login callin bkcvjDkSing"+" "+rs.getString("noti_user"));
                   //propic.setIcon(new ImageIcon(rs.getString("propic")));
                   uname.setText(crs.getString("uname"));
                   title.setText(crs.getString("title"));
                   type.setText(crs.getString("type"));
                   date.setText(crs.getString("date"));
                   time.setText(crs.getString("time"));
                   venue.setText(crs.getString("venue"));
                   description.setText(crs.getString("description"));
                   if(crs.getString("file").equals("empty")){
                       file.setText("No file is present.");
                   }
                   else{
                       file.setText(crs.getString("file"));
                       JDialog.setDefaultLookAndFeelDecorated(true);
                        int response = JOptionPane.showConfirmDialog(null, "File is downloaded in Downloads !!", "Confirm",
                        JOptionPane.OK_OPTION, JOptionPane.QUESTION_MESSAGE);
                   }
                   
                   
                } catch (SQLException ex) {
                    Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
                    System.out.println("login calliing while here");
                }   
            }
            else{
                description.setText("Entry not present in database.");
            }
                   
                    
                    
                    
                    
                }
             
                
                
                
        } catch (SQLException ex) {
            Logger.getLogger(Searching.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("login calliing while there");
        }
        
    }
    
    
    
    
    
    
    
   
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        type = new javax.swing.JLabel();
        date = new javax.swing.JLabel();
        time = new javax.swing.JLabel();
        venue = new javax.swing.JLabel();
        propic = new javax.swing.JLabel();
        uname = new javax.swing.JLabel();
        description = new javax.swing.JLabel();
        file = new javax.swing.JLabel();
        accept = new javax.swing.JButton();
        decline = new javax.swing.JButton();
        title = new javax.swing.JLabel();

        setBackground(new java.awt.Color(69, 149, 104));

        type.setFont(new java.awt.Font("Verdana", 0, 12)); // NOI18N
        type.setForeground(new java.awt.Color(255, 255, 255));
        type.setText("type");

        date.setFont(new java.awt.Font("Verdana", 0, 12)); // NOI18N
        date.setForeground(new java.awt.Color(255, 255, 255));
        date.setText("date");

        time.setFont(new java.awt.Font("Verdana", 0, 12)); // NOI18N
        time.setForeground(new java.awt.Color(255, 255, 255));
        time.setText("time");

        venue.setFont(new java.awt.Font("Verdana", 0, 12)); // NOI18N
        venue.setForeground(new java.awt.Color(255, 255, 255));
        venue.setText("Venue");

        propic.setFont(new java.awt.Font("Verdana", 0, 12)); // NOI18N
        propic.setForeground(new java.awt.Color(255, 255, 255));
        propic.setText("image");

        uname.setFont(new java.awt.Font("Verdana", 0, 12)); // NOI18N
        uname.setForeground(new java.awt.Color(255, 255, 255));
        uname.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        uname.setText("name");

        description.setFont(new java.awt.Font("Verdana", 0, 12)); // NOI18N
        description.setForeground(new java.awt.Color(255, 255, 255));
        description.setText("Description");

        file.setFont(new java.awt.Font("Verdana", 0, 12)); // NOI18N
        file.setForeground(new java.awt.Color(255, 255, 255));
        file.setText("file");

        accept.setText("ACCEPT");
        accept.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                acceptMouseClicked(evt);
            }
        });

        decline.setText("DECLINE");
        decline.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                declineActionPerformed(evt);
            }
        });

        title.setFont(new java.awt.Font("Verdana", 0, 12)); // NOI18N
        title.setForeground(new java.awt.Color(255, 255, 255));
        title.setText("title");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(accept, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(57, 57, 57)
                .addComponent(decline, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(108, 108, 108))
            .addGroup(layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(file, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                            .addGap(9, 9, 9)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(uname, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(18, 18, 18))
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(propic, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(26, 26, 26)))
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(title, javax.swing.GroupLayout.PREFERRED_SIZE, 267, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(date, javax.swing.GroupLayout.PREFERRED_SIZE, 163, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(type, javax.swing.GroupLayout.PREFERRED_SIZE, 163, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(time, javax.swing.GroupLayout.PREFERRED_SIZE, 172, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGroup(layout.createSequentialGroup()
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 1, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(venue, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                    .addComponent(description, javax.swing.GroupLayout.PREFERRED_SIZE, 392, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(propic, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(uname, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(title, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(type)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(date)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(time)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(venue)))
                .addGap(18, 18, 18)
                .addComponent(description, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(file, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(accept, javax.swing.GroupLayout.DEFAULT_SIZE, 31, Short.MAX_VALUE)
                    .addComponent(decline, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(22, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void declineActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_declineActionPerformed
        // TODO add your handling code here:
        
        declineClick = true;
        frame.dispose();
    }//GEN-LAST:event_declineActionPerformed

    private void acceptMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_acceptMouseClicked
        // TODO add your handling code here:
        
        acceptClick = true;
        frame.dispose();
        
    }//GEN-LAST:event_acceptMouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton accept;
    private javax.swing.JLabel date;
    private javax.swing.JButton decline;
    private javax.swing.JLabel description;
    private javax.swing.JLabel file;
    private javax.swing.JLabel propic;
    private javax.swing.JLabel time;
    private javax.swing.JLabel title;
    private javax.swing.JLabel type;
    private javax.swing.JLabel uname;
    private javax.swing.JLabel venue;
    // End of variables declaration//GEN-END:variables
}
