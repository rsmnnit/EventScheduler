/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Event;

/**
 *
 * @author radhe
 */
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author radhe
 */
public class Querry {
    
    
    PreparedStatement stmt;
    ResultSet rs;
    Connection con;
    
    
    public Querry(Connection con){
        this.con=con;
    }
   
    
    
   
    public ResultSet retrieve(String sql){
       // Statement stmt = con.createStatement();
        
        try{
            
            stmt = con.prepareStatement(sql);
            rs = (ResultSet)stmt.executeQuery(sql);
         
        } catch (SQLException ex) {
            Logger.getLogger(Querry.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return rs;
    }
    
    
    
    public int update(String sql){
        
        int res = 0;
        try{
            
             res = stmt.executeUpdate(sql);
             
        } catch (SQLException ex) {
            Logger.getLogger(Querry.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return res;
    }
}
