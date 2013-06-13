/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import Controller.GameController;
import controller.ChatController;
import enums.ModePlay;
import java.awt.BorderLayout;
import javax.swing.JFrame;

/**
 *
 * @author Minh Khanh
 */
public class Caro extends JFrame{
    public Caro()
    {
        initComponents();
    }
    
    private void initComponents()
    {
        setSize(840, 600);
        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        
        GameController gameController = new GameController(ModePlay.Computer);
        GamePanel gamePanel = new GamePanel(gameController);
        ChatController chatController = new ChatController();
        view.ChatPanel chatPanel = new view.ChatPanel(chatController);
        
        this.setLayout(new BorderLayout());
            
        add(gamePanel, BorderLayout.CENTER);
        add(chatPanel, BorderLayout.EAST);
        
    }
    
    public static void main(String args[]) {
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Caro.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Caro().setVisible(true);
            }
        });
    }
}
