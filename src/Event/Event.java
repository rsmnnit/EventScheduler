/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Event;

import com.sun.rowset.CachedRowSetImpl;
import java.awt.BorderLayout;
import java.awt.FileDialog;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListCellRenderer;
import javax.swing.DefaultListModel;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.event.ListDataListener;

/**
 *
 * @author radhe
 */
public class Event extends javax.swing.JPanel {

    public DbHandler db;
    public Connection con;
    public PreparedStatement ps;
    public int state;
    DefaultListModel newnoti;
    JList eventList;
    JScrollPane listscroll;
    JLabel intro;
    ClientConnect cc;
    private CachedRowSetImpl crs;
    String fileattached="";
    String filename="",types;
    DefaultComboBoxModel ven;
    
    /**
     * Creates new form Event
     */
    public Event(final ClientConnect cc) throws SQLException {
        initComponents();
        state = 0;
        this.cc = cc;
        
        
        
        
        ven = new DefaultComboBoxModel();
        
        
        
        
        
        
        
        
        
        
        
        db = new DbHandler();
        con = db.retcon();
        date.getEditor().setEditable(false);
        date.setDate(new Date());
        intro = new JLabel();
        newnoti = new DefaultListModel();
        
        eventList = new JList(newnoti);
        eventList.setCellRenderer(new DefaultListCellRenderer());
        eventList.setVisibleRowCount(4);
        
        listscroll = new JScrollPane(eventList);
        //listscroll.setSize(200, 200);
        listscroll.setBounds(125, 125, 600, 300);
        
        
        intro.setBounds(220, 150, 500, 200);
        intro.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/creater.png")));
        this.add(intro, BorderLayout.CENTER);
        intro.setVisible(true);
        eventForm.setVisible(false);
        
        
        
      
        
          eventList.addMouseListener(new MouseAdapter() {
      @Override
      public void mouseClicked(MouseEvent mouseEvent) {
        JList theList = (JList) mouseEvent.getSource();
        if (mouseEvent.getClickCount() == 2) {
          int index = theList.locationToIndex(mouseEvent.getPoint());
          int hindex = eventList.getSelectedIndex();
          
          String str[] = eventList.getModel().getElementAt(hindex).toString().split(" ");
          int id = Integer.parseInt(str[2]);
            types = str[4];
          
          if (index >= 0) {
            Object o = theList.getModel().getElementAt(index);
            JFrame frame = new JFrame("Invite");
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
 
        //Create and set up the content pane.
                Invite ev = new Invite(frame, id, cc, types);
                ev.setOpaque(true); //content panes must be opaque
                frame.setContentPane(ev);
 
        //Display the window.
                frame.pack();
               // frame.setVisible(true);
                //JOptionPane.showOptionDialog(frame, "", "", 0, 0,null, null, null);
                JDialog dialog = new JDialog(frame, "Invite");
                dialog.setModal(true);
                
                dialog.setContentPane(ev);
                dialog.pack();
                dialog.setLocationRelativeTo(null);
                dialog.setVisible(true);
            //JDialog.setDefaultLookAndFeelDecorated(true);
            //int response = JOptionPane.showConfirmDialog(null, "Do you want to add this event?", "Confirm",
            //JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
            
            
            
            /*if (ev.inviteClick == true || ev.deleteClick == true) {
                String q = "update notification set seen=1 where noti_user='"+cc.uname+"' and event_id="+id+";";
                
                cc.getQuerry(q,1,cc);
                try {
                    Thread.sleep(100);
                } catch (InterruptedException ex) {
                    Logger.getLogger(Searching.class.getName()).log(Level.SEVERE, null, ex);
                }
                
                
                
                if(cc.connected){
                    while(!cc.update){
                        System.out.print("h");
                    }
                    
                    cc.update = false;
                    
                    
                }
                
                
                
                System.out.println(index+"   "+hindex+"login calliing while there"+eventList.getSize());
                
            } else {
            }
          
          */
            
            
          }
        }
      }
    });
        
          
          
          
          
        //this.populate();
    }
    
    
    
