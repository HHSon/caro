package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import model.LoginModel;
import view.FrameManager;
import view.LoginFrame;


public class LoginController {
    private LoginFrame loginFrame;
    private LoginModel loginModel;
    
    
    public LoginController(LoginFrame loginFrame, LoginModel loginModel) {
        if ((loginFrame == null) || (loginModel == null)) {
            throw new NullPointerException();
        }
        
        loginFrame.addBtnLoginActionListener(btnLoginListener);
        loginFrame.addBtnSignUpActionListener(btnSignUpActionListener);
        
        this.loginFrame = loginFrame;
        this.loginModel = loginModel;
    }
    
    private ActionListener btnLoginListener = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent ae) {
            login();
        }
    };
    
    private void login() { 
        if (loginFrame.checkInputData() == false) {
            return;
        }
        loginModel.login();
    }
    
    private ActionListener btnSignUpActionListener = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent ae) {
            FrameManager.getInstance().showSignUpFrame();
        }
    };
}