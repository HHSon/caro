/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package network.symbol;

/**
 *
 * @author LHS
 */
public class Symbol {

    public static final int PORT_SERVER = 5000;
    public static final String IP_SERVER = "192.168.1.100";


    public static final byte FLAG_REQUEST = 3;
    public static final byte FLAG_REPLAY = 4;
    public static final byte FLAG_ONLINE = 1;

    public static final byte ACK = 7;
    public static final byte LOSS = 8;
    public static final int RESEND = 2;
    public static final int DEFAULT_SCORE = 5000;
    public static final int STATE_UNKNOWN = 9;
    public static final int STATE_SIGN_UP = 10;
    public static final int STATE_LOGIN = 11;
    public static final int STATE_RELAXING = 12;
    public static final int STATE_PLAYING = 13;
    public static final int MAX_THREAD = 1000;
    public static final int TIMEOUT_SERVER = 10;
    public static final int TIMEOUT_CLIENT = 10;
    public static final int LENGTH_DATA = 1500;
    public static final int TIMEOUT_UDP = 500;
    public static final int TIMEOUT_RECIEVER = 50;
    public static final int TIMEOUT_ACCEPT = 8;
    public static final int TIMEOUT_LOGIN = 500;
    public static final int TIMEOUT_SIGN_UP = 500;
    
    
    public static final byte TYPE_MESSAGE = 1;
    public static final byte TYPE_POINT = 2;
    
    public static final byte TYPE_SIGNUP = 50;
    public static final byte TYPE_LOGIN = 51;
    public static final byte TYPE_LOGOUT = 52;
    public static final byte TYPE_STATUS = 53;
    public static final byte TYPE_LIST_ONLINE = 54;
    
    
    public static final byte ACCEPT_TRUE = 1;
    public static final byte ACCEPT_FALASE = 0;
    public static final int WAITTING_REQUEST_LOGIN = 5;
    public static final int WAITTING_REQUEST_SIGNUP = 5;
    
    public static final byte STATE_ONLINE = 14;
    public static final byte STATE_OFFLINE = 15;
    
    
    

}