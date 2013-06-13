/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package network.socket;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.SocketException;
import java.net.UnknownHostException;
import network.packet.UDPMessage;
import network.packet.UDPPacket;
import network.packet.UDPPoint;
import network.symbol.Symbol;

/**
 *
 * @author LHS
 */
public class UDPSocket {

    public DatagramSocket socket = null;

    //Mở 1 UDP socket tại 1 port trống
    public UDPSocket() throws SocketException {
        this.socket = new DatagramSocket();
    }

    //Tạo 1 UDP socket tại 1 port chỉ định
    public UDPSocket(int port) throws SocketException {
        this.socket = new DatagramSocket(port);
    }

    //Send tới 1 địa chỉ
    //data = dữ liệu cần truyển đi(byte)
    //ip InetAddress( tạo từ chuỗi String IP)
    //port = port bên socket nhận
    //Sinh ra lỗi khi không gửi được packet
    public void sendAnycast(byte[] data, InetAddress ip, int port) throws IOException {
        DatagramPacket datapk = new DatagramPacket(data, data.length, ip, port);
        this.socket.send(datapk);
    }

    //Chuỗi IP là String
    //Sinh ra lỗi khi không gửi được packet
    public void sendAnycast(byte[] data, String ip, int port) throws IOException {
        InetAddress inetAddress = null;
        try {
            inetAddress = InetAddress.getByName(ip);
        } catch (UnknownHostException ex) {
            log.Logger.write("sendAnycaset: Can't change to InetAddress: " + ip + ": " + port);
            return;
        }

        DatagramPacket datapk = new DatagramPacket(data, data.length, inetAddress, port);
        this.socket.send(datapk);

    }

    public boolean sendAnycast(byte[] data, String ip, int port, boolean reliable) {
        InetAddress inetAddress = null;
        try {
            inetAddress = InetAddress.getByName(ip);
        } catch (UnknownHostException ex) {
            log.Logger.write("sendAnycaset reliable: Can't change to InetAddress: " + ip + ": " + port);
            return false;
        }

        DatagramPacket datapk = new DatagramPacket(data, data.length, inetAddress, port);
        DatagramPacket ackPkt = null;
        for (int i = 0; i < Symbol.RESEND; i++) {

            try {
                this.socket.send(datapk);
            } catch (IOException ex) {
                //Logger.getLogger(UDPSocket.class.getName()).log(Level.SEVERE, null, ex);
                log.Logger.write("Can't send pkt to: " + ip + ":" + port);
                continue;
            }

            log.Logger.write("Successfully  send pkt to: " + ip + ":" + port);

            ackPkt = this.recievePacket(Symbol.TIMEOUT_UDP, 1);
            if (ackPkt == null) {
                log.Logger.write("Can't rcv ACK from: " + datapk.getAddress().getHostAddress() + " port: " + datapk.getPort());
                continue;
            }

            if (ackPkt.getData()[0] == Symbol.ACK) {
                log.Logger.write("Sucessfully rcv ACK: " + ip + ":" + port);
                return true;
            } else {
                log.Logger.write("Time out rcv ACK: " + ip + ":" + port);
                continue;
            }
        }

        return false;
    }

    //Send tới toàn mạng
    //Sinh ra lỗi khi không gửi được packet
    public void sendBroadcast(byte[] data, int port) throws IOException {
        InetAddress ip = null;
        try {
            ip = InetAddress.getByName("255.255.255.255");
        } catch (UnknownHostException ex) {
            System.out.println("Can't change to InetAddress Broadcast");
            return;
        }
        DatagramPacket datapk = new DatagramPacket(data, data.length, ip, port);
        this.socket.send(datapk);
    }

    //CHỜ NHẬN TRONG 1 KHOẢNG THỜI GIAN TIMEOUT
    //Chỉ định số byte nhận SIZEDATA
    //Nhận dữ liệu trả về dữ liệu là 1 DatagramPacket
    //SINH RA LỖI KHI return về NULL
    public DatagramPacket recievePacket(int timeout, int sizeData) {
        byte[] data = new byte[sizeData];
        DatagramPacket datapk = new DatagramPacket(data, data.length);
        try {
            this.socket.setSoTimeout(timeout);
            this.socket.receive(datapk);
        } catch (Exception e) {
            log.Logger.write("recievePacket not reliable: Can't recieve pkt");
            return null;
        }

        log.Logger.write("recievePacket not reliable: Sucessfully recieve pkt form:" + datapk.getAddress().getHostAddress() + " port: " + datapk.getPort());
        return datapk;
    }

