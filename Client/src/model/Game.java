/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import enums.ModePlay;
import Controller.GameController;
import enums.Chess;
import enums.GameState;
import enums.PlayerCode;
import java.awt.Point;

/**
 *
 * @author Minh Khanh
 */
public class Game {
    private int columns;
    private int rows;
    private PlayerCode[][] board;
    private Player currentPlayerMove;
    private Player you;
    private Player friend;
    private boolean isPlaying;
    private boolean endGame;
    private GameController controller;
    private GameState state;

    public Game(String namePlayer1, String namePlayer2, GameController c) {          
        initBoard();
        you = new Player();
        you.setName(namePlayer1);
        you.setCode(PlayerCode.Player1);
        friend = (c.getMode() == ModePlay.Human)? new Player() : new Computer(this);
        friend.setName(namePlayer2);
        friend.setCode(PlayerCode.Player2);
        isPlaying = false;
        endGame = false;
        controller = c;
             
        state = GameState.NotReady;
    }     
    
    private void initBoard()
    {
        columns = 15;
        rows = 15;
        board = new PlayerCode[rows][columns];
        resetBoard();
    }
    
    public void NewGame()
    { 
        resetBoard();
        isPlaying = true;
        endGame = false;
        currentPlayerMove = you;
        setFirstPlayer(true);
    }
    
    public void NewGame(boolean firstPlayerIsYou)
    { 
        resetBoard();
        isPlaying = true;
        endGame = false;
        setFirstPlayer(firstPlayerIsYou);
    }
    
    public void setFirstPlayer(boolean isYou)
    {
        if (isYou)
        {
            you.setChess(Chess.O);
            friend.setChess(Chess.X);
            currentPlayerMove = you;
        }
        else
        {
            friend.setChess(Chess.O);
            you.setChess(Chess.X);
            currentPlayerMove = friend;
        }
    }
    
    public void resetBoard()
    {
        for (int i = 0; i < rows; i++)
        {
            for (int j = 0; j < columns; j++)
            {
                board[i][j] = PlayerCode.None;
            }
        }
    }  
    
    public boolean Move(int r, int c)
    {
        if (r >= 0 && r < rows && c >= 0 && c < columns && board[r][c] == PlayerCode.None)
        {
            board[r][c] = currentPlayerMove.getCode();
            endGame = checkWin(r, c);
            isPlaying = !endGame;
            
            controller.PlayerMoved(currentPlayerMove);            
            if (endGame)
            {
                controller.EndGame(currentPlayerMove);
            }
            currentPlayerMove = (currentPlayerMove == friend)? you : friend;
            return true;
        }
        
        return false;
    }
    
    public void YouMove(int r, int c)
    {
        if (Move(r, c))
        {
            if (friend instanceof Computer)
            {
                Point pos = ((Computer)friend).Next();
                Move(pos.y, pos.x);
            }
            else
            {
                controller.YouMoveFinished(r, c);
            }
        }
    }
    
    public void FriendMove(int r, int c)
    {
        Move(r, c);
    }
    
    private Point firstCell = new Point();
    private Point lastCell = new Point();
    private boolean checkWin(int row, int col) {
        PlayerCode player = this.board[row][col];
        int count, c, r;

        //kiem tra hang ngang
        count = 0;
        c = col - 1;
        r = row;
        while (c >= 0 && board[r][c] == player) {
            c--;
            count++;
        }
        firstCell.x = c + 1;
        firstCell.y = r;
        c = col + 1;
        while (c < columns && board[r][c] == player) {
            c++;
            count++;
        }
        if (count == 4) {
            lastCell.x = c - 1;
            lastCell.y = r;
            return true;
        }
        // kiem tra hang doc
        count = 0;
        c = col;
        r = row - 1;
        while (r >= 0 && board[r][c] == player) {
            r--;
            count++;
        }
        firstCell.x = c;
        firstCell.y = r + 1;
        r = row + 1;
        while (r < rows && board[r][c] == player) {
            r++;
            count++;
        }
        if (count == 4) {
            lastCell.x = c;
            lastCell.y = r - 1;
            return true;
        }
        // kiem tra duong cheo chinh
        count = 0;
        c = col - 1;
        r = row - 1;
        while (c >= 0 && r >= 0 && board[r][c] == player) {
            c--;
            r--;
            count++;
        }
        firstCell.x = c + 1;
        firstCell.y = r + 1;
        c = col + 1;
        r = row + 1;
        while (c < columns && r < rows && board[r][c] == player) {
            c++;
            r++;
            count++;
        }
        if (count == 4) {
            lastCell.x = c - 1;
            lastCell.y = r - 1;
            return true;
        }
        // kiem tra duong cheo phu
        count = 0;
        c = col + 1;
        r = row - 1;
        while (c < columns && r >= 0 && board[r][c] == player) {
            c++;
            r--;
            count++;
        }
        firstCell.x = c - 1;
        firstCell.y = r + 1;
        c = col - 1;
        r = row + 1;
        while (c >= 0 && r < rows && board[r][c] == player) {
            c--;
            r++;
            count++;
        }
        lastCell.x = c + 1;
        lastCell.y = r - 1;
        if (count == 4) {
            lastCell.x = c + 1;
            lastCell.y = r - 1;
            return true;
        }

        return false;
    }
    
    public int GetNumberColumns()
    {
        return this.columns;
    }
    
    public int GetNumberRows()
    {
        return this.rows;
    }
    
    public PlayerCode[][] GetBoard()
    {
        return this.board;
    }
    
    public boolean NextPlayerIsYou()
    {
        return currentPlayerMove == you;
    }
    
    public Player getYou()
    {
        return this.you;
    }
    
    public Player getFriend()
    {
        return this.friend;
    }

    /**
     * @return the firstCell
     */
    public Point getFirstCell() {
        return firstCell;
    }

    /**
     * @return the lastCell
     */
    public Point getLastCell() {
        return lastCell;
    }
    
    public boolean IsPlaying()
    {
        return this.isPlaying;
    }
    
    public boolean IsEndGame()
    {
        return this.endGame;
    }
    
    public Player CurrentPlayerMove()
    {
        return this.currentPlayerMove;
    }
    
    public GameState getState()
    {
        return this.state;
    }
}
