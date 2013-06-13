/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.Game;
import Model.Player;
import View.GamePanel;
import enums.ModePlay;

/**
 *
 * @author Minh Khanh
 */
public class GameController{    
    private ModePlay mode;
    private Game game;
    private GamePanel panel;

    public GameController(String yourName, String friendName, ModePlay mode) {
        this.mode = mode;
        game = new Game(yourName, friendName, this);
    } 
    
    public void Play()
    {
        game.NewGame();
        panel.StartGame();
    }
    
    public void YouMoveFinished(int r, int c)
    {
        
    }
    
    public void FriendMove(int r, int c)
    {
        game.FriendMove(r, c);
    }
    
    public void GameTimeoutHandle(String name)
    {
        
    }
    
    public void PlayerMoved(Player player)
    {
        panel.PlayerMoved(player);
    }
    
    public void EndGame(Player player)
    {
        panel.EndGame();
    }
    
    public ModePlay getMode() {
        return mode;
    }

    public void setMode(ModePlay mode) {
        this.mode = mode;
    }

    public Game getGame() {
        return game;
    }

    public void setGame(Game game) {
        this.game = game;
    }
    
    public void setView(GamePanel view)
    {
        this.panel = view;
    }
    
}
