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
public class Registry extends Login {

    private String fullname;

    /**
     *
     * @param username
     * @param password
     * @param fullname
     * @param type
     * @param flag
     * @param accept
     */
    public Registry(String username, String password, String fullname, byte type, byte flag, byte accept) {
        super(username, password, type, flag, accept);

        this.fullname = fullname;
        byte[] arrayByte = fullname.getBytes();
        int offset = this.lengthData;

        Bits.putInt(this.data, offset, arrayByte.length);
        offset += 4;

        Bytes.addByte(this.data, offset, arrayByte, arrayByte.length);
        offset += arrayByte.length;
        this.lengthData = offset;
    }

    public Registry(byte[] data) {
        super(data);
        int offset = this.lengthData;
        int length = Bits.getInt(this.data, offset);
        offset += 4;
        this.fullname = new String(Bytes.copyByte(this.data, offset, offset + length));
        this.lengthData = offset + length;

    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    @Override
    public void printInformation() {

        System.out.println("Fullname: " + this.fullname);
        synchronized (log.Logger.class) {
            log.Logger.write("<----- this ---->");
            super.printInformation();
            log.Logger.write("Fullname: " + this.fullname);

        }

    }
}
