/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package events;


/**
 *
 * @author Minh Khanh
 */
public interface GameListener {
    public void SendData(int r, int c);
    public void EndGame(boolean isWin);
}
