/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package events;

/**
 *
 * @author Minh Khanh
 */
public interface ChatListener {
    public void SendMessage(String msg);
    public void InvitePlay();
}
