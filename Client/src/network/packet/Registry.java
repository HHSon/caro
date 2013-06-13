/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package network.packet;

import java.io.IOException;
import java.net.UnknownHostException;
import java.util.logging.Level;
import java.util.logging.Logger;
import network.socket.Client;
import network.symbol.Symbol;


/**
 *
 * @author LHS
 */
public class Registry extends Packet {

    private String username;
    private String password;
    private String fullname;
    private byte accepter;
    private int score;

    public Registry(byte flag, String username, String password, String fullname, byte accepter, int score) {
        this.username = username;
        this.password = password;
        this.fullname = fullname;
        this.accepter = accepter;
        this.flag = flag;
        this.score = score;
    }

    public Registry(String username, String password, String fullname) {

        this.username = username;
        this.password = password;
        this.fullname = fullname;
        this.accepter = Symbol.ACCEPTER_FALASE;
        this.flag = Symbol.FLAG_REQUEST;
    }

    public Registry(byte accpeter) {
        this.accepter = accpeter;
        this.username = null;
        this.password = null;
        this.fullname = null;
        this.flag = Symbol.FLAG_REPLAY;
    }

    public String getFullname() {
        return fullname;
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

    public void setAccepter(byte accepter) {
        this.accepter = accepter;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }
}

class runRegistry {

    public static void main(String[] args) throws UnknownHostException, IOException {

        for (int i = 0; i < 2; i++) {
            Client tcp = new Client(Symbol.IP_SERVER, Symbol.PORT_SERVER);
            tcp.sendObject(new Registry(i + "", 1 + "", i + ""));
            try {
                Thread.sleep(100);
            } catch (InterruptedException ex) {
                Logger.getLogger(runRegistry.class.getName()).log(Level.SEVERE, null, ex);
            }
        }



    }
}
