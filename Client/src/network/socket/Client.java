/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package network.socket;

/**
 *
 * @author LHS
 */
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.net.Socket;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.IIOException;
import network.symbol.Symbol;

//import java.lang.ArrayIndexOutOfBoundsException;
/**
 *
 * @author LHS
 */
public class Client {

    private Socket socket;
    public static final int LENGTH_DATA = Symbol.LENGTH_DATA;
    DataInputStream in;
    DataOutputStream out;
    ObjectOutputStream objOut;
    ObjectInputStream objIn;

    public void createIOObject() {
        try {
            objOut = new ObjectOutputStream(socket.getOutputStream());
            objIn = new ObjectInputStream(socket.getInputStream());
        } catch (IOException ex) {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public Client(Socket socket) throws IOException {
        this.socket = socket;
        this.in = new DataInputStream(this.socket.getInputStream());
        this.out = new DataOutputStream(this.socket.getOutputStream());
        // objOut = new ObjectOutputStream(this.socket.getOutputStream());
        // objIn = new ObjectInputStream(this.socket.getInputStream());
    }

    public Client(Socket socket, int timeout) throws IOException {
        this.socket = socket;
        this.socket.setSoTimeout(timeout);
        this.in = new DataInputStream(this.socket.getInputStream());
        this.out = new DataOutputStream(this.socket.getOutputStream());
        // objOut = new ObjectOutputStream(socket.getOutputStream());
        // objIn = new ObjectInputStream(socket.getInputStream());
    }

    public Client(String ip, int port) throws UnknownHostException, IOException {

        try {
            this.socket = new Socket(ip, port);
        } catch (Exception e) {
            log.Logger.write("Can't connect server");
            return;
        }

        log.Logger.write("Successfully connection to server:" + ip + ":" + port);
        this.in = new DataInputStream(this.socket.getInputStream());
        this.out = new DataOutputStream(this.socket.getOutputStream());
        
        // objOut = new ObjectOutputStream(socket.getOutputStream());
        // objIn = new ObjectInputStream(socket.getInputStream());
        // this.createIOObject();

    }

    public Client(String ip, int port, int timout) throws UnknownHostException, IOException {
        try {
            this.socket = new Socket(ip, port);
        } catch (Exception e) {
            log.Logger.write("Can't connect server");
            return;
        }

        log.Logger.write("Successfully connection to server:" + ip + ":" + port + ",TIMEOUT: " + timout);

        this.socket.setSoTimeout(timout);
        //objOut = new ObjectOutputStream(socket.getOutputStream());
        //objIn = new ObjectInputStream(socket.getInputStream());

        //     this.in = new DataInputStream(this.socket.getInputStream());
        //    this.out = new DataOutputStream(this.socket.getOutputStream());

    }

    public void sendByte(byte[] data) throws IOException {
        out.write(data, 0, data.length);
    }

    public byte[] rcvByte() throws IOException {
        byte[] data = new byte[LENGTH_DATA];
        this.in.read(data);
        return data;
    }

    public boolean sendObject(Object object) {
        try {
            objOut = new ObjectOutputStream(this.socket.getOutputStream());
        } catch (IOException ex) {
            //  Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
            log.Logger.write("ERROR function sendObject-new objOut: socket: " + this.getInformationConnection() + " " + ex.getMessage());
            return false;
        }


        if (object == null) {
            log.Logger.write("ERROR Function sendObjcet to: object null");
            return false;
        }

        try {
            objOut.writeObject(object);
            log.Logger.write("Sucessfully Function sendObjcet to: " + socket.getRemoteSocketAddress().toString());

        } catch (IOException ex) {
            log.Logger.write("ERROR Function sendObjcet-write to: " + socket.getRemoteSocketAddress().toString() + " " + ex.getMessage());
            return false;
        }

//        try {
//            objOut.close();
//        } catch (IOException ex) {
//            log.Logger.write("ERROR function sendObject-close objOut: socket" + this.getInformationConnection() + " " + ex.getMessage());
//            //Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
//        }

        return true;
    }

    public Object rcvObjcet() throws IOException {

        Object object = null;
        try {
            objIn = new ObjectInputStream(this.socket.getInputStream());
        } catch (IOException ex) {
            //  Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
//            if (!ex.getMessage().equals("Read timed out")) {
//                log.Logger.write("ERROR function rcvObjcet-new objIn: socket: " + this.getInformationConnection() + " " + ex.getMessage());
//            }
            throw new IIOException(ex.getMessage());

        }


        try {
            object = this.objIn.readObject();

        } catch (IOException | ClassNotFoundException ex) {
            //  Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);

            if (!ex.getMessage().equals("Read timed out")) {
                log.Logger.write("ERROR Function rcvObjcet-read from: " + socket.getRemoteSocketAddress().toString() + " " + ex.getMessage());
            }

            throw new IOException(ex.getMessage());

        }

//        try {
//            objIn.close();
//        } catch (IOException ex) {
//            // Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
//            log.Logger.write("ERROR function rcvObjcet-close objIn: socket" + this.getInformationConnection() + " " + ex.getMessage());
//            throw new ExportException(ex.getMessage());
//        }

        log.Logger.write("Sucessfully Function rcvObjcet from: " + socket.getRemoteSocketAddress().toString());
        return object;
    }

    public String getAdd() {
        return this.socket.getInetAddress().getHostName();
    }

    public int getLocalPort() {
        return this.socket.getLocalPort();
    }

    public Socket getSocket() {
        return socket;
    }

    public boolean isCloset() {
        return this.socket.isClosed();
    }

    public String getInformationConnection() {
        return this.getSocket().getRemoteSocketAddress().toString();
    }

    public void setTimeOut(int timeout) {
        try {
            this.socket.setSoTimeout(timeout);
        } catch (SocketException ex) {
            ///Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
            log.Logger.write("Can't set timeout " + timeout);
        }

        log.Logger.write("Sucessfully set timeout " + timeout);
    }

    public void close() throws IOException {
        //  in.close();
        //  out.close();
        this.objIn.close();
        this.objOut.close();
        socket.close();

    }
}
