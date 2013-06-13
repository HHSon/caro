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
import java.net.Socket;
import java.net.SocketException;
import java.util.logging.Level;
import java.util.logging.Logger;
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

    private void createIO() {
        try {
            this.in = new DataInputStream(this.socket.getInputStream());
            this.out = new DataOutputStream(this.socket.getOutputStream());
        } catch (IOException ex) {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
            log.Logger.write("Function new IO: " + ex);
            return;
        }
        log.Logger.write("Successfully new IO");
    }

    public Client(Socket socket) {
        this.socket = socket;
        this.createIO();
    }

    public Client(Socket socket, int timeout) {
        this.socket = socket;
        this.createIO();
        this.setTimeOut(timeout);
    }

    public Client(String ip, int port) throws Exception {

        try {
            this.socket = new Socket(ip, port);
        } catch (Exception e) {
            log.Logger.write("Can't connect server " + ip + ":" + port);
            throw new Exception(e);
        }

        log.Logger.write("Successfully connection to server:" + ip + ":" + port);
        this.createIO();
    }

    public Client(String ip, int port, int timout) {
        try {
            this.socket = new Socket(ip, port);
        } catch (Exception e) {
            log.Logger.write("Can't connect server " + ip + ":" + port);
            return;
        }

        log.Logger.write("Successfully connection to server:" + ip + ":" + port + ",TIMEOUT: " + timout);
        this.createIO();
        this.setTimeOut(timout);
    }

    public boolean sendByte(byte[] data) {
        try {
            out.write(data, 0, data.length);
        } catch (Exception ex) {
            log.Logger.write("Can't send pkt to " + socket.getRemoteSocketAddress() + " " + ex);
        }

        log.Logger.write("Sucessfully send pkt to " + socket.getRemoteSocketAddress());
        return true;
    }

    public byte[] rcvByte() throws IOException {
        byte[] data = new byte[LENGTH_DATA];
        try {
            this.in.read(data);
        } catch (Exception e) {
            //  log.Logger.write("Successfully rcv pkt to " + socket.getRemoteSocketAddress());
            throw new IOException(e.getMessage());
        }
        log.Logger.write("Successfully rcv pkt to " + socket.getRemoteSocketAddress());
        return data;
    }

    public byte[] rcvByte(int length) throws IOException {
        byte[] data;
        data = new byte[length];
        try {
            this.in.read(data);
        } catch (Exception e) {
            //  log.Logger.write("Successfully rcv pkt to " + socket.getRemoteSocketAddress());
            throw new IOException(e.getMessage());
        }
        log.Logger.write("Successfully rcv pkt to " + socket.getRemoteSocketAddress());
        return data;
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

    public final void setTimeOut(int timeout) {
        try {
            this.socket.setSoTimeout(timeout);
        } catch (SocketException ex) {
            log.Logger.write("Function: setTimeOut(int timeout): Can't set timeout " + timeout);
            return;
        }
        log.Logger.write("Function: setTimeOut(int timeout): Sucessfully set timeout " + timeout);
    }

    public void close() {
        try {
            if (!socket.isClosed()) {
                socket.close();
            }
            in.close();
            out.close();

        } catch (IOException ex) {
            log.Logger.write("Function: Close(): Can't close IO " + ex);
            return;
        }
        log.Logger.write("Function: Close(): Succesfully close IO");
    }
}

class RunClient {

    public static void main(String[] args) {
        Client client = new Client("127.0.0.1", 5000, 100);
    }
}
