/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Event;


import com.sun.rowset.CachedRowSetImpl;
import java.io.BufferedWriter;
import java.io.EOFException;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ConnectException;
import java.net.Socket;
import java.net.SocketException;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JDialog;
import javax.swing.JOptionPane;

/**
 *
 * @author radhe
 */
public class ClientConnect implements Runnable{
    
    
    Socket s; 
    static  ObjectInputStream din;
    static ObjectOutputStream dout;
    int count = 0;
    Data localdata;
    String querry;
    public boolean doTask, connected, resultOk, update;
    int flag;
    CachedRowSetImpl crs;
    FrontPage frame;
    public ClientConnect cc;
    String uname="";
    boolean previous;
    
    public ClientConnect(FrontPage frame) {
        localdata = new Data();
        doTask = false;
        connected = false;
        resultOk = false;
        update = false;
        crs = null;
        s=null;
        this.frame = frame;
        previous = false;
    }

    
    
    
    public void executeTask() throws IOException, ClassNotFoundException, SQLException{
       // localdata=dobj;
        
        try{
            
        
        dout = new ObjectOutputStream(s.getOutputStream());
        dout.writeObject(localdata);
        
        
        System.out.println(localdata.q);
        
        din = new ObjectInputStream(s.getInputStream());
        
        if(localdata.flag==1 || localdata.flag == 5 || localdata.flag==6){
            Data ret= (Data) din.readObject();
            if(ret.flag<=0){
                System.out.println("update unsuccesful");
                if(previous){
                    previous=true;
                    System.out.println("update unsuccesful");
                            int response = JOptionPane.showConfirmDialog(frame, "Previously stored querry was unsuccessful.!!", "Confirm",
                            JOptionPane.OK_OPTION, JOptionPane.QUESTION_MESSAGE);
                }
            }
            else{
                 cc.update = true;
                 System.out.println("update successful");
                 if(previous){
                     previous=false;
                     System.out.println("update unsuccesful");
                            int response = JOptionPane.showConfirmDialog(frame, "File is downloaded in Downloads !!", "Confirm",
                            JOptionPane.OK_OPTION, JOptionPane.QUESTION_MESSAGE);
                            
                 }
            }
            
        }
        else if(localdata.flag == 3){
        
            Data ret= (Data) din.readObject();
            crs = ret.crs;
            
            if(!ret.filename.equals("empty")){
                
                BufferedWriter bw = null;
                try{
                    
                    File fil = new File("C:\\Users\\Jayant Kumar Yadav\\Downloads\\"+ret.filename);
                    fil.createNewFile();
                    bw = new BufferedWriter(new FileWriter(fil));
                    bw.write(ret.fp);
                    
                }catch(Exception e){}
                finally{
                    if(bw!=null)
                        bw.close();
                }
            
            }
            
            cc.resultOk=true;
            
        }
        else{
        //System.out.println("suck client");
           
            crs = (CachedRowSetImpl) din.readObject();
           // System.out.println("update successful jack");
            cc.resultOk = true;
            
           /* while(crs.next()){
                System.out.println("update successful jack");
                System.out.println(crs.getString("USERNAME")+" "+crs.getString("PASSWORD")+"jayant dsbjaald");
            }*/
            
            
            System.out.println(count);
            count++;
        }
    
    }catch(SocketException ex){
        
        System.out.println("exception querry reocrded in this file.");
        RequestStore middle = new RequestStore(localdata);
        middle.store();
    }catch(EOFException e){
        
        System.out.println("dialog open kar");
        int response = JOptionPane.showConfirmDialog(frame, "Request is stored locally next time send automatically !!", "Confirm",
        JOptionPane.OK_OPTION, JOptionPane.QUESTION_MESSAGE);
        
    }
        
    }
    
    
    
    
    public void getQuerry(String querry,int flag, ClientConnect cc){
        if(querry!=null){
            localdata.q = querry;
            localdata.flag = flag;
            this.cc = cc;
            doTask = true;
        }
    }
    
    public void getQuerry(String querry,int flag, ClientConnect cc, String block){
        if(querry!=null){
            localdata.q = querry;
            localdata.flag = flag;
            localdata.block = block;
            this.cc = cc;
            doTask = true;
        }
    }
    
    
    
    
    
     public void getQuerry(Data data, ClientConnect cc, boolean previous){
        if(data!=null){
            localdata = data;
            this.cc = cc;
            doTask = true;
            previous = true;
        }
    }
    
    
    
    
    
    
    public void getQuerry(String querry,int flag, ClientConnect cc, String title, String filename, String fileattached, String block){
        if(querry!=null){
            localdata.q = querry;
            localdata.flag = flag;
            localdata.title = title;
            localdata.filename = filename;
            localdata.fp = fileattached;
            localdata.block = block;
            this.cc = cc;
            doTask = true;
        }
    }
    
    
    public void getQuerry(String querry,int flag, ClientConnect cc, String filename, String fileattached){
        if(querry!=null){
            localdata.q = querry;
            localdata.flag = flag;
            localdata.filename = filename;
            localdata.fp = fileattached;
            this.cc = cc;
            doTask = true;
        }
    }
    
    
    
    public CachedRowSetImpl sendResult(){
        cc.resultOk=false;
        return crs;
    }
    

    @Override
    public synchronized void run() {
        int i=0;
        while(true){
            
            
            
           // if(s!=null)
            //    System.out.println("socket ke  "+ (s.isConnected()));
            
            
            
            
            try {
                
                
                //System.out.println("h");
                while(s==null || !s.isConnected() || !s.isBound() || s.isClosed()){
                    try {
                        //System.out.println("socket ke aandar aa gya");
                        frame.setTitle("Disconnected");
                        s = new Socket("localhost",8000);
                        frame.setTitle("Connected");
                        connected = true;
                        
                    } catch (SocketException ex) {
                        //System.out.println("socket ke aandar aa gya exception pakda");
                        connected = false;
                        frame.setTitle("Disconnected");
                        Logger.getLogger(ClientConnect.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                
                
                
            } catch (IOException ex) {
                 //System.out.println("exception pakda");
                Logger.getLogger(ClientConnect.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            
            
           
            if(doTask && s.isConnected()){
                try {
                    executeTask();
                     System.out.println("exception pakda3");
                    doTask = false;
                } catch (IOException | ClassNotFoundException | SQLException ex) {
                    Logger.getLogger(ClientConnect.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
             
            
        }
        
    }
    
    
}
