package view;

import controller.SignUpController;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.util.Arrays;
import model.SignUpModel;

public class SignUpFrame extends javax.swing.JFrame {
    
    private SignUpController signUpController;
    private SignUpModel signUpModel;
    private char passwordEchoChar;
    
    
    public SignUpFrame() {
        initComponents();
        initScreen();
        
        signUpModel = new SignUpModel(this);
        signUpController = new SignUpController(this, signUpModel);
    }
    
    private void initScreen() {
        lblUserNameError.setVisible(false);
        lblReenterPassword.setVisible(false);
        passwordEchoChar = ptxtPassword.getEchoChar();
    }
    
    public String getUserName() {
        return txtUserName.getText();
    }
    
    public String getFullName() {
        return txtFullName.getText();
    }
    
    public String getPassword() {
        return ptxtPassword.getText();
    }
    
    public void addBtnSignUpActionListener(ActionListener actionListener) {
        if (actionListener == null) {
            throw new NullPointerException();
        }
        
        btnSignUp.addActionListener(actionListener);
    }
    
    public boolean checkInputData() {
        if ((txtUserName.getText() == null) ||
                (txtUserName.getText().equals(""))) {
            txtUserName.requestFocus();
            return false;
        }
        
        if ((ptxtPassword.getPassword() == null) ||
                (ptxtPassword.getPassword().length <= 0)) {
            ptxtPassword.requestFocus();
            return false;
        }
        
        if ((ptxtReenterPassword.getPassword() == null) || 
                (ptxtReenterPassword.getPassword().length <= 0)) {
            ptxtReenterPassword.requestFocus();
            return false;
        }
        
        if (checkComfirmPassword() == false) {
            lblReenterPassword.setForeground(new Color(204, 0, 0));
            lblReenterPassword.setText("Mật khẩu không trùng khớp");
            lblReenterPassword.setVisible(true);
            ptxtReenterPassword.requestFocus();
            return false;
        }
                
        if ((txtFullName.getText() == null) || 
                (txtFullName.getText().equals(""))) {
            txtFullName.requestFocus();
            return false;
        }
        
        return true;
    }
    
    private boolean checkComfirmPassword() {
        return Arrays.equals(ptxtPassword.getPassword(),
                             ptxtReenterPassword.getPassword());
    }
    
    public String encryptPassword(char[] keyword) {
        return keyword.toString();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        ptxtPassword = new javax.swing.JPasswordField();
        jLabel4 = new javax.swing.JLabel();
        ptxtReenterPassword = new javax.swing.JPasswordField();
        txtUserName = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        txtFullName = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        lblUserNameError = new javax.swing.JLabel();
        lblReenterPassword = new javax.swing.JLabel();
        chkShowPassword = new javax.swing.JCheckBox();
        btnSignUp = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Caro Online - Đăng Nhập");
        setLocationByPlatform(true);

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel1.setText("Đăng Kí");

        jLabel3.setText("Mật khẩu");

        jLabel4.setText("Nhập lại mật khẩu:");

        ptxtReenterPassword.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                ptxtReenterPasswordCaretUpdate(evt);
            }
        });

        jLabel2.setText("Tên đăng nhập");

        jLabel5.setText("Họ tên");

        lblUserNameError.setForeground(new java.awt.Color(204, 0, 0));
        lblUserNameError.setText("Tên đăng nhập đã tồn tại");

        lblReenterPassword.setForeground(new java.awt.Color(0, 102, 0));
        lblReenterPassword.setText("Mật khẩu trùng khớp");

        chkShowPassword.setText("Hiện mật khẩu");
        chkShowPassword.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                chkShowPasswordItemStateChanged(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3)
                    .addComponent(jLabel4)
                    .addComponent(jLabel5)
                    .addComponent(jLabel2))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(txtUserName, javax.swing.GroupLayout.PREFERRED_SIZE, 212, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(ptxtPassword, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(ptxtReenterPassword, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtFullName, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 212, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblUserNameError)
                    .addComponent(lblReenterPassword)
                    .addComponent(chkShowPassword))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtUserName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2)
                    .addComponent(lblUserNameError))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(ptxtPassword, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(chkShowPassword))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel4)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(ptxtReenterPassword, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(lblReenterPassword)))
                .addGap(21, 21, 21)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtFullName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5))
                .addContainerGap(31, Short.MAX_VALUE))
        );

        btnSignUp.setBackground(new java.awt.Color(102, 153, 0));
        btnSignUp.setText("Đăng Kí");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(34, 34, 34)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(192, 192, 192)
                        .addComponent(btnSignUp, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(40, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addComponent(jLabel1)
                .addGap(30, 30, 30)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnSignUp, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(32, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void chkShowPasswordItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_chkShowPasswordItemStateChanged
        if (evt.getStateChange() == ItemEvent.DESELECTED) {
            ptxtPassword.setEchoChar(passwordEchoChar);
            ptxtReenterPassword.setEchoChar(passwordEchoChar);
        } else {
            ptxtPassword.setEchoChar((char)0);
            ptxtReenterPassword.setEchoChar((char)0);
        }
    }//GEN-LAST:event_chkShowPasswordItemStateChanged

    private void ptxtReenterPasswordCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_ptxtReenterPasswordCaretUpdate
        if ((ptxtReenterPassword.getPassword() == null) ||
                ptxtReenterPassword.getPassword().length <= 0) {
            return;
        }
        
        if (checkComfirmPassword() == true) {
            lblReenterPassword.setForeground(new Color(102,153,0));
            lblReenterPassword.setText("Trùng khớp");
            lblReenterPassword.setVisible(true);
        } else {
            lblReenterPassword.setVisible(false);
        }
    }//GEN-LAST:event_ptxtReenterPasswordCaretUpdate

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnSignUp;
    private javax.swing.JCheckBox chkShowPassword;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JLabel lblReenterPassword;
    private javax.swing.JLabel lblUserNameError;
    private javax.swing.JPasswordField ptxtPassword;
    private javax.swing.JPasswordField ptxtReenterPassword;
    private javax.swing.JTextField txtFullName;
    private javax.swing.JTextField txtUserName;
    // End of variables declaration//GEN-END:variables
}
