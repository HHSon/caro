/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import events.ChatListener;
import view.ChatPanel;

/**
 *
 * @author Minh Khanh
 */
public class ChatController{    
    
    private ChatListener listener;
    private ChatPanel chatPanel;
    
    public ChatController(ChatPanel chat)
    {
        this.chatPanel = chat;
    }
    
    public void SendMessage(String msg)
    {
        if (listener != null)
        {
            listener.SendMessage(msg);
        }
    }
    
    public void ReceiveMessage(String name ,String msg)
    {
        chatPanel.addMessage(name, msg);
    }
    
    public void InvitePlay()
    {
        if (listener != null)
        {
            listener.InvitePlay();
        }
    }
    
    public void addChatListener(ChatListener l)
    {
        this.listener = l;
    }
}
