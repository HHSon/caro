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
 */
public class UDPMessage extends UDPPacket {

    private String message;
    private int lengthData;

    public UDPMessage(byte type, String username, String message) {
        super(type, username);
        this.message = message;
        byte[] temp = message.getBytes();
        this.lengthData = temp.length + this.lengHeader + 4;

        Bits.putInt(this.data, this.lengHeader, lengthData);
        Bytes.addByte(this.data, this.lengHeader + 4, temp, temp.length);

    }

    public UDPMessage(byte[] data) {
        super(data);
        this.lengthData = Bits.getInt(this.data, this.lengHeader);
        this.message = new String(Bytes.copyByte(this.data, this.lengHeader + 4, this.lengthData));
    }

    public String getMessage() {
        return message;
    }

    public int getLengthData() {
        return lengthData;
    }

    public void setLengthData(int lengthData) {
        this.lengthData = lengthData;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public byte[] createData() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public UDPPacket createPkt() {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}

//class fsdfsfsd {
//
//    public static void main(String[] args) {
//        UDPPacket message = new UDPMessage(Symbol.TYPE_MESSAGE, "lamhaisosdfsfsfdfsfsdfdsfsfsn", "lamhdsfdsfsdfsdfsdfaison");
//        UDPMessage dfsdfsdf = new UDPMessage(message.createData());
//    }
//}
