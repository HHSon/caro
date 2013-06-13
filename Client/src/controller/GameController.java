/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.Game;
import enums.ModePlay;

/**
 *
 * @author Minh Khanh
 */
public class GameController{    
    private ModePlay mode;
    private Game game;

    public GameController(ModePlay mode) {
        game = new Game("You", "Computer", this);
    } 
    
    public void Play()
    {
        game.NewGame();
    }
    
    public void YouMoveFinished(int r, int c)
    {
        
    }
    
    /**
     * @return the mode
     */
    public ModePlay getMode() {
        return mode;
    }

    /**
     * @param mode the mode to set
     */
    public void setMode(ModePlay mode) {
        this.mode = mode;
    }

    /**
     * @return the game
     */
    public Game getGame() {
        return game;
    }

    /**
     * @param game the game to set
     */
    public void setGame(Game game) {
        this.game = game;
    }
    
}
