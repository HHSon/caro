/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import Model.Player;
import View.Caro;
import View.GamePanel;
import enums.ModePlay;
import events.ChatListener;
import events.GameListener;
import view.ChatPanel;

/**
 *
 * @author Minh Khanh
 */
public class CaroController implements ChatListener, GameListener{
    private ChatController chatController;
    private Controller.GameController gameController;
    private Caro frmCaro;
    private String nameFriend;
    
    public CaroController(String name)
    {
        nameFriend = name;
    }
    
    public void Run()
    {
        frmCaro = new Caro();
        frmCaro.setTitle(nameFriend);
        addChatPanel();
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                frmCaro.setVisible(true);
            }
        });
    }
    
    private void ReceiveMessage(String msg) {
        chatController.ReceiveMessage(nameFriend, msg);
        System.out.println("nhan dc message: " + msg);
    }
    
    @Override
    public void SendMessage(String msg) {
        System.out.println("Gui di message: " + msg);
    }

    @Override
    public void InvitePlay() {
        System.out.println("Moi " + nameFriend + " choi game");
        addGamePanel();
    }
    
    private void addChatPanel() {
        ChatPanel chatPanel = new ChatPanel();
        chatController = new ChatController(chatPanel);
        chatController.addChatListener(this);
        chatPanel.setController(chatController);
        frmCaro.addChatPanel(chatPanel);
    }
    
    private void addGamePanel() {
        gameController = new Controller.GameController("You", nameFriend, ModePlay.Human);
        GamePanel gamePanel = new GamePanel(gameController);
        gameController.setView(gamePanel);
        frmCaro.addGamePanel(gamePanel);
    }

    @Override
    public void SendData(int r, int c) {
        System.out.println("Gui nuoc di");
    }
    
    public void ReceiveData(int r, int c) {
        System.out.println("nhan nuoc di");
        gameController.FriendMove(r, c);
    }

    @Override
    public void EndGame(boolean isWin) {
        
    }
}
