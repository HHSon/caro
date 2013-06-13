/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import enums.Chess;
import enums.PlayerCode;

/**
 *
 * @author Minh Khanh
 */
public class Player {
    protected String name;
    protected PlayerCode code;
    private Chess chess;
    
    public Player()
    {
        
    }
    
    public Player(String n, PlayerCode c)
    {
        this.name = n;
        this.code = c;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the code
     */
    public PlayerCode getCode() {
        return code;
    }

    /**
     * @param code the code to set
     */
    public void setCode(PlayerCode code) {
        this.code = code;
    }

    /**
     * @return the chess
     */
    public Chess getChess() {
        return chess;
    }

    /**
     * @param chess the chess to set
     */
    public void setChess(Chess chess) {
        this.chess = chess;
    }
}
