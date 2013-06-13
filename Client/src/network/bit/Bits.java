/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package network.bit;

/**
 *
 * @author LHS
 */
public class Bits {

//Truyền vào byte trả về kiểu dữ liệu    
    public static boolean getBoolean(byte[] b, int off) {
        return b[off] != 0;
    }

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

    static char getChar(byte[] b, int off) {
        return (char) (((b[off + 1] & 0xFF) << 0)
                + ((b[off + 0] & 0xFF) << 8));
    }

    public static short getShort(byte[] b, int off) {
        return (short) (((b[off + 1] & 0xFF) << 0)
                + ((b[off + 0] & 0xFF) << 8));
    }

    public static int getInt(byte[] b, int off) {
        return ((b[off + 3] & 0xFF) << 0)
                + ((b[off + 2] & 0xFF) << 8)
                + ((b[off + 1] & 0xFF) << 16)
                + ((b[off + 0] & 0xFF) << 24);
    }

    public static float getFloat(byte[] b, int off) {
        int i = ((b[off + 3] & 0xFF) << 0)
                + ((b[off + 2] & 0xFF) << 8)
                + ((b[off + 1] & 0xFF) << 16)
                + ((b[off + 0] & 0xFF) << 24);
        return Float.intBitsToFloat(i);
    }

    public static long getLong(byte[] b, int off) {
        return ((b[off + 7] & 0xFFL) << 0)
                + ((b[off + 6] & 0xFFL) << 8)
                + ((b[off + 5] & 0xFFL) << 16)
                + ((b[off + 4] & 0xFFL) << 24)
                + ((b[off + 3] & 0xFFL) << 32)
                + ((b[off + 2] & 0xFFL) << 40)
                + ((b[off + 1] & 0xFFL) << 48)
                + ((b[off + 0] & 0xFFL) << 56);
    }

    public static double getDouble(byte[] b, int off) {
        long j = ((b[off + 7] & 0xFFL) << 0)
                + ((b[off + 6] & 0xFFL) << 8)
                + ((b[off + 5] & 0xFFL) << 16)
                + ((b[off + 4] & 0xFFL) << 24)
                + ((b[off + 3] & 0xFFL) << 32)
                + ((b[off + 2] & 0xFFL) << 40)
                + ((b[off + 1] & 0xFFL) << 48)
                + ((b[off + 0] & 0xFFL) << 56);
        return Double.longBitsToDouble(j);
    }

//Chuyển kiểu dữ liệu khác nhau thành byte    
    public static void putBoolean(byte[] b, int off, boolean val) {
        b[off] = (byte) (val ? 1 : 0);
    }

    public static void putChar(byte[] b, int off, char val) {
        b[off + 1] = (byte) (val >>> 0);
        b[off + 0] = (byte) (val >>> 8);
    }

    public static void putShort(byte[] b, int off, short val) {
        b[off + 1] = (byte) (val >>> 0);
        b[off + 0] = (byte) (val >>> 8);
    }

    public static void putInt(byte[] b, int off, int val) {
        b[off + 3] = (byte) (val >>> 0);
        b[off + 2] = (byte) (val >>> 8);
        b[off + 1] = (byte) (val >>> 16);
        b[off + 0] = (byte) (val >>> 24);
    }

    public static void putFloat(byte[] b, int off, float val) {
        int i = Float.floatToIntBits(val);
        b[off + 3] = (byte) (i >>> 0);
        b[off + 2] = (byte) (i >>> 8);
        b[off + 1] = (byte) (i >>> 16);
        b[off + 0] = (byte) (i >>> 24);
    }

    public static void putLong(byte[] b, int off, long val) {
        b[off + 7] = (byte) (val >>> 0);
        b[off + 6] = (byte) (val >>> 8);
        b[off + 5] = (byte) (val >>> 16);
        b[off + 4] = (byte) (val >>> 24);
        b[off + 3] = (byte) (val >>> 32);
        b[off + 2] = (byte) (val >>> 40);
        b[off + 1] = (byte) (val >>> 48);
        b[off + 0] = (byte) (val >>> 56);
    }

    public static void putDouble(byte[] b, int off, double val) {
        long j = Double.doubleToLongBits(val);
        b[off + 7] = (byte) (j >>> 0);
        b[off + 6] = (byte) (j >>> 8);
        b[off + 5] = (byte) (j >>> 16);
        b[off + 4] = (byte) (j >>> 24);
        b[off + 3] = (byte) (j >>> 32);
        b[off + 2] = (byte) (j >>> 40);
        b[off + 1] = (byte) (j >>> 48);
        b[off + 0] = (byte) (j >>> 56);
    }
}
