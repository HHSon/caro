/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package network.bit;


/**
 *
 * @author LHS
 */
public class Bytes {
    //Nối thêm 1 chuỗi addb vào trong sourceb

    public static void addByte(byte[] sourceb, int off, byte[] addb, int length) {
        for (int i = 0; i < length; i++) {
            sourceb[off] = addb[i];
            off++;
        }

    }
    //Copy mảng byte

    public static byte[] copyByte(byte[] source, int soff, int eoff) {
        byte[] copyb = new byte[eoff - soff];
        int j = 0;
        for (int i = soff; i < eoff; i++) {
            copyb[j] = source[i];
            j++;
        }
        return copyb;
    }
}