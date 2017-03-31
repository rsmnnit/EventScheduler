/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Event;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Jayant Kumar Yadav
 */
public class Checker implements Runnable{
    
    public int p;
    public final String fileName = "C:\\Users\\Jayant Kumar Yadav\\Desktop\\event\\temp.txt";
    public int n;
    public String str;
    
    public Checker(int n){
        this.n=n;
        p=0;
    }
    
    public Checker(int n,String str){
        this.n=n;
        p=0;
        this.str = str;
    }
    
    
    
    @Override
    public void run() {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        if(n==1)
            read();
        else if(n==2)
            write(str);
    
    }
    
    public void read(){
        
        FileReader fileReader = null;
        
        try {
            String line = "";
            fileReader = new FileReader(fileName);
            //System.out.println("read file");
            // Always wrap FileReader in BufferedReader.
            BufferedReader bufferedReader = new BufferedReader(fileReader);
           // System.out.println("read file");
            
           
           
            while((line = bufferedReader.readLine()) != null) {
                System.out.println(line);
                //String[] s = line.split("$");
                //System.out.println("read file ");
                if(0<=line.indexOf("registered")){
                    p=1;
                    System.out.println("read file p="+p);
                }
                if(0<line.indexOf("logged")){
                    p=2;
                }
            }
            
            
            // Always close files.
            bufferedReader.close();
            //return p;
            fileReader.close();
            
            
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Checker.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Checker.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    //return p;
    
    
    
    
    public void write(String str){
        
        File file = new File(fileName);
        
        try{
            if(!file.exists()){
                file.createNewFile();
            }
                        
            FileWriter fw = new FileWriter(file,true);
            try (BufferedWriter bw = new BufferedWriter(fw); PrintWriter out = new PrintWriter(bw)) {
                out.print(str);
            }
          
            
        }catch(IOException ex) {
            Logger.getLogger(Checker.class.getName()).log(Level.SEVERE, null, ex);
        }  
    }
    
    
    
}
