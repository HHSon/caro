package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import model.SignUpModel;
import view.SignUpFrame;

public class SignUpController {
    private SignUpFrame signUpFrame;
    private SignUpModel signUpModel;
    
    public SignUpController(SignUpFrame signUpFrame, SignUpModel signUpModel) {
        if ((signUpFrame == null) || (signUpModel == null)) {
            throw new NullPointerException();
        }
        
        signUpFrame.addBtnSignUpActionListener(btnSignUpActionLisntener);
        
        this.signUpFrame = signUpFrame;
        this.signUpModel = signUpModel;
    }
    
    private ActionListener btnSignUpActionLisntener = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent ae) {
            signUpModel.SignUp();
        }
    };
}
