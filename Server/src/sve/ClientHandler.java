/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sve;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author lazar
 */
public class ClientHandler extends Thread{

    Socket socket;
    ObjectInputStream in;
    ObjectOutputStream out;
    public static int brojj=0;
    public int broj;
    public static String rec;
    int brojPogadjanja=0;
    int brojPogodjenih=0;
    public ClientHandler(Socket s) {
        try {
            socket=s;
            out=new ObjectOutputStream(s.getOutputStream());
            in=new ObjectInputStream(s.getInputStream());
            brojj++;
            broj=brojj;
            
        } catch (IOException ex) {
            Logger.getLogger(ClientHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    @Override
    public void run() {
        
        try {
            rec=Meni.rec;
            while (true) {                
                Request request=receive();
                switch (request.getOperacija()) {
                    case null:
                        
                        break;
                    case pokusaj:
                        brojPogadjanja++;
                        System.out.println("ulazi");
                        String indeksiPogodjenih="";
                        char slovo=((String) request.getObject()).toUpperCase().charAt(0);
                        for(int i=0; i<5; i++){
                        char s=rec.charAt(i);
                        int j=i+1;
                        if(s==slovo){
                        indeksiPogodjenih=indeksiPogodjenih+j;
                        }
                        }
                        brojPogodjenih=brojPogodjenih+indeksiPogodjenih.length();
                        if(broj==1){
                            Meni.izmeniPrvi("("+brojPogadjanja+"/"+brojPogodjenih+")");
                        }else{
                        Meni.izmeniDrugi("("+brojPogadjanja+"/"+brojPogodjenih+")");
                        }
                        System.out.println("1");
                        send(new Response(indeksiPogodjenih, null));
                        System.out.println("1");
                        break;
                        
                    case pobeda:
                        if(broj==1)
                            Control.brojPokusajaPrvog=(int) request.getObject();
                        else
                            Control.brojPokusajaDrugog=(int) request.getObject();
                        Control.zavrsio++;
                        boolean kraj=false;
                        while (!kraj) {                            
                            if(Control.zavrsio==2)
                                kraj=true;
                            else
                                sleep(2000);
                        }
                        if(broj==1){
                        if(Control.brojPokusajaPrvog<=Control.brojPokusajaDrugog){
                        send(new Response("pobeda", null));
                        }else{
                        send(new Response("poraz", null));
                        }
                        }
                        if(broj==2){
                        if(Control.brojPokusajaPrvog>=Control.brojPokusajaDrugog){
                        send(new Response("pobeda", null));
                        }else{
                        send(new Response("poraz", null));
                        }
                        }
                        break;
                    default:
                        throw new AssertionError();
                }
            }
        } catch (Exception e) {
            System.out.println("korisnik izlazi");
            e.printStackTrace();
            
        }
    
    }
    
    
    public synchronized void send(Response response){
        try {
            out.writeObject(response);
            out.flush();
        } catch (IOException ex) {
            Logger.getLogger(ClientHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public synchronized Request receive(){
        try {
            return (Request) in.readObject();
        } catch (IOException ex) {
            Logger.getLogger(ClientHandler.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ClientHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
    return null;
    }
    
    
    
}
