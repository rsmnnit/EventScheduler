package Event;

import com.sun.rowset.CachedRowSetImpl;
import java.io.Serializable;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Jayant Kumar Yadav
 */
public class Data implements Serializable{
    
    public String q;
    public int flag; 
    public String fp;
    public String filename;
    public CachedRowSetImpl crs;
    public String title;
    public String block;
    
    public void getData(String qu,int f){
            q=qu;
            flag=f;
    }
    
    
     public void getData(String qu,int f, String title){
            q=qu;
            flag=f;
            this.title = title;
    }
    
    
    
    public void getData(String qu,int f, String fp, String fn){
            q=qu;
            flag=f;
            filename=fn;
            this.fp=fp;
    }
    
}
