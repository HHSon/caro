/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import controller.ChatController;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 *
 * @author Minh Khanh
 */
public class Caro extends JFrame{
    
    private int WidthMin = 400;
    private int WidthMax = 900;
    private JPanel content;
    
    public Caro()
    {
        initComponents();
    }
    
    private void initComponents()
    {
        setSize(WidthMin, 600);
        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        
        setLayout(new FlowLayout(FlowLayout.CENTER, 20, 20));
        
        content = new JPanel();
        content.setLayout(new BorderLayout(10, 20));        
        ChatController chatController = new ChatController();
        view.ChatPanel chatPanel = new view.ChatPanel(chatController);
        
        JPanel center = new JPanel();
        center.setLayout(new BoxLayout(center, BoxLayout.Y_AXIS));
        center.add(Box.createRigidArea(new Dimension(10, 65)));
        center.add(chatPanel);
        content.add(center, BorderLayout.CENTER);        
        add(content);
    }
    
    public void addGamePanel(GamePanel gamePanel)
    {
        setSize(WidthMax, 600);
        content.add(gamePanel, BorderLayout.WEST);
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
