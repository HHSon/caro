/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import controller.ChatController;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JEditorPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.text.BadLocationException;
import javax.swing.text.DefaultCaret;
import javax.swing.text.Element;
import javax.swing.text.html.HTMLDocument;

/**
 *
 * @author Minh Khanh
 */
public class ChatPanel extends JPanel implements MouseListener{
    
    private JEditorPane pTextPane;
    private JTextArea txtText;
    private JButton btSend;
    private ChatController controller;
    private JButton btBold;
    private JButton btItalic;
    private JButton btUnderline;
    private JButton btInvite;
    
    private boolean isBold;
    private boolean isItalic;
    private boolean isUnderline;
    
    private static final int Width = 250;
    
    public ChatPanel(ChatController c){
        this.controller = c;
        initComponents();
    }
    
    private void initComponents(){
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.setPreferredSize(new Dimension(Width, 450));
        this.setMaximumSize(new Dimension(Width, 450));
        
        // Hien thi text chat
        pTextPane = new JEditorPane();
        pTextPane.setEditable(false);
        pTextPane.setContentType("text/html");
        pTextPane.setText("<html><body><div id='mark'></div></body></html>");
        pTextPane.setBackground(Color.white);
        pTextPane.setMaximumSize(new Dimension(Width, 250));
        pTextPane.setPreferredSize(new Dimension(Width, 250));
        DefaultCaret caret = (DefaultCaret)pTextPane.getCaret();
        caret.setUpdatePolicy(DefaultCaret.ALWAYS_UPDATE);        
        
        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBorder(BorderFactory.createLineBorder(Color.black));
        scrollPane.setViewportView(pTextPane);
        this.add(scrollPane);
        
        //button panel
        JPanel formatPanel = new JPanel();
        formatPanel.setLayout(new BoxLayout(formatPanel, BoxLayout.X_AXIS));
        
        btBold = new JButton("B");
        btBold.setPreferredSize(new Dimension(40, 40));
        btBold.setMaximumSize(new Dimension(40, 40));
        btBold.setBackground(Color.white);
        btItalic = new JButton("I");
        btItalic.setPreferredSize(new Dimension(40, 40));
        btItalic.setMaximumSize(new Dimension(40, 40));
        btItalic.setBackground(Color.white);
        btUnderline = new JButton("U");
        btUnderline.setPreferredSize(new Dimension(40, 40));
        btUnderline.setMaximumSize(new Dimension(40, 40));
        btUnderline.setBackground(Color.white);
        formatPanel.add(btBold);
        formatPanel.add(btItalic);
        formatPanel.add(btUnderline);
        
        btInvite = new JButton("Mời chơi");        
        btInvite.setBackground(Color.green);
        btInvite.addMouseListener(this);
        
        JPanel btPanel = new JPanel();
        btPanel.setPreferredSize(new Dimension(Width, 40));
        btPanel.setMaximumSize(new Dimension(Width, 40));
        btPanel.setLayout(new BorderLayout());
        btPanel.add(formatPanel, BorderLayout.WEST);
        btPanel.add(btInvite, BorderLayout.EAST);
        this.add(btPanel);
        
        //
        txtText = new JTextArea();
        txtText.setLayout(new FlowLayout(FlowLayout.RIGHT, 2, 22));   
        txtText.setPreferredSize(new Dimension(Width, 100));
        txtText.setMaximumSize(new Dimension(Width, 100));
        
        btSend = new JButton("Gửi");
        btSend.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
        btSend.setPreferredSize(new Dimension(50, 40));
        btSend.setMaximumSize(new Dimension(50, 40));
        btSend.addMouseListener(this);
        txtText.add(btSend);
        this.add(txtText);        
    }
    
    private String template = "<div style='color:balck'>\n" +
            "<span><b>NAME: </b></span>" + 
            "CONTENT" +
            "</div>";
//    private String templatePlayer2 = "<div style='color:red'>\n" +
//            "<span><b>NAME: </b></span>" + 
//            "CONTENT" +
//            "</div>";
    public void addMessage(String name ,String msg)
    {
        HTMLDocument doc = (HTMLDocument) pTextPane.getDocument();
        Element el = doc.getElement("mark");
        try {
            doc.insertAfterEnd(el, template.replace("NAME", name).replace("CONTENT", msg));
        } catch (BadLocationException | IOException ex) {
            Logger.getLogger(ChatPanel.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @Override
    public void mousePressed(MouseEvent e) {
        JButton sender = (JButton) e.getComponent();
        if (sender == btInvite)
        {
            controller.InvitePlay();
        }
        else if (sender == btSend)
        {
            String msg = this.txtText.getText();
            if (msg.trim().isEmpty()) return;
            this.txtText.setText("");
            addMessage("Me", msg);
            controller.SendMessage(msg);
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {}
    @Override
    public void mouseReleased(MouseEvent e) {}
    @Override
    public void mouseEntered(MouseEvent e) {}
    @Override
    public void mouseExited(MouseEvent e) {}   
    
}
