/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package svee;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import sve.Request;
import sve.Response;

/**
 *
 * @author lazar
 */
public class Control {
    public static Control instance;
    Socket socket;
    ObjectInputStream in;
    ObjectOutputStream out;

    public Control() {
        try {
            socket=new Socket("localhost", 9000);
            out=new ObjectOutputStream(socket.getOutputStream());
            in=new ObjectInputStream(socket.getInputStream());
            
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Greska, server mozda nije pokrenut");
            System.exit(0);
        }
        
        
    }
    
    
    
    public static Control getInstance(){
    if(instance==null)
        instance=new Control();
    return instance;
    
    }
    
    public synchronized void send(Request request){
        try {
            out.writeObject(request);
            out.flush();
        } catch (IOException ex) {
            Logger.getLogger(Control.class.getName()).log(Level.SEVERE, null, ex);
        }
    
    }
    
    public synchronized Response receive(){
        try {
            return (Response) in.readObject();
        } catch (IOException ex) {
            Logger.getLogger(Control.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Control.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
}
