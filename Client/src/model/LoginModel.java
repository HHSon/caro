package model;

import java.io.IOException;
import javax.swing.JOptionPane;
import network.packet.Login;
import network.packet.Packet;
import network.symbol.Symbol;
import view.FrameManager;
import view.LoginFrame;
import view.Main;

public class LoginModel {
    private LoginFrame loginFrame;
    private boolean isLoginFalse;
    private String errorString;
    
    
    public LoginModel(LoginFrame loginFrame) {
        if (loginFrame == null) {
            throw new NullPointerException("loginFrame phải khác null");
        }
        this.loginFrame = loginFrame;
        this.isLoginFalse = true;
    }
    
    public boolean isLoginFalse() {
        return isLoginFalse;
    }
    
    public String getErrorString() {
        return errorString;
    }
    
    public void login() {
        if (loginFrame.checkInputData() == false) {
            return;
        }
        String username = loginFrame.getUserName();
        String password = loginFrame.getPassword();
        
        Login pktLogin = new Login(username, password, Symbol.TYPE_LOGIN, Symbol.FLAG_REQUEST, Symbol.ACCEPT_FALASE);
        Main.mainConnection.sendByte(pktLogin.createData());

        network.packet.Packet pkt = null;
      //  Main.mainConnection.setTimeOut(Symbol.TIMEOUT_LOGIN);

        for (int i = 0; i < Symbol.WAITTING_REQUEST_LOGIN; i++) {
            try {
                pkt = Packet.buildUDPPacket(Main.mainConnection.rcvByte());
                break;
            } catch (IOException ex) {
                if (i == Symbol.WAITTING_REQUEST_LOGIN - 1) {
                    log.Logger.write("Khong nhan duoc goi tin phan hoi tu server." + ex);
                    FrameManager.showMessageBox(
                            "Server đang có lỗi hoặc do đường truyền của bạn quá chậm "
                            + ". Xin vui lòng bạn hãy thử lại sau",
                            "Đăng nhập thất bại",
                            JOptionPane.ERROR_MESSAGE);
                    return;
                }
            }
        }

        log.Logger.write("Nhan duoc goi tin phan hoi tu server.");

        if (pkt != null && pkt instanceof Login) {
            Login loginPkt = (Login) pkt;
            if (loginPkt.getUsername().equals(username) && loginPkt.getPassword().equals(password)
                    && loginPkt.getFlag() == Symbol.FLAG_REPLAY && loginPkt.getAccept() == Symbol.ACCEPT_TRUE) {
                //dang nhap
                log.Logger.write("Dang nhap thanh cong voi ten tai khoan: " + pktLogin.getUsername());
                FrameManager.getInstance().disposeAllFrame();
                FrameManager.getInstance().showMainFrame();
            } else {
                log.Logger.write("Tai khoan cung cap khong dung.");
                FrameManager.showMessageBox(
                        "Tên đăng nhập hoặc mật khẩu không đúng",
                        "Lỗi",
                        JOptionPane.ERROR_MESSAGE);
            }
        }
        
        Main.mainConnection.setTimeOut(0);
    }
}