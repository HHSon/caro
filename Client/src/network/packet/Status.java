/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package network.packet;

import network.bit.Bits;
import network.bit.Bytes;
import network.symbol.Symbol;

/**
 *
 * @author LHS
 */
public class Status extends Packet {

    byte status;
    String username;

    public Status(byte[] data) {
        super(data);
        this.status = data[2];
        int length = Bits.getInt(data, 3);
        username = new String(Bytes.copyByte(data, 7, 7 + length));
    }

    public Status(String username, byte status) {
        super(Symbol.TYPE_STATUS, Symbol.FLAG_REQUEST);
        this.data[2] = status;

        byte[] arrayByte = username.getBytes();
        Bits.putInt(data, 3, arrayByte.length);
        Bytes.addByte(data, 7, arrayByte, arrayByte.length);
    }

    @Override
    public void printInformation() {

        System.out.println("<-----------Staus---------->");
        System.out.println("Type: " + this.type);
        System.out.println("Flag: " + this.flag);
        System.out.println("Status: " + this.status);
        System.out.println("Username: " + this.username);
        System.out.println("<-----------Staus---------->");
    }
}

//class run {
//
//    public static void main(String[] args) {
//        Status staus = new Status("lamhaison", Symbol.STATE_ONLINE);
//        staus = new Status("lamhaison", Symbol.STATE_OFFLINE);
//        Status t = new Status(staus.createData());
//        t.printInformation();
//
//
//    }
//}
