package model;

import view.LoginFrame;

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
    
    public void login(String username, String password) {
    }
}