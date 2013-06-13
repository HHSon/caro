/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package network.packet;

import java.util.Vector;
import network.bit.Bytes;
import network.symbol.Symbol;

/**
 *
 * @author LHS
 */
public class ListOnline extends Packet {

    Vector<String> listUser;

    public ListOnline(byte flag) {
        super(Symbol.TYPE_LIST_ONLINE, flag);
        this.listUser = new Vector<>();
    }

    public ListOnline(Vector<String> listUser) {
        super(Symbol.TYPE_LIST_ONLINE, Symbol.FLAG_REPLAY);
        this.listUser = listUser;

        int offset = 1;
        for (int i = 0; i < this.listUser.size(); i++) {
            byte[] arrayByte = this.listUser.get(i).getBytes();
            this.data[offset + 1] = (byte) arrayByte.length;
            offset += 1;
            Bytes.addByte(data, offset + 1, arrayByte, arrayByte.length);
            offset += arrayByte.length;
        }

        this.data[offset + 1] = -1;
    }

    public ListOnline(byte[] data) {
        super(data);
        listUser = new Vector<>();

        //Nếu là gói tin request
        if (this.flag == Symbol.FLAG_REQUEST) {
            return;
        }

        int offset = 2;
        while (true) {
            int length = (int) data[offset];
            if (length == -1) {
                return;
            }

            offset += 1;

            this.listUser.add(new String(Bytes.copyByte(data, offset, offset + length)));
            offset += length;
        }
    }

    public ListOnline() {
        listUser = new Vector<>();
    }

    public Vector<String> getListUser() {
        return listUser;
    }

    public void addUser(String username) {

        if (this.searchUser(username) == -1) {
            this.listUser.add(username);
        }
    }

    public void getUser(int index) {
        this.listUser.get(index);
    }

    public int searchUser(String username) {
        for (int i = 0; i < this.listUser.size(); i++) {
            if (this.listUser.get(i).equals(username)) {
                return i;
            }
        }

        return -1;
    }

    public void removeUser(String username) {
        this.listUser.remove(username);
    }

    public void removeUser(int index) {
        this.listUser.remove(index);
    }

    public void print() {
        for (int i = 0; i < this.listUser.size(); i++) {
            System.out.println(this.listUser.get(i));
        }
    }

    @Override
    public void printInformation() {
        for (int i = 0; i < this.listUser.size(); i++) {
            System.out.println(this.listUser.get(i));
        }
    }
}

//class sdfsdfds {
//
//    public static void main(String[] args) {
//
//
//        Vector<String> t = new Vector<>();
//        for (int i = 0; i < 100; i++) {
//            t.add("lamhaison" + i);
//        }
//        t.add("lamhaison");
//        t.add("rungbiennui");
//        t.add(("demo"));
//
//        ListOnline list = new ListOnline(t);
//        list = new ListOnline(Symbol.FLAG_REQUEST);
//        new ListOnline(list.createData()).print();
//    }
//}
