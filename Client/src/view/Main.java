package view;

import java.io.IOException;
import java.net.UnknownHostException;
import network.socket.Client;
import network.symbol.Symbol;


public class Main {

    public static Client mainConnection;

    public static void main(String[] args) {
        try {
            mainConnection = new Client(Symbol.IP_SERVER, Symbol.PORT_SERVER);
        } catch (UnknownHostException ex) {
            System.out.println(ex.getMessage());
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
       
        //FrameManager.getInstance().showLoginFrame();
        FrameManager.getInstance().showMainFrame();
    }
}