    public void listvenue(){
    
             
        String q = "select distinct(venue) from allocations;";
                
                cc.getQuerry(q,2,cc);
                try {
                    Thread.sleep(100);
                } catch (InterruptedException ex) {
                    Logger.getLogger(Searching.class.getName()).log(Level.SEVERE, null, ex);
                }
                
                
                
                if(cc.connected){
                    while(!cc.resultOk){
                        System.out.print("h");
                    }
                    
                    crs = cc.sendResult();
                    ven.removeAllElements();
                    ven.addElement("Event Venue?");
            try {
                while(crs.next()){
                    ven.addElement(crs.getString(1));
                    System.out.println(crs.toString());
                }
            } catch (SQLException ex) {
                Logger.getLogger(Event.class.getName()).log(Level.SEVERE, null, ex);
            }
                
                    
                }
        
          venue.setModel(ven);
          this.repaint();
        
    }
    
    
    
    public void fromList(boolean val){
        
        System.out.println("hey i am printing");
        venue.setEnabled(val);
        date.setEnabled(val);
        slot.setEnabled(val);
        
        //block.setEnabled(val);
        //edit.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/view.png")));
        
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    
    public void populate(){
        
        String qur = "insert into event values(default,'"+title.getText()+"','"+type.getSelectedItem().toString()+"','"+new java.sql.Date(date.getDate().getTime())+"',"+slot.getSelectedIndex()+",'"+venue.getSelectedItem().toString()+"','"+description.getText()+"','"+file.getText()+"','"+block.getSelectedItem().toString()+"','"+cc.uname+"');";
        
        if(file.getText().equals("upload file of max 250KB.")){
            filename="empty";
            fileattached="";
        }
        
        
        if(type.getSelectedItem().toString().equals("Public"))
            cc.getQuerry(qur,5,cc,title.getText(),filename, fileattached, type.getSelectedItem().toString());
        else
            cc.getQuerry(qur,6,cc,filename, fileattached);
        
        
        
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
            
            try {
                
                JDialog.setDefaultLookAndFeelDecorated(true);
                int response = JOptionPane.showConfirmDialog(null, "event created.", "Confirm",
                        JOptionPane.OK_OPTION, JOptionPane.QUESTION_MESSAGE);
                if (response == JOptionPane.OK_OPTION ){
                    clear();
                }
            } catch(Exception e){}
            
        }
       
        
        
    }
    
    
    
    
    
    
    public void clear(){
        
        title.setText("");
        description.setText("");
        type.setSelectedIndex(0);
        date.setDate(new Date());
        slot.setSelectedIndex(0);
        venue.setSelectedIndex(0);
        block.setSelectedIndex(0);
        file.setText("Upload a file related to your event is exists.");
        
    }
    
    
    public void loadList(){
    
        String qur = "select event_id, type, title from event where uname='"+cc.uname+"';";

        try {
            //ps = con.prepareStatement(qur);
            //ps.setString(1,"jackjay");
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
                        System.out.println(crs.getString(1)+crs.getString(2)+crs.getString(3));
                        if(!newnoti.contains("event id: "+crs.getString("event_id")+" type: "+crs.getString("type")+" user: "+crs.getString("title"))){
                            newnoti.addElement("event id: "+crs.getString("event_id")+" type: "+crs.getString("type")+" user: "+crs.getString("title"));
                        }
                    }
                    
                    
                }
            
            
        } catch (SQLException ex) {
            Logger.getLogger(Searching.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("login calliing while there");
        }
    
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        eventForm = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        title = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        upload = new javax.swing.JButton();
        type = new javax.swing.JComboBox<String>();
        venue = new javax.swing.JComboBox<String>();
        slot = new javax.swing.JComboBox<String>();
        jLabel18 = new javax.swing.JLabel();
        date = new org.jdesktop.swingx.JXDatePicker();
        jLabel5 = new javax.swing.JLabel();
        description = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        file = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        block = new javax.swing.JComboBox<String>();
        new_create = new javax.swing.JLabel();
        edit = new javax.swing.JLabel();

        setBackground(new java.awt.Color(101, 207, 165));
        setForeground(new java.awt.Color(255, 255, 255));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/event.png"))); // NOI18N

        eventForm.setBackground(new java.awt.Color(68, 189, 141));
        eventForm.setPreferredSize(new java.awt.Dimension(400, 250));

        jLabel4.setFont(new java.awt.Font("Trebuchet MS", 2, 14)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel4.setText("Title");

        title.setBackground(java.awt.Color.lightGray);
        title.setFont(new java.awt.Font("Trebuchet MS", 0, 12)); // NOI18N
        title.setForeground(new java.awt.Color(102, 83, 23));
        title.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                titleActionPerformed(evt);
            }
        });

        jLabel10.setFont(new java.awt.Font("Trebuchet MS", 2, 14)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(255, 255, 255));
        jLabel10.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel10.setText("Time Slot");

        jLabel11.setFont(new java.awt.Font("Trebuchet MS", 2, 14)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(255, 255, 255));
        jLabel11.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel11.setText("Venue");

        jLabel12.setFont(new java.awt.Font("Trebuchet MS", 2, 14)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(255, 255, 255));
        jLabel12.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel12.setText("Type");

        upload.setBackground(java.awt.Color.lightGray);
        upload.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        upload.setForeground(new java.awt.Color(102, 83, 23));
        upload.setText("File");
        upload.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                uploadMouseClicked(evt);
            }
        });
        upload.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                uploadActionPerformed(evt);
            }
        });

        type.setBackground(java.awt.Color.lightGray);
        type.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        type.setForeground(new java.awt.Color(102, 83, 23));
        type.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Event Type?", "Public", "Limited" }));

        venue.setBackground(java.awt.Color.lightGray);
        venue.setFont(new java.awt.Font("Trebuchet MS", 0, 12)); // NOI18N
        venue.setForeground(new java.awt.Color(102, 83, 23));
        venue.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Event Venue?" }));

        slot.setBackground(java.awt.Color.lightGray);
        slot.setFont(new java.awt.Font("Trebuchet MS", 0, 12)); // NOI18N
        slot.setForeground(new java.awt.Color(102, 83, 23));
        slot.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Event Time?", "Free Time", "08:00 - 10:00", "10:00 - 12:00", "02:00 - 04:00", "04:00 - 06:00", "06:00 - 08:00" }));

        jLabel18.setFont(new java.awt.Font("Trebuchet MS", 2, 14)); // NOI18N
        jLabel18.setForeground(new java.awt.Color(255, 255, 255));
        jLabel18.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel18.setText("Date");

        date.setBackground(java.awt.Color.lightGray);
        date.setForeground(new java.awt.Color(102, 83, 23));

        jLabel5.setFont(new java.awt.Font("Trebuchet MS", 2, 14)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel5.setText("Description");

        description.setBackground(java.awt.Color.lightGray);
        description.setFont(new java.awt.Font("Trebuchet MS", 0, 12)); // NOI18N
        description.setForeground(new java.awt.Color(102, 83, 23));

        jLabel13.setFont(new java.awt.Font("Trebuchet MS", 2, 14)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(255, 255, 255));
        jLabel13.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel13.setText("About");

        file.setFont(new java.awt.Font("Trebuchet MS", 2, 14)); // NOI18N
        file.setForeground(new java.awt.Color(255, 255, 255));
        file.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        file.setText("upload file of max 250KB.");

        jLabel15.setFont(new java.awt.Font("Trebuchet MS", 2, 14)); // NOI18N
        jLabel15.setForeground(new java.awt.Color(255, 255, 255));
        jLabel15.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel15.setText("Block");

        block.setBackground(java.awt.Color.lightGray);
        block.setFont(new java.awt.Font("Trebuchet MS", 0, 12)); // NOI18N
        block.setForeground(new java.awt.Color(102, 83, 23));
        block.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Block People?", "Coordinator", "Member", "Student", "Outsider" }));

        javax.swing.GroupLayout eventFormLayout = new javax.swing.GroupLayout(eventForm);
        eventForm.setLayout(eventFormLayout);
        eventFormLayout.setHorizontalGroup(
            eventFormLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(eventFormLayout.createSequentialGroup()
                .addGap(73, 73, 73)
                .addGroup(eventFormLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(eventFormLayout.createSequentialGroup()
                        .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(description))
                    .addGroup(eventFormLayout.createSequentialGroup()
                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(title, javax.swing.GroupLayout.PREFERRED_SIZE, 536, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, eventFormLayout.createSequentialGroup()
                .addGap(111, 111, 111)
                .addGroup(eventFormLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(eventFormLayout.createSequentialGroup()
                        .addComponent(jLabel12)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(type, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel18)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(date, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel10)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(slot, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, eventFormLayout.createSequentialGroup()
                        .addComponent(jLabel13)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 18, Short.MAX_VALUE)
                        .addComponent(file, javax.swing.GroupLayout.PREFERRED_SIZE, 389, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(upload, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, eventFormLayout.createSequentialGroup()
                        .addComponent(jLabel11)
                        .addGap(16, 16, 16)
                        .addComponent(venue, javax.swing.GroupLayout.PREFERRED_SIZE, 223, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel15)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(block, javax.swing.GroupLayout.PREFERRED_SIZE, 229, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(98, 98, 98))
        );
        eventFormLayout.setVerticalGroup(
            eventFormLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(eventFormLayout.createSequentialGroup()
                .addGap(60, 60, 60)
                .addGroup(eventFormLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(title, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(eventFormLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel5)
                    .addComponent(description, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(eventFormLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel12)
                    .addComponent(type, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel18)
                    .addComponent(date, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel10)
                    .addComponent(slot, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(16, 16, 16)
                .addGroup(eventFormLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel11)
                    .addComponent(venue, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel15)
                    .addComponent(block, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(eventFormLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel13)
                    .addComponent(upload)
                    .addComponent(file, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(20, Short.MAX_VALUE))
        );

        new_create.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/newevent.png"))); // NOI18N
        new_create.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        new_create.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                new_createMouseClicked(evt);
            }
        });

        edit.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/view.png"))); // NOI18N
        edit.setText("\n");
        edit.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        edit.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                editMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 206, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(new_create, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(edit, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
            .addComponent(eventForm, javax.swing.GroupLayout.DEFAULT_SIZE, 800, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(edit, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(34, 34, 34)
                        .addComponent(new_create, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(eventForm, javax.swing.GroupLayout.DEFAULT_SIZE, 350, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void titleActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_titleActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_titleActionPerformed

    private void uploadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_uploadActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_uploadActionPerformed

    
    
    
    
    private void new_createMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_new_createMouseClicked
        // TODO add your handling code here:
         System.out.println(state+" jayant");
        if(state == 1){
           
           JDialog.setDefaultLookAndFeelDecorated(true);
           int response = JOptionPane.showConfirmDialog(null, "Do you want to create this event?", "Confirm",
           JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
           
           if (response == JOptionPane.OK_OPTION ){
               state=0;
           edit.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/view.png")));
           new_create.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/newevent.png")));
           
           populate();
           
           }
           
           //DisableElement de = new DisableElement(settiingForm);
           //de.enableComponents(settiingForm, true);
           
       }
        else if(state == 0){
           
           System.out.println("above edit");
           edit.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/cancel.png")));
           new_create.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/create.png")));
           System.out.println("below edit");
           if(listscroll.isVisible())
               listscroll.setVisible(false);
           intro.setVisible(false);
           fromList(true);
           eventForm.setVisible(true);
           clear();
           state = 1;
           
           
           //JDialog.setDefaultLookAndFeelDecorated(true);
            //JOptionPane.showConfirmDialog(null, "Your changes are saved !", "Status", JOptionPane.PLAIN_MESSAGE, JOptionPane.INFORMATION_MESSAGE);
            
           
           //DisableElement de = new DisableElement(settiingForm);
          // de.enableComponents(settiingForm, false);
       }
    }//GEN-LAST:event_new_createMouseClicked
    
    
    
    private void editMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_editMouseClicked
        // TODO add your handling code here:
        System.out.println(state+" jayant");
        switch (state) {
            case 1:
                edit.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/view.png")));
                new_create.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/newevent.png")));
                state=0;
                clear();
                fromList(true);
                intro.setVisible(true);
                eventForm.setVisible(false);
                
                //DisableElement de = new DisableElement(settiingForm);
                //de.enableComponents(settiingForm, true);
                break;
            case 0:
                //new_create.setVisible(false);
                edit.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/cancel.png")));
                //DisableElement de = new DisableElement(settiingForm);
                //de.enableComponents(settiingForm, false);
                new_create.setVisible(false);
                eventForm.setVisible(false);
                loadList();
                this.add(listscroll, BorderLayout.CENTER);
                intro.setVisible(false);
                listscroll.setVisible(true);
                state=2;
                break;
            case 2:
                edit.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/view.png")));
                new_create.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/newevent.png")));
                state=0;
                listscroll.setVisible(false);
                clear();
                new_create.setVisible(true);
                //eventForm.setVisible(true);
                intro.setVisible(true);

                break;
            default:
                break;
        }
        
    }//GEN-LAST:event_editMouseClicked

    private void uploadMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_uploadMouseClicked
        // TODO add your handling code here:
     System.out.println("here i am ");
        FileDialog filedialog= new FileDialog((java.awt.Frame) null);
        filedialog.setFilenameFilter(new FilenameFilter() {
            public boolean accept(File dir, String name) {
                return name.endsWith(".pdf") || name.endsWith(".txt");
            }
        });
        filedialog.setVisible(true);
        String dir=filedialog.getDirectory();
        String files= dir+"/"+filedialog.getFile();
        System.out.println("here "+files);
        File f = new File(files);
        System.out.println("file size is in KB "+f.length()/1024+" file name is "+files);
        
        
        if(f.length()>0 && f.length()/1024<=250){
            
        filename = filedialog.getFile();
        try {
            fileattached = new String(Files.readAllBytes(Paths.get(files)));
        } catch (IOException ex) {
            Logger.getLogger(Event.class.getName()).log(Level.SEVERE, null, ex);
        }
        file.setText(filename);
        }
        else{
           JDialog.setDefaultLookAndFeelDecorated(true);
           int response = JOptionPane.showConfirmDialog(null, "File exceeds maximum limit(250KB)... Try again with new file.", "Confirm",
           JOptionPane.OK_OPTION, JOptionPane.QUESTION_MESSAGE);
        }
        
        
        
    }//GEN-LAST:event_uploadMouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.JComboBox<String> block;
    public org.jdesktop.swingx.JXDatePicker date;
    public javax.swing.JTextField description;
    public javax.swing.JLabel edit;
    public javax.swing.JPanel eventForm;
    public javax.swing.JLabel file;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    public javax.swing.JLabel new_create;
    public javax.swing.JComboBox<String> slot;
    public javax.swing.JTextField title;
    public javax.swing.JComboBox<String> type;
    public javax.swing.JButton upload;
    public javax.swing.JComboBox<String> venue;
    // End of variables declaration//GEN-END:variables
}
