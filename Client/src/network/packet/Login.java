/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package network.packet;

import network.bit.Bits;
import network.bit.Bytes;

/**
 *
 * @author LHS
 *
 */
public class Login extends Packet {

    protected String username;
    protected String password;
    protected byte accept;  // byte 2

    public Login() {
    }

    public Login(String username, String password, byte type, byte flag, byte accept) {
        super(type, flag);
        this.username = username;
        this.password = password;
        this.accept = accept;
        this.data[2] = accept;

        byte[] arrayByte = username.getBytes();
        int offset = 2 + 1;

        Bits.putInt(this.data, offset, arrayByte.length);
        offset += 4;

        Bytes.addByte(this.data, offset, arrayByte, arrayByte.length);
        offset += arrayByte.length;

        arrayByte = password.getBytes();
        Bits.putInt(this.data, offset, arrayByte.length);
        offset += 4;

        Bytes.addByte(this.data, offset, arrayByte, arrayByte.length);
        this.lengthData = offset + arrayByte.length;
    }

    public Login(byte[] data) {
        super(data);
        this.accept = data[2];

        int offfset = 2 + 1; //3
        int length = Bits.getInt(data, offfset);
        offfset += 4; //7

        this.username = new String(Bytes.copyByte(data, offfset, offfset + length));
        offfset += length; //16

        length = Bits.getInt(data, offfset);
        offfset += 4; //20
        this.password = new String(Bytes.copyByte(data, offfset, offfset + length));
        this.lengthData = offfset + length;
    }

    public String getUsername() {
        return this.username;
    }

    public String getPassword() {
        return this.password;
    }

    public void setAccept(byte accept) {
        this.accept = accept;
        this.data[2] = accept;
    }

    public byte getAccept() {
        return this.accept;
    }

    @Override
    public void printInformation() {

        System.out.println("<----- this ---->");
        System.out.println("Username: " + this.username);
        System.out.println("Password: " + this.password);
        System.out.println("Flag: " + this.flag);
        System.out.println("Type: " + this.type);
        System.out.println("Accept: " + this.accept);
        log.Logger.write("<----- this ---->");


        synchronized (log.Logger.class) {
            log.Logger.write("<----- this ---->");
            log.Logger.write("Username: " + this.username);
            log.Logger.write("Password: " + this.password);
            log.Logger.write("Type: " + this.type);
            log.Logger.write("Flag: " + this.flag);
            log.Logger.write("Accept: " + this.accept);
            log.Logger.write("<----- this ---->");

        }



    }
}
