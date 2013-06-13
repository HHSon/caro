package model;

import java.io.IOException;
import javax.swing.JOptionPane;
import network.packet.Packet;
import network.packet.Registry;
import network.symbol.Symbol;
import view.FrameManager;
import view.Main;
import view.SignUpFrame;


public class SignUpModel {
    
    private String signUpErrorMessage;
    private boolean isSignUpError;
    private SignUpFrame signUpFrame;
    
    
    public SignUpModel(SignUpFrame signUpFrame) {
        if (signUpFrame == null) {
            throw new NullPointerException();
        }
        this.signUpFrame = signUpFrame;
        
        isSignUpError = true;
    }
    
    public void SignUp() {
        if (signUpFrame.checkInputData() == false) {
            return;
        }
        
        String userName = signUpFrame.getUserName();
        String password = signUpFrame.getFullName();
        String fullName = signUpFrame.getPassword();
        
        
        Registry pktSend = new Registry(userName, password, fullName,
                Symbol.TYPE_SIGNUP, Symbol.FLAG_REQUEST, Symbol.ACCEPT_FALASE);
        pktSend.printInformation();
        Main.mainConnection.sendByte(pktSend.createData());
        Main.mainConnection.setTimeOut(Symbol.TIMEOUT_SIGN_UP);

        Packet pkt = null;
        for (int i = 0; i < Symbol.WAITTING_REQUEST_SIGNUP; i++) {
            try {
                pkt = Packet.buildUDPPacket(Main.mainConnection.rcvByte());
                break;

            } catch (IOException ex) {
                if (i == Symbol.WAITTING_REQUEST_SIGNUP - 1) {
                    // Logger.getLogger(SignUpFrame.class.getName()).log(Level.SEVERE, null, ex);
                    log.Logger.write("Dang ky ko thanh cong voi ten tai khoan: " + pktSend.getUsername() + " " + ex.getMessage());
                    FrameManager.showMessageBox(
                            "Server đang có lỗi hoặc do đường truyền của bạn quá chậm "
                            + ". Xin vui lòng bạn hãy thử lại sau",
                            "Đăng kí thất bại",
                            JOptionPane.ERROR_MESSAGE);
                    return;
                }
            }
        }

        //pkt.printInformation();

        if (pkt != null && pkt instanceof Registry) {
            Registry pktRegistry = (Registry) pkt;

            System.out.println(pkt.getData()[2]);
            if (pktRegistry.getUsername().equals(userName) && pktRegistry.getPassword().equals(password)
                    && pktRegistry.getFullname().equals(fullName)
                    && pktRegistry.getFlag() == Symbol.FLAG_REPLAY && pktRegistry.getAccept() == Symbol.ACCEPT_TRUE) {
                //dang nhap
                FrameManager.showMessageBox(
                        "Bạn đã đăng kí thành công với tài khoản " + userName
                        + ". Giờ bạn có thể đăng nhập với tài khoản này",
                        "Đăng kí thành công",
                        JOptionPane.INFORMATION_MESSAGE);

                FrameManager.getInstance().disposeSignUpFrame();
                log.Logger.write("Dang ky thanh cong voi ten tai khoan: " + pktRegistry.getUsername());
            } else {
                FrameManager.showMessageBox(
                        "Bạn đã đăng kí không thành công với tài khoản " + userName
                        + ". Xin vui lòng bạn hãy thử lại với tên tài khoản khác",
                        "Đăng kí thất bại",
                        JOptionPane.ERROR_MESSAGE);
            }
        }


        Main.mainConnection.setTimeOut(0);
    }
}
