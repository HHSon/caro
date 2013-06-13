package model;

import network.socket.Client;
import network.symbol.Symbol;
import view.FrameManager;


public class Global {
    public static boolean isLogin = false;
    public static String userName = null;
    public static int score;
    public static String contestName = null;
    
    private static Client mainConnection = null;
    
    
    public static void main(String[] args) {
        newMainConnection();
        FrameManager.getInstance().showLoginFrame();
    }
    
    
    public static Client getMainConnection() {
        return mainConnection;
    }
    
    public static void newMainConnection() {
        try {
            mainConnection = new Client(Symbol.IP_SERVER, Symbol.PORT_SERVER);
        } catch (Exception ex) {
            
        }
    }
    
    public static void closeMainConnection() {
        mainConnection.close();
        mainConnection = null;
    }
}