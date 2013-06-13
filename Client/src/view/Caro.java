/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import Controller.GameController;
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
        
        this.setLayout(new BorderLayout());
            
        add(gamePanel, BorderLayout.CENTER);
        
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
