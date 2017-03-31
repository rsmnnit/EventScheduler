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
import java.sql.DriverManager;
import java.sql.SQLException;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

public class DbHandler {
    
   
    Connection con;
    
    public DbHandler(){
        
        try{
            
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/event_scheduling","root","");
        
        }catch(ClassNotFoundException | InstantiationException | IllegalAccessException | SQLException e){   
        }
        
    }
    
    public Connection retcon(){
        return con;
    }    
}
