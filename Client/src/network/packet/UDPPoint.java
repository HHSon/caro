/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package network.packet;

import java.io.IOException;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.logging.Level;
import java.util.logging.Logger;
import network.bit.*;
import network.socket.Client;
import network.socket.UDPSocket;
import network.symbol.Symbol;

/**
 *
 * @author LHS
 */
public class UDPPoint extends UDPPacket {

    int x;
    int y;

    public UDPPoint(int x, int y, byte type, String username) {
        super(type, username);
        this.x = x;
        this.y = y;
        Bits.putInt(data, this.lengHeader, x);
        Bits.putInt(data, this.lengHeader + 4, y);
    }

    public UDPPoint(byte[] data) {
        super(data);
        this.x = Bits.getInt(data, this.lengHeader);
        this.y = Bits.getInt(data, lengHeader + 4);

    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    @Override
    public byte[] createData() {
        return this.data;
    }

    @Override
    public UDPPacket createPkt() {
        return this;
    }
}

class runDemo {

    public static void main(String[] args) throws InterruptedException {
        Client client = null;
        
        try {
            client = new Client(Symbol.IP_SERVER, 5000);
        } catch (UnknownHostException ex) {
            System.out.println(ex);
        } catch (IOException ex) {
            System.out.println(ex);
        }

        for (int i = 0; i < 20; i++) {

            UDPPacket t = new UDPPoint(100, 200, Symbol.TYPE_POINT, "thanh cong roif");
            UDPPoint point = new UDPPoint(t.createData());
            try {
                client.sendByte(t.createData());
            } catch (IOException ex) {
                System.out.printf("Khong the gui " + ex);
            }

            t = new UDPMessage(Symbol.TYPE_MESSAGE, "lamhaison", "Xin chao ban, minh la son");
            try {
                client.sendByte(t.createData());
            } catch (IOException ex) {
                System.out.printf("Khong the gui2 " + ex);
            }
            Thread.sleep(1000);
        }

    }
}
