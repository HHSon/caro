package view;

import java.awt.Frame;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;


public class FrameManager {
    private LoginFrame loginFrame;
    
    private SignUpFrame signUpFrame;
    private MainFrame mainFrame;
    private Frame lastShowFrame;
    private static FrameManager INSTANCE;
    
    
    private FrameManager() {
        setLookAndFeels(UIManager.getSystemLookAndFeelClassName());
    }
    
    /**
     * Chỉ cho phép tạo một đối tượng duy nhất trong
     * chương trình
     */
    public static FrameManager getInstance() {
        if (INSTANCE == null)
            INSTANCE = new FrameManager();
        
        return INSTANCE;
    }
    
    public LoginFrame showLoginFrame() {
        if (loginFrame == null)
            loginFrame = new LoginFrame();        
        loginFrame.setVisible(true);
        lastShowFrame = loginFrame;
        return loginFrame;
    }
    
    public SignUpFrame showSignUpFrame() {
        if (signUpFrame == null)
            signUpFrame = new SignUpFrame();
        
        signUpFrame.setVisible(true);
        lastShowFrame = signUpFrame;
        
        return signUpFrame;
    }
    
    public MainFrame showMainFrame() {
        if (mainFrame == null)
            mainFrame = new MainFrame();
        
        mainFrame.setVisible(true);
        lastShowFrame = mainFrame;
        
        return mainFrame;
    }
    
    public void disposeLoginFrame() {
        if (loginFrame != null)
            loginFrame.dispose();
    }
    
    public void disposeSignUpFrame() {
        if (signUpFrame != null)
            signUpFrame.setVisible(false);
        
    }
    
    public void disposeMainFrame() {
        if (mainFrame != null)
            mainFrame.dispose();
    }
    
    public void disposeAllFrame() {
        disposeLoginFrame();
        disposeSignUpFrame();
        disposeMainFrame();
    }
    
    public final boolean setLookAndFeels(String name) {
        try {
            UIManager.setLookAndFeel(name);
        } catch (ClassNotFoundException | 
                 InstantiationException | 
                 IllegalAccessException | 
                 UnsupportedLookAndFeelException ex) {
            
            String msg = "Gặp lỗi khi đặt kiểu giao diện" 
                         + " theo kiểu mặc định của hệ điều hành. "
                         + " Chương trình dùng giao diện mặc định";
            showMessageBox(msg, "Lỗi giao diện", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        return true;
    }
    
    /**
     * Hiện một messageBox lên màn hình
     * @param message nội dung
     * @param title Tiêu đề của message
     * @param type Kiểu định nghĩa trong JOptionPane
     */
    public static void showMessageBox(String message, String title, int type) {
        JOptionPane.showMessageDialog(null, message, title, type, null);
    }
}
