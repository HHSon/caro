/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import controller.CaroController;
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
    private int WidthMax = 860;
    private int Height = 585;
    private JPanel content;
    private CaroController controller;
    
    public Caro()
    {        
        initComponents();
    }
    
    private void initComponents()
    {
        setResizable(false);
        setLayout(new FlowLayout(FlowLayout.CENTER, 30, 10));
        content = new JPanel();
        content.setLayout(new BorderLayout(25, 20));  
        add(content);
    }
    
    public void addChatPanel(view.ChatPanel chatPanel)
    {
        setSize(WidthMin, Height);        
        JPanel center = new JPanel();
        center.setLayout(new BoxLayout(center, BoxLayout.Y_AXIS));
        center.add(Box.createRigidArea(new Dimension(10, 65)));
        center.add(chatPanel);
        content.add(center, BorderLayout.CENTER);        
        
    }
    
    public void setController(CaroController c)
    {
        this.controller = c;
    }
    
    public void addGamePanel(GamePanel gamePanel)
    {
        setSize(WidthMax, Height);
        content.add(gamePanel, BorderLayout.WEST);
    }
}
