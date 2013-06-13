/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package events;

import Model.Player;

/**
 *
 * @author Minh Khanh
 */
public interface GameListener {
    public void PlayerMoved(Player player);
    public void StartGame();
    public void EndGame();
}
