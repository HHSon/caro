/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package network.packet;

import java.io.Serializable;

/**
 *
 * @author LHS
 */
public abstract class Packet implements Serializable {
    //private byte type;
    protected byte flag;

    public byte getFlag() {
        return flag;
    }

    public void setFlag(byte flag) {
        this.flag = flag;
    }
}
