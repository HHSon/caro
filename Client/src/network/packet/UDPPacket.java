/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package network.packet;

import network.bit.*;
import network.symbol.Symbol;

/**
 *
 * @author LHS
 */
public abstract class UDPPacket {

    protected byte type;
    protected String username;
    protected int lengHeader;
    protected byte[] data;

    public UDPPacket() {
    }

    public UDPPacket(byte type, String username) {
        this.type = type;
        this.username = username;
        byte[] bytes = username.getBytes();
        this.lengHeader = bytes.length + 1 + 4;
        this.data = new byte[Symbol.LENGTH_DATA];
        data[0] = type;
        Bits.putInt(data, 1, lengHeader);
        Bytes.addByte(data, 5, bytes, bytes.length);
    }

    public UDPPacket(byte[] data) {
        this.data = data;
        this.type = data[0];
        this.lengHeader = Bits.getInt(data, 1);
        this.username = new String(Bytes.copyByte(data, 5, lengHeader));
    }

    public int getLengHeader() {
        return lengHeader;
    }

    public byte getType() {
        return type;
    }

    public String getUsername() {
        return username;
    }

    public void setLengHeader(int lengHeader) {
        this.lengHeader = lengHeader;
    }

    public void setType(byte type) {
        this.type = type;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public byte[] getData() {
        return data;
    }

    public void setData(byte[] data) {
        this.data = data;
    }
    
    

    public abstract byte[] createData();

    public abstract UDPPacket createPkt();

    public static UDPPacket buildUDPPacket(byte[] data) {
        if (data[0] == Symbol.TYPE_POINT) {
            return new UDPPoint(data).createPkt();
        } else {
            return new UDPMessage(data).createPkt();
        }
    }
}
