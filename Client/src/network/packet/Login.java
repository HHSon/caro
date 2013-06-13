/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package network.packet;

import java.io.IOException;
import network.socket.Client;
import network.symbol.Symbol;


/**
 *
 * @author LHS
 */
public class Login extends Packet {

    private String username;
    private String password;
    private byte accepter;

    public Login(String username, String password, byte flag, byte accepter) {
        this.username = username;
        this.password = password;
        this.accepter = accepter;
        this.flag = flag;
    }

    public Login(String username, String password) {
        this.password = password;
        this.username = username;
        this.flag = Symbol.FLAG_REQUEST;
        this.accepter = Symbol.ACCEPTER_FALASE;
    }

    public Login(byte accpeter) {
        this.accepter = accpeter;
        this.flag = Symbol.FLAG_REPLAY;
        this.username = null;
        this.password = null;
    }

    public String getPassword() {
        return password;
    }

    public String getUsername() {
        return username;
    }

    public byte getAccepter() {
        return accepter;
    }

    @Override
    public byte getFlag() {
        return super.getFlag();
    }

    public void setAccepter(byte accepter) {
        this.accepter = accepter;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}

class rundemo {

    public static void main(String[] args) throws IOException, InterruptedException, InterruptedException {

//        for (int i = 0; i < 200; i++) {
//            Client tcp = new Client("127.0.0.1", 5000);
//            Thread.sleep(50);
//            try {
//                tcp.close();
//            } catch (Exception e) {
//                System.out.println("Can't close socket " + (i + 1));
//            }
//            System.out.println(tcp.getSocket().isClosed());
//
//            System.out.println("Create socket " + (i + 1) + "th");
//        }


        Client tcp = new Client("127.0.0.1", 5000);
        Login login = new Login("lamhaison", "230866668");
        login = new Login("lamhaison", "admin");
        for (int i = 0; i < 1; i++) {
            tcp.sendObject(login);
//            tcp.close();
            Thread.sleep(50);
        }
        
        while(true){
            
        }
        

    }
}
