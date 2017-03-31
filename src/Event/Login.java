/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Event;

import static Event.FrontPage.front;
import com.sun.rowset.CachedRowSetImpl;
import java.awt.BorderLayout;
import java.awt.Color;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BorderFactory;

/**
 *
 * @author Jayant Kumar Yadav
 */




public class Login implements Runnable{
    
    
    MainLogin m;
    ResultSet rs;
    PreparedStatement ps;
    UserView uv;
    ClientConnect cc;
    CachedRowSetImpl crs;
    
    
    public Login(MainLogin m, UserView uv, ClientConnect cc){
        this.m=m;
        this.uv=uv;
        this.cc=cc;
    }
    
    
    
    @Override
    public void run() {
        try {
            //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            //System.out.println("login calliing");
            
            
            String qur = "select * from member where uname='"+m.uName.getText()+"' and password='"+String.valueOf(m.pass.getPassword())+"';";
            //System.out.println("login calliing"+m.uName.getText()+" "+String.valueOf(m.pass.getPassword()));
            
           // DbHandler db = new DbHandler();
            //Connection con = db.retcon();
            
            System.out.println("login calling");
            //Querry q = new Querry(con);
            //rs = q.retrieve(qur);
            
            
            //ps = con.prepareStatement(qur);
            
            
            //ps.setString(1,m.uName.getText());
            //ps.setString(2,String.valueOf(m.pass.getPassword()));
            System.out.println("login calling showing ps ");
            //System.out.println(ps.toString().split(": "));
            
            cc.getQuerry(qur, 2, cc);
            
            
            try {
                Thread.sleep(150);
            } catch (InterruptedException ex) {
                Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            
            
            if(cc.connected){
                
                //System.out.println("manish"+cc.resultOk);
                while(!cc.resultOk){
                    System.out.print("");
                    
                }
                //System.out.println("\nradhey "+cc.resultOk);
                crs = cc.sendResult();
                
                
                if(crs.next()){
                
                System.out.println("login g");
               
               // System.out.println("login callin bkcvjDkSing"+" "+crs.getString("uname"));
                
                
                /*if(m.remember.isSelected()){
                    Checker f = new Checker(2,"$logged");
                    Thread t = new Thread(f);
                    t.start();
                }
                */
                System.out.println("login callin bkcvjDkSing");
                
                
                
                cc.uname = m.uName.getText();
                
                
                uv = new UserView(cc);
                uv.t4.listvenue();
                front.remove(m);
                front.add(uv,BorderLayout.CENTER);
                front.pack();
                front.setVisible(true);
                 
            }
            else{
                
                
                m.error.setText("* Login details are in correct.");
                m.login.setBorder(BorderFactory.createLineBorder(Color.red, 2));
                m.pass.setBorder(BorderFactory.createLineBorder(Color.red, 2));
                m.loginpanel.repaint();
                m.loginpanel.setVisible(true);
                
            }
                
                
            }
            
            //rs = (ResultSet) ps.executeQuery();
            
            
            
            
            System.out.println("in the thread.");
            
            
        } catch (SQLException ex) {
            Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