    public DatagramPacket recievePacket(int timeout, int sizeData, boolean reliable) {

        byte[] data = new byte[sizeData];
        DatagramPacket datapk = new DatagramPacket(data, data.length);
        try {
            this.socket.setSoTimeout(timeout);
            this.socket.receive(datapk);
        } catch (Exception e) {
            log.Logger.write("recievePacket reliable: Can't recieve pkt");
            return null;
        }

        log.Logger.write("recievePacket not reliable: Sucessfully recieve pkt form:" + datapk.getAddress().getHostAddress() + " port: " + datapk.getPort());

        byte[] ack = new byte[1];
        ack[0] = 1;
        try {
            this.sendAnycast(ack, datapk.getAddress(), datapk.getPort());
            log.Logger.write("recievePkt reliable: Successfully Send ack to: " + datapk.getAddress().getHostAddress() + " port: " + datapk.getPort());
        } catch (IOException ex) {
            // Logger.getLogger(UDPSocket.class.getName()).log(Level.SEVERE, null, ex);
            log.Logger.write("recievePkt reliable: Can't Send ack to: " + datapk.getAddress().getHostAddress() + " port: " + datapk.getPort());
        }
        return datapk;
    }

    //Không chỉ định SIZEDATA
    public DatagramPacket recievePacket(int timeout) {
        byte[] data = new byte[timeout];
        DatagramPacket datapk = new DatagramPacket(data, data.length);
        try {
            this.socket.setSoTimeout(timeout);
            this.socket.receive(datapk);
        } catch (Exception e) {
            return null;
        }



        return datapk;
    }

    int getPort() {
        return this.socket.getLocalPort();
    }

    //Đóng kết nối nếu chưa được đóng
    public void close() {

        //Nếu chưa được đóng
        if (!this.socket.isClosed()) {
            this.socket.close();
        }
    }
}

class sfsdfsdf {

    public static void main(String[] args) {

        ServerSocket server = null;
        try {
            server = new ServerSocket(5000);
        } catch (IOException ex) {
            //Logger.getLogger(sfsdfsdf.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("Server bi loi");
            return;
        }
        Client t = null;
        try {
            t = new Client(server.accept());
        } catch (IOException ex) {
            // Logger.getLogger(sfsdfsdf.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("Khong tao duoc socket");
            return;

        }

        System.out.println("Connect to server: " + t.getInformationConnection());
        while (true) {

            UDPPacket pkt;
            try {
                pkt = UDPPacket.buildUDPPacket(t.rcvByte());
            } catch (IOException ex) {
                //Logger.getLogger(sfsdfsdf.class.getName()).log(Level.SEVERE, null, ex);
                System.out.println("Khong nhan duoc du lieu");
                continue;

            }
            if (pkt instanceof UDPMessage) {
                System.out.println("udp message");
                UDPMessage message = (UDPMessage) pkt;
                System.out.println(message.getUsername());

                System.out.println(message.getMessage());

            } else {
                System.out.println("udp point");
                if (pkt instanceof UDPPoint) {
                    UDPPoint point = (UDPPoint) pkt;
                    System.out.println(point.getUsername());
                    System.out.println(point.getX());
                    System.out.println(point.getY());
                }
            }

        }


//         UDPSocket socket = new UDPSocket(5200);
//        while (true) {
//            UDPPacket pkt = UDPPacket.buildUDPPacket(socket.recievePacket(500000, 1500, true).getData());
//            if (pkt instanceof UDPMessage) {
//                System.out.println("udp message");
//                UDPMessage message = (UDPMessage) pkt;
//                System.out.println(message.getUsername());
//
//                System.out.println(message.getMessage());
//
//            } else {
//                if (pkt instanceof UDPPoint) {
//                    UDPPoint point = (UDPPoint) pkt;
//                    System.out.println(point.getUsername());
//                    System.out.println(point.getX());
//                    System.out.println(point.getY());
//                }
//            }
//        }

    }
}
