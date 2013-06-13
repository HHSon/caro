package network.converting;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;
import network.bit.Bits;
import network.bit.Bytes;
import network.packet.ListOnline;
import network.symbol.Symbol;

public class Converting {

    public static byte[] fromObjecttoBytes(Object obj) {
        ByteArrayOutputStream b = new ByteArrayOutputStream();
        try {
            ObjectOutputStream o = new ObjectOutputStream(b);
            o.writeObject(obj);
            byte[] dataObject = b.toByteArray();
            byte[] data = new byte[Symbol.LENGTH_DATA];
            Bits.putInt(data, 0, dataObject.length);
            Bytes.addByte(data, 4, dataObject, dataObject.length);
            return data;

        } catch (Exception ex) {
            Logger.getLogger(Converting.class.getName()).log(Level.SEVERE, null, ex);
            log.Logger.write("ERROR function fromObjecttoByte: " + ex.getMessage());
        }
        return null;
    }

    public static Object fromBytestoObject(byte[] bytes) {

        int length = Bits.getInt(bytes, 0);
        byte[] data = Bytes.copyByte(bytes, 4, 4 + length);
        ByteArrayInputStream b = new ByteArrayInputStream(data);

        ObjectInputStream o;
        try {
            o = new ObjectInputStream(b);
            return o.readObject();
        } catch (IOException | ClassNotFoundException ex) {
            // Logger.getLogger(Converting.class.getName()).log(Level.SEVERE, null, ex);
            log.Logger.write("ERROR function fromBytesToObject: " + ex.getMessage());
        }
        return null;
    }
}

class sdfsdf {

    public static void main(String[] args) {
        ListOnline t = new ListOnline();
        t.addUser("lamhaison");
        t.addUser("rungbiennui");

        byte[] data = Converting.fromObjecttoBytes(t);
        ListOnline b = (ListOnline) Converting.fromBytestoObject(data);
        b.print();
    }
}
