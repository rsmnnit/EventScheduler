/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Event;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JDialog;

/**
 *
 * @author Jayant Kumar Yadav
 */
public class BackRequest implements Runnable {
    
    ClientConnect cc;
    String filename;
    Data data;
    
    BackRequest(ClientConnect cc){
    
        this.cc =cc;
        filename = "C:\\Users\\Jayant Kumar Yadav\\Desktop\\event\\store.txt";
        
    }

    @Override
    public void run() {
        
        File file = new File(filename);
        System.out.println("request gyi ....");
                if(file.exists()){
                    try {
                        FileInputStream fin = new FileInputStream(filename);
                        ObjectInputStream oin = new ObjectInputStream(fin);
                        
                        data = (Data) oin.readObject();
                        //fileReader = new FileReader(filename);
                        //System.out.println("read file");
                        // Always wrap FileReader in BufferedReader.
                        //BufferedReader bufferedReader = new BufferedReader(fileReader);
                        // System.out.println("read file");
                        
                        
                        
                    
                        
                       
                        cc.getQuerry(data, cc, true);
                       
                        try {
                            Thread.sleep(100);
                        } catch (InterruptedException ex) {
                            Logger.getLogger(Searching.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        if(cc.connected){
                            
        System.out.println("redfsdfgdgfsdfbgd  quest gyi ....");
                            while(!cc.update){
                                System.out.print("");
                            }
                    
                            cc.update = false;
                            
                            File files = new File("C:\\Users\\Jayant Kumar Yadav\\Desktop\\event\\store.txt");
        
                            files.delete();
                            
                        }
                        
                        
                        
                        
                        
                        
                        
                        
                        
                    } catch (FileNotFoundException ex) {
                        Logger.getLogger(Checker.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (IOException ex) {
                        Logger.getLogger(Checker.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (ClassNotFoundException ex) {
                Logger.getLogger(BackRequest.class.getName()).log(Level.SEVERE, null, ex);
            }
                }
        
        
    }
    
    
    
    
}
