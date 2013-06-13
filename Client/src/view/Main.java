package view;

import network.socket.Client;
import network.symbol.Symbol;


public class Main {

    public static Client mainConnection;

    public static void main(String[] args) {
        try {
            mainConnection = new Client(Symbol.IP_SERVER, Symbol.PORT_SERVER);
        } catch (Exception ex) {
            //TODO:
        }
        
        FrameManager.getInstance().showLoginFrame();
        //FrameManager.getInstance().showMainFrame();
    }
}
