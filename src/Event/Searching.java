/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Event;

import static Event.FrontPage.front;
import com.sun.rowset.CachedRowSetImpl;
import java.awt.BorderLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;
import org.jdesktop.swingx.calendar.DateUtils;

/**
 *
 * @author radhe
 */
public class Searching extends javax.swing.JPanel {


    JTabbedPane t;
    Event ev;
    JList eventList;
    JScrollPane listscroll;
    DefaultListModel s,n;
    ClientConnect cc;
    public CachedRowSetImpl crs;
    MainLogin login;
    
    public Searching(final JTabbedPane t, final Event ev, ClientConnect cc) {
        
        initComponents();
        hint.setText("");
        this.cc = cc;
      //  this.setLayout(new BorderLayout());
      this.ev = ev;
      this.t = t;
      s = new DefaultListModel();
      n = new DefaultListModel();
        //db = new DbHandler();
        //con = db.retcon();
        fromDate.getEditor().setEditable(false);
        fromDate.setDate(new Date());
        
        toDate.getEditor().setEditable(false);
        toDate.setDate(new Date());
        
        eventList = new JList(s);
       listscroll = new JScrollPane(eventList);
      /*  PreparedStatement ps;
      String qur = "select * from allocations;";
            //System.out.println("login calliing"+m.uName.getText()+" "+m.pass.getText());
            
            System.out.println("login calliing");
            //Querry q = new Querry(con);
            //rs = q.retrieve(qur);
        try {
            ps = con.prepareStatement(qur);
            //ps.setString(1,m.uName.getText());
            //ps.setString(2,m.pass.getText());
            System.out.println("login calliing");
            ResultSet rs = (ResultSet) ps.executeQuery();
            System.out.println("login calliing result");
            while(rs.next()){
                //m.login.setText("Logged in");
                System.out.println("login calliing while");
                try {
                    System.out.println("login callin bkcvjDkSing"+" "+rs.getString("venue"));
                   s.addElement(rs.getString("venue")+" "+rs.getString("slot"));
                    
                } catch (SQLException ex) {
                    Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
                    System.out.println("login calliing while here");
                }   
            }
        } catch (SQLException ex) {
            Logger.getLogger(Searching.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("login calliing while there");
        }
            
      
        
        eventList.setCellRenderer(new DefaultListCellRenderer());
        eventList.setVisibleRowCount(4);
        
       
        //listscroll.setSize(200, 200);
        listscroll.setBounds(125, 150, 600, 250);
        this.add(listscroll, BorderLayout.CENTER);
        this.setVisible(true);
        
        */
        
        eventList.addMouseListener(new MouseAdapter() {
      @Override
      public void mouseClicked(MouseEvent mouseEvent) {
        JList theList = (JList) mouseEvent.getSource();
        if (mouseEvent.getClickCount() == 2) {
          int index = theList.locationToIndex(mouseEvent.getPoint());
          //int hindex = eventList.getSelectedIndex();
          if (index >= 0) {
            Object o = theList.getModel().getElementAt(index);
            String arr[] = o.toString().split(" ");
            JDialog.setDefaultLookAndFeelDecorated(true);
            int response = JOptionPane.showConfirmDialog(null, "Do you want to create event?", "Confirm",
            JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
            if (response == JOptionPane.OK_OPTION) {
                System.out.println("event called");
                //ev.fromList();
                ev.eventForm.setVisible(true);
                ev.intro.setVisible(false);
                ev.venue.setSelectedItem(arr[0]);
                ev.slot.setSelectedIndex(Integer.parseInt(arr[1])+1);
                
                SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
                Date date;
                try {

                    date = formatter.parse(arr[2]);
                    System.out.println(date);
                    System.out.println(formatter.format(date));
                    ev.date.setDate(date);
                    ev.intro.setVisible(false);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                
                System.out.println(Integer.parseInt(o.toString().split(" ")[1])+1);
                //ev.date.setDate(fromDate.getDate());
                //ev.date.getDate(fromDate.getDate());
                ev.fromList(false);
                ev.new_create.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/create.png")));
                ev.edit.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/cancel.png")));
                ev.state=1;
                t.setSelectedIndex(3);
                  
                  
            }
            else {
                
                
                
            }
            
            
          }
        }
      }
    });
       
        
        
      
        
        
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     * @param f
     * @param t
     * @param index
     */
    
    public void search(Date from, Date to, int index){
        
        
        s.clear();
       
        PreparedStatement ps,qs;
        
        for(Date move=from; !move.equals(DateUtils.nextDay(to)); move = DateUtils.nextDay(move)){
            
            String qur; 
            //System.out.println("login calliing"+m.uName.getText()+" "+m.pass.getText());
            java.sql.Date sdate = new java.sql.Date(move.getTime());
            System.out.println("login calliing");
            
            try {
                if(index==0){
                    qur = "select venue,slot from allocations as l where not exists (select venue, `time` from event as e where `date`="+sdate+" and l.venue=e.venue and l.slot=e.`time`);";
                    //ps = con.prepareStatement(qur);
                    //ps.setDate(1,sdate);
                }
                else{
                    qur = "select venue,slot from allocations as l where not exists (select venue, `time` from event as e where `date`="+sdate+" and l.venue=e.venue and l.slot=e.`time`) and l.slot="+index+"; ";
                    //ps = con.prepareStatement(qur);
                    //ps.setDate(1,sdate);
                    //ps.setInt(2,index);
                }
            
            //ps.setString(2,m.pass.getTe"xt());
            System.out.println("login calliing");
            //ResultSet rs = (ResultSet) ps.executeQuery();
            System.out.println("login calliing result");
            
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
                System.out.println("login calliing while");
                try {
                   // System.out.println("login callin bkcvjDkSing"+" "+rs.getString("venue")+" "+rs.getString("slot")+" "+move.toString());
                    
                    
                   s.addElement(crs.getString("venue")+" "+crs.getString("slot")+" "+sdate.toString());
                   
                    
                } catch (SQLException ex) {
                    Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
                    System.out.println("login calliing while here");
                }   
            }
                
            }
            
            hint.setText("*Your results found.");
            } catch (SQLException ex) {
            Logger.getLogger(Searching.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("login calliing while there");
            }
            
        }
        
        
        
        
        if(s.isEmpty()){
            
            hint.setText("*Your results not found. These are suggested results.");
            
        int i=0;
        for(Date move=DateUtils.nextDay(to); i<10; move = DateUtils.nextDay(move), i++){
            
            String qur; 
            //System.out.println("login calliing"+m.uName.getText()+" "+m.pass.getText());
            java.sql.Date sdate = new java.sql.Date(move.getTime());
            System.out.println("login calliing");
            
            try {
                if(index==0){
                    qur = "select venue,slot from allocations as l where not exists (select venue, `time` from event as e where `date`="+sdate+" and l.venue=e.venue and l.slot=e.`time`);";
                    //ps = con.prepareStatement(qur);
                    //ps.setDate(1,sdate);
                }
                else{
                    qur = "select venue,slot from allocations as l where not exists (select venue, `time` from event as e where `date`="+sdate+" and l.venue=e.venue and l.slot=e.`time`) and l.slot="+index+";";
                    //ps = con.prepareStatement(qur);
                    //ps.setDate(1,sdate);
                    //ps.setInt(2,index);
                }
            
            //ps.setString(2,m.pass.getTe"xt());
            System.out.println("login calliing");
            //ResultSet rs = (ResultSet) ps.executeQuery();
            System.out.println("login calliing result");
            
            
            
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
                System.out.println("login calliing while");
                    try {
                   // System.out.println("login callin bkcvjDkSing"+" "+rs.getString("venue")+" "+rs.getString("slot")+" "+move.toString());
                    
                    
                        s.addElement(crs.getString("venue")+" "+crs.getString("slot")+" "+sdate.toString());
                   
                    
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
            
        }
        
        
        
        
        
        }
        
        
        
        
            
        eventList.setCellRenderer(new DefaultListCellRenderer());
        eventList.setVisibleRowCount(4);
        
        //listscroll.setSize(200, 200);
        listscroll.setBounds(125, 150, 600, 250);
        this.add(listscroll, BorderLayout.CENTER);
        this.setVisible(true);
        
    
    }

    
    
    
    
    
    
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        scrollForm = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        fieldPanel = new javax.swing.JPanel();
        fName = new javax.swing.JTextField();
        lName = new javax.swing.JTextField();
        uName = new javax.swing.JTextField();
        pswd = new javax.swing.JTextField();
        email = new javax.swing.JTextField();
        branch = new javax.swing.JTextField();
        regNo = new javax.swing.JTextField();
        course = new javax.swing.JTextField();
        contact = new javax.swing.JTextField();
        gender = new javax.swing.JComboBox<>();
        picName = new javax.swing.JLabel();
        upload = new javax.swing.JButton();
        register = new javax.swing.JButton();
        fromDate = new org.jdesktop.swingx.JXDatePicker();
        jLabel1 = new javax.swing.JLabel();
        toDate = new org.jdesktop.swingx.JXDatePicker();
        jLabel2 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        search = new javax.swing.JButton();
        slot = new javax.swing.JComboBox<>();
        hint = new javax.swing.JLabel();

        scrollForm.setBackground(new java.awt.Color(69, 149, 104));

        jLabel8.setFont(new java.awt.Font("MV Boli", 1, 36)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(230, 234, 102));
        jLabel8.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel8.setText("Register Here");

        jPanel4.setBackground(new java.awt.Color(69, 149, 104));

        jLabel9.setFont(new java.awt.Font("Trebuchet MS", 2, 14)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(234, 219, 219));
        jLabel9.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel9.setText("First Name");

        jLabel10.setFont(new java.awt.Font("Trebuchet MS", 2, 14)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(234, 219, 219));
        jLabel10.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel10.setText("Last Name");

        jLabel11.setFont(new java.awt.Font("Trebuchet MS", 2, 14)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(234, 219, 219));
        jLabel11.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel11.setText("Username");

        jLabel12.setFont(new java.awt.Font("Trebuchet MS", 2, 14)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(234, 219, 219));
        jLabel12.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel12.setText("Password");

        jLabel13.setFont(new java.awt.Font("Trebuchet MS", 2, 14)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(234, 219, 219));
        jLabel13.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel13.setText("Email id");

        jLabel14.setFont(new java.awt.Font("Trebuchet MS", 2, 14)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(234, 219, 219));
        jLabel14.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel14.setText("Registration id");

        jLabel15.setFont(new java.awt.Font("Trebuchet MS", 2, 14)); // NOI18N
        jLabel15.setForeground(new java.awt.Color(234, 219, 219));
        jLabel15.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel15.setText("Branch");

        jLabel16.setFont(new java.awt.Font("Trebuchet MS", 2, 14)); // NOI18N
        jLabel16.setForeground(new java.awt.Color(234, 219, 219));
        jLabel16.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel16.setText("Course");

        jLabel17.setFont(new java.awt.Font("Trebuchet MS", 2, 14)); // NOI18N
        jLabel17.setForeground(new java.awt.Color(234, 219, 219));
        jLabel17.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel17.setText("Gender");

        jLabel18.setFont(new java.awt.Font("Trebuchet MS", 2, 14)); // NOI18N
        jLabel18.setForeground(new java.awt.Color(234, 219, 219));
        jLabel18.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel18.setText("Contact");

        jLabel19.setFont(new java.awt.Font("Trebuchet MS", 2, 14)); // NOI18N
        jLabel19.setForeground(new java.awt.Color(234, 219, 219));
        jLabel19.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel19.setText("Profile picture");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addContainerGap(41, Short.MAX_VALUE)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel12, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel11, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel9, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 150, Short.MAX_VALUE)
                            .addComponent(jLabel10, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel19, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel18, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel17, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel16, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel15, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel14, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 150, Short.MAX_VALUE)
                            .addComponent(jLabel13, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel18, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel19, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        fieldPanel.setBackground(new java.awt.Color(69, 149, 104));

        fName.setBackground(java.awt.Color.lightGray);
        fName.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        fName.setForeground(new java.awt.Color(102, 83, 23));

        lName.setBackground(java.awt.Color.lightGray);
        lName.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        lName.setForeground(new java.awt.Color(102, 83, 23));

        uName.setBackground(java.awt.Color.lightGray);
        uName.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        uName.setForeground(new java.awt.Color(102, 83, 23));

        pswd.setBackground(java.awt.Color.lightGray);
        pswd.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        pswd.setForeground(new java.awt.Color(102, 83, 23));

        email.setBackground(java.awt.Color.lightGray);
        email.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        email.setForeground(new java.awt.Color(102, 83, 23));

        branch.setBackground(java.awt.Color.lightGray);
        branch.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        branch.setForeground(new java.awt.Color(102, 83, 23));

        regNo.setBackground(java.awt.Color.lightGray);
        regNo.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        regNo.setForeground(new java.awt.Color(102, 83, 23));

        course.setBackground(java.awt.Color.lightGray);
        course.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        course.setForeground(new java.awt.Color(102, 83, 23));

        contact.setBackground(java.awt.Color.lightGray);
        contact.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        contact.setForeground(new java.awt.Color(102, 83, 23));

        gender.setBackground(java.awt.Color.lightGray);
        gender.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        gender.setForeground(new java.awt.Color(102, 83, 23));
        gender.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        picName.setBackground(java.awt.Color.lightGray);
        picName.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        picName.setForeground(new java.awt.Color(102, 83, 23));

        upload.setBackground(java.awt.Color.lightGray);
        upload.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        upload.setForeground(new java.awt.Color(102, 83, 23));
        upload.setText("Upload");
        upload.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                uploadActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout fieldPanelLayout = new javax.swing.GroupLayout(fieldPanel);
        fieldPanel.setLayout(fieldPanelLayout);
        fieldPanelLayout.setHorizontalGroup(
            fieldPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(fName)
            .addComponent(lName)
            .addComponent(uName)
            .addComponent(branch)
            .addComponent(regNo)
            .addComponent(email)
            .addComponent(pswd)
            .addComponent(course)
            .addComponent(contact)
            .addComponent(gender, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(fieldPanelLayout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addComponent(picName, javax.swing.GroupLayout.PREFERRED_SIZE, 192, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(upload, javax.swing.GroupLayout.DEFAULT_SIZE, 133, Short.MAX_VALUE))
        );
        fieldPanelLayout.setVerticalGroup(
            fieldPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(fieldPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(fName, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lName, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(uName, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pswd, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(email, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(regNo, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(branch, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(course, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(gender, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(contact, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(fieldPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(picName, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(upload, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        register.setBackground(java.awt.Color.lightGray);
        register.setForeground(new java.awt.Color(102, 88, 3));
        register.setText("Register");
        register.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                registerMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout scrollFormLayout = new javax.swing.GroupLayout(scrollForm);
        scrollForm.setLayout(scrollFormLayout);
        scrollFormLayout.setHorizontalGroup(
            scrollFormLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(scrollFormLayout.createSequentialGroup()
                .addGap(82, 82, 82)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(fieldPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(151, 151, 151))
            .addGroup(scrollFormLayout.createSequentialGroup()
                .addGroup(scrollFormLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(scrollFormLayout.createSequentialGroup()
                        .addGap(249, 249, 249)
                        .addComponent(register, javax.swing.GroupLayout.PREFERRED_SIZE, 268, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(scrollFormLayout.createSequentialGroup()
                        .addGap(113, 113, 113)
                        .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 538, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        scrollFormLayout.setVerticalGroup(
            scrollFormLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(scrollFormLayout.createSequentialGroup()
                .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(22, 22, 22)
                .addGroup(scrollFormLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(fieldPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addComponent(register, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(32, Short.MAX_VALUE))
        );

        setBackground(new java.awt.Color(69, 149, 104));

        jLabel1.setForeground(new java.awt.Color(253, 252, 252));
        jLabel1.setText("FROM");

        jLabel2.setForeground(new java.awt.Color(253, 252, 252));
        jLabel2.setText("TO");

        jLabel5.setForeground(new java.awt.Color(253, 252, 252));
        jLabel5.setText("DATE");

        jLabel7.setForeground(new java.awt.Color(253, 252, 252));
        jLabel7.setText("SLOT");

        search.setText("SEARCH");
        search.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                searchMouseClicked(evt);
            }
        });

        slot.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Free Time", "08:00 - 10:00", "10:00 - 12:00", "02:00 - 04:00", "04:00 - 06:00", "06:00 - 08:00", " " }));
        slot.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                slotActionPerformed(evt);
            }
        });

        hint.setForeground(new java.awt.Color(253, 252, 252));
        hint.setText("*Your results found.");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(100, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(hint, javax.swing.GroupLayout.PREFERRED_SIZE, 294, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(fromDate, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(8, 8, 8)
                                .addComponent(jLabel2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(toDate, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLabel7)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(slot, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(144, 144, 144)
                                .addComponent(jLabel5)))
                        .addGap(18, 18, 18)
                        .addComponent(search)))
                .addGap(61, 61, 61))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel5)
                .addGap(24, 24, 24)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(fromDate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7)
                    .addComponent(slot, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(search)
                    .addComponent(jLabel2)
                    .addComponent(toDate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(hint)
                .addContainerGap(353, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void uploadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_uploadActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_uploadActionPerformed

    private void registerMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_registerMouseClicked
        
    }//GEN-LAST:event_registerMouseClicked

    private void searchMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_searchMouseClicked
        // TODO add your handling code here:
        
        search(fromDate.getDate(),toDate.getDate(),slot.getSelectedIndex());
    }//GEN-LAST:event_searchMouseClicked

    private void slotActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_slotActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_slotActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.JTextField branch;
    public javax.swing.JTextField contact;
    public javax.swing.JTextField course;
    public javax.swing.JTextField email;
    public javax.swing.JTextField fName;
    private javax.swing.JPanel fieldPanel;
    public org.jdesktop.swingx.JXDatePicker fromDate;
    public javax.swing.JComboBox<String> gender;
    private javax.swing.JLabel hint;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel4;
    public javax.swing.JTextField lName;
    public javax.swing.JLabel picName;
    public javax.swing.JTextField pswd;
    public javax.swing.JTextField regNo;
    public javax.swing.JButton register;
    private javax.swing.JPanel scrollForm;
    public static javax.swing.JButton search;
    public javax.swing.JComboBox<String> slot;
    public org.jdesktop.swingx.JXDatePicker toDate;
    public javax.swing.JTextField uName;
    public javax.swing.JButton upload;
    // End of variables declaration//GEN-END:variables
}
