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
public class Logout extends Packet {

    public Logout() {
        super(Symbol.TYPE_LOGOUT, Symbol.FLAG_REQUEST);
    }

    public Logout(byte flag) {
        super(Symbol.TYPE_LOGOUT, flag);
    }

    public Logout(byte[] data) {
        super(data);
    }

    public Logout(byte type, byte flag) {
        super(type, flag);
    }

    @Override
    public void printInformation() {

        System.out.println("<--------------->");
        System.out.println("Type: " + this.type);
        System.out.println("Flag: " + this.flag);
        System.out.println("<--------------->");

        log.Logger.write("Type: " + this.type);
        log.Logger.write("Flag: " + this.flag);

    }
}
