/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.Game;
import enums.ModePlay;
import View.BoardPanel;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

/**
 *
 * @author Minh Khanh
 */
public class BoardController implements MouseListener, MouseMotionListener{
    
    private Game game;
    private BoardPanel board;
    
    public BoardController(Game g, BoardPanel b, ModePlay m)
    {
        this.game = g;
        this.board = b;
    }

    @Override
    public void mousePressed(MouseEvent e) {
        if (e.getButton() == MouseEvent.BUTTON1 && game.IsPlaying() && game.NextPlayerIsYou())
        {
            int r = board.ConvertPixelToCellY(e.getY());
            int c = board.ConvertPixelToCellX(e.getX());
            game.YouMove(r, c);
            board.update();
        }
    }
    
    @Override
    public void mouseMoved(MouseEvent e) {
        board.OnMouseMove(e.getX(), e.getY());
    } 
    
    @Override
    public void mouseExited(MouseEvent e) {
        board.OnMouseLeave();
    }

    @Override
    public void mouseReleased(MouseEvent e) {}

    @Override
    public void mouseEntered(MouseEvent e) {}
    
    @Override
    public void mouseClicked(MouseEvent e) {}  

    @Override
    public void mouseDragged(MouseEvent e) {}
}
