/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sve;

import java.net.ServerSocket;
import java.net.Socket;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author lazar
 */
public class Control extends Thread{
    List<ClientHandler> listaKorisnika;
    public static Control instance;
    ServerSocket serverSocket;
    boolean pokrecem=false;
    public static int zavrsio=0;
    public static int brojPokusajaPrvog;
    public static int brojPokusajaDrugog;
    public Control() {
        listaKorisnika=new LinkedList<>();
        start();
    }
    
    @Override
    public void run() {
        
        try {
                serverSocket=new ServerSocket(9000);               
                Socket s=serverSocket.accept();
                ClientHandler cl=new ClientHandler(s);
                listaKorisnika.add(cl);
                s=serverSocket.accept();
                cl=new ClientHandler(s);
                listaKorisnika.add(cl);
                for (ClientHandler c : listaKorisnika) {
                c.send(new Response("", null));
                c.start();
            }
            System.out.println("povezali su se dva korisnika");
        } catch (Exception e) {
            System.out.println("server se gasi");
            e.printStackTrace();
        }
        
        
    
    }
    
    public static Control getInstance(){
    if(instance==null)
        instance=new Control();
    return instance;
    }
    
}
