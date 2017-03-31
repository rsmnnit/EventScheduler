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
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.ConnectException;
import java.net.Socket;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class RequestStore extends javax.swing.JFrame {

    /**
     * Creates new form MiddleMan
     */
    Socket s;
    String query;
    Data data;
    
    
    public RequestStore(Data data) {
        this.data = data;
    }
              
    
    public void store() throws IOException{
        File file = new File("/home/radhe/project/store.txt");
        
        boolean deleted=file.delete();
        
        System.out.println(deleted);
        if(!file.exists())
        {
            try {
                 file.createNewFile();
            } catch (IOException ex1) {
                Logger.getLogger(RequestStore.class.getName()).log(Level.SEVERE, null, ex1);
            }
        }
        
        FileOutputStream fout = null;
        ObjectOutputStream oout = null;
        try {
            fout = new FileOutputStream(file);
            oout = new ObjectOutputStream(fout);
            
            oout.writeObject(data);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(RequestStore.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(RequestStore.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally{
            oout.close();
        }
                
        
    } 
                                        
                
}
