/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Event;

import static Event.FrontPage.front;
import java.awt.BorderLayout;
import java.awt.Color;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BorderFactory;
import javax.swing.border.Border;



/**
 *
 * @author Jayant Kumar Yadav
 */
public class Register implements Runnable{
    
    Registration R;
    PreparedStatement ps;
    UserView uv;
    ClientConnect cc;
    
    public Register(Registration R, UserView uv, ClientConnect cc){
        
        this.R=R;
        this.uv=uv;
        this.cc=cc;
        
    }
    
    public Boolean chkName(String name){
        return name != null;
    } 
    
    
    @Override
    public void run() {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        String value;
        System.out.println("calling thread");
        value=R.fName.getText();
        System.out.println("calling thread");
        
        
        
        Border border = BorderFactory.createLineBorder(Color.yellow, 2);
        System.out.println("calling thread");
        if(!chkName(value)){
            System.out.println("calling thread");
            R.fName.setBorder(border);
            R.fName.setText("mar jao");
            R.repaint();
            R.setVisible(true);
        }
        
        
        
        String qur = "insert ignore into member values('"+R.fName.getText()+"','"+R.lName.getText()+"',"+Integer.parseInt(R.regNo.getText())+",'"+R.email.getText()+"','"+R.gender.getSelectedItem().toString()+"',"+Integer.parseInt(R.contact.getText())+",'"+R.picName.getText()+"','"+R.course.getSelectedItem().toString()+"','"+R.uName.getText()+"','"+R.pswd.getText()+"','"+R.branch.getSelectedItem().toString()+"','"+R.access.getSelectedItem().toString()+"','"+R.yearn.getText()+"','"+new java.sql.Date(R.dob.getDate().getTime())+"')";
        /*DbHandler db = new DbHandler();
        Connection con = db.retcon();*/
        //Querry q = new Querry(con);
        //int s = q.update(qur);
        System.out.println("inserted "+cc.connected);
        cc.getQuerry(qur, 1, cc);
        
        try {
            Thread.sleep(75);
        } catch (InterruptedException ex) {
            Logger.getLogger(Register.class.getName()).log(Level.SEVERE, null, ex);
        }
         System.out.println("inserted "+cc.connected);
        if(cc.connected){
            System.out.println("inserted1 "+cc.update);
            while(!cc.update){
            System.out.print("");
            }
             System.out.println("\ninserted2");
             cc.update = false;
             System.out.println("inserted3");
            Checker f = new Checker(2,"registered");
                Thread t = new Thread(f);
                t.start();
                
                cc.uname = R.uName.getText();
                System.out.println("inserted");
            try {
                uv = new UserView(cc);
            } catch (SQLException ex) {
                Logger.getLogger(Register.class.getName()).log(Level.SEVERE, null, ex);
            }
                front.remove(R);
                front.add(uv,BorderLayout.CENTER);
                front.pack();
                front.setVisible(true);
        }
        else{
            R.regerror.setText("Some fields have wrong entry.");
            R.repaint();
            R.setVisible(true);
        }
       
        
        //try {
            
            
           /* ps = con.prepareStatement(qur);
            ps.setString(1,R.fName.getText());
            ps.setString(2,R.lName.getText());
            ps.setInt(3,Integer.parseInt(R.regNo.getText()));
            ps.setString(4,R.email.getText());
            ps.setString(5,R.gender.getSelectedItem().toString());
            ps.setInt(6,Integer.parseInt(R.contact.getText()));
            ps.setString(7,R.picName.getText());
            ps.setString(8,R.course.getSelectedItem().toString());
            ps.setString(9,R.uName.getText());
            ps.setString(10,R.pswd.getText());
            ps.setString(11,R.branch.getSelectedItem().toString());
            ps.setString(12,R.access.getSelectedItem().toString());
            ps.setString(13,R.yearn.getText());
            java.sql.Date d = new java.sql.Date(R.dob.getDate().getTime());
            ps.setDate(14,d);*/
           
            
            //int rs = ps.executeUpdate();
            
           // if(rs>0){
       
        
        System.out.println("in the thread.");
        
        
    }
    
    
    
}
