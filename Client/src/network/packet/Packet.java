/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package network.packet;

import network.symbol.Symbol;

/**
 *
 * @author LHS
 */
public abstract class Packet {

    protected byte type;    //byte 0
    protected byte flag;    // byte 1
    protected byte[] data;
    protected int lengthData;

    public Packet() {
    }

    //Từ các trường tạo ra data
    public Packet(byte type, byte flag) {
        this.type = type;
        this.flag = flag;
        this.data = new byte[Symbol.LENGTH_DATA];

        this.data[0] = type;
        this.data[1] = flag;
    }

    public Packet(byte[] data) {
        this.data = data;
        this.type = data[0];
        this.flag = data[1];
    }

    public void setType(byte type) {
        this.type = type;
        this.data[0] = type;
    }

    public void setFlag(byte flag) {
        this.flag = flag;
        this.data[1] = flag;
    }

    public byte getFlag() {
        return this.flag;
    }

    public byte[] getData() {
        return this.data;
    }

    public byte getType() {
        return type;
    }

    public byte[] createData() {
        return this.data;
    }

    public Packet createPkt() {
        return this;
    }

    public abstract void printInformation();

    public static Packet buildUDPPacket(byte[] data) {

        if (data[0] == Symbol.TYPE_SIGNUP) {
            return new Registry(data);
        }

        if (data[0] == Symbol.TYPE_LOGIN) {
            return new Login(data);
        }

        if (data[0] == Symbol.TIMEOUT_LOGIN) {
            return new Logout(data);
        }

        if (data[0] == Symbol.TYPE_STATUS) {
            return new Status(data);
        }

        if (data[0] == Symbol.TYPE_LIST_ONLINE) {
            return new ListOnline(data);
        }

        return null;
    }
}