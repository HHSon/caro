/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package network.packet;

import network.bit.*;

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
//class runDemo {
//
//    public static void main(String[] args) throws SocketException, IOException, InterruptedException {
//        UDPSocket socket = new UDPSocket(5100);
//
//        for (int i = 0; i < 20; i++) {
//
//            UDPPacket t = new UDPPoint(100, 200, Symbol.TYPE_POINT, "thanh cong roif");
//            UDPPoint point = new UDPPoint(t.createData());
//
//
//            socket.sendAnycast(t.createData(), "127.0.0.1", 5200, true);
//
//            t = new UDPMessage(Symbol.TYPE_MESSAGE, "lamhaison", "Xin chao ban, minh la son");
//            socket.sendAnycast(t.createData(), "127.0.0.1", 5200, true);
//            Thread.sleep(100);
//        }
//
//    }
//}
