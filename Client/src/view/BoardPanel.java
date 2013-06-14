/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import Controller.BoardController;
import Model.Game;
import enums.Chess;
import enums.GameState;
import enums.PlayerCode;
import java.awt.BasicStroke;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Stroke;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.Timer;

/**
 *
 * @author Minh Khanh
 */
public class BoardPanel extends JPanel implements ActionListener{

    private int sizeCell;
    private int nCol;
    private int nRow;
    private BufferedImage virtualChessO;
    private BufferedImage virtualChessX;
    private BufferedImage chessPlayerO;
    private BufferedImage chessPlayerX;
    private Point posVC;
    private Color background;
    private Color foreground;
    private BufferedImage buffer;
    private Graphics graphic;
    private Game game;
    private boolean isDrawVitualChess;
    
    private BufferedImage waiting1;
    private BufferedImage waiting2;
    private BufferedImage waiting3;
    private int waitingCount = 0;
    private Timer timerWaiting;

    public BoardPanel(Game game) {
        this.game = game;
        init();
        
        timerWaiting = new Timer(200, this);
        timerWaiting.start();
    }

    public void init() {
        sizeCell = 30;
        nCol = game.GetNumberColumns();
        nRow = game.GetNumberRows();
        posVC = new Point();
        background = Color.white;
        foreground = Color.black;
        buffer = new BufferedImage(nCol * sizeCell + 2, nRow * sizeCell + 2, BufferedImage.TYPE_INT_ARGB);
        graphic = buffer.getGraphics();
        isDrawVitualChess = false;
        setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        setSize(nCol * sizeCell, nRow * sizeCell);
        try {
            
            virtualChessO = ImageIO.read(new File("./src/images/virtual_chessO.png"));
            virtualChessX = ImageIO.read(new File("./src/images/virtual_chessX.png"));
            chessPlayerO = ImageIO.read(new File("./src/images/chessO.png"));
            chessPlayerX = ImageIO.read(new File("./src/images/chessX.png"));
            
            chessPlayerX = ImageIO.read(new File("./src/images/bg.jpg"));
            
        } catch (IOException ex) {
            Logger.getLogger(BoardPanel.class.getName()).log(Level.SEVERE, null, ex);
        }        
    }

    public void draw(Graphics g) {
        graphic.setColor(background);
        graphic.fillRect(0, 0, buffer.getWidth(), buffer.getWidth());
        graphic.setColor(foreground);
        for (int i = 0; i <= nCol; i++) {
            graphic.drawLine(i * sizeCell, 0, i * sizeCell, buffer.getHeight());
        }
        for (int i = 0; i <= nRow; i++) {
            graphic.drawLine(0, i * sizeCell, buffer.getWidth(), i * sizeCell);
        }
        for (int i = 0; i < nRow; i++)
        {
            for (int j = 0; j < nCol; j++)
            {
                PlayerCode player = game.GetBoard()[i][j];
                if (player == PlayerCode.None) continue;
                BufferedImage chess = (player == PlayerCode.Player1)? chessPlayerO : chessPlayerX;
                graphic.drawImage(chess, j * sizeCell, i * sizeCell, this);
            }
        }
        if (isDrawVitualChess)
        {
            BufferedImage chess = (game.getYou().getChess() == Chess.O)? virtualChessO : virtualChessX;
            graphic.drawImage(chess, posVC.x, posVC.y, this);
        }
        if (game.IsEndGame())
        {
            Point start = game.getFirstCell();
            Point end = game.getLastCell();
            Graphics2D g2d = (Graphics2D) graphic;
            g2d.setColor(Color.BLUE);
            Stroke oldStroke = g2d.getStroke();
            g2d.setStroke(new BasicStroke(5, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));
            g2d.drawLine(start.x * sizeCell + sizeCell / 2,
                    start.y * sizeCell + sizeCell / 2,
                    end.x * sizeCell + sizeCell / 2,
                    end.y * sizeCell + sizeCell / 2);
            g2d.setColor(foreground);
            g2d.setStroke(oldStroke);            
        }
        
        if (game.getState() == GameState.Waiting)
        {
            BufferedImage waiting = null;
            if (waitingCount == 1)
            {
                waiting = waiting1;
            }
            else if (waitingCount == 2)
            {
                waiting = waiting2;
            }
            else if (waitingCount == 3)
            {
                waiting = waiting3;
            }
            if (waiting != null)
            {
                graphic.drawImage(waiting, 25, 200, this);
            }
        }        
        g.drawImage(buffer, 0, 0, this);
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        draw(g);
    }

    public void update() {
        repaint();
    }
    
    public void setPositionVirtualChess(Point mousePos)
    {
        this.posVC = new Point(mousePos.x / sizeCell, mousePos.y / sizeCell);
        update();
    }
    
    public int ConvertPixelToCellX(int x)
    {
        return x / sizeCell;
    }
    
    public int ConvertPixelToCellY(int y)
    {
        return y / sizeCell;
    }
    
    public void OnMouseMove(int x, int y)
    {
        isDrawVitualChess = false;
        if (game.NextPlayerIsYou() && game.IsPlaying())
        {
            int r = ConvertPixelToCellY(y);
            int c = ConvertPixelToCellX(x);
            if (r >= 0 && r < nRow && c >= 0 && c < nCol && game.GetBoard()[r][c] == PlayerCode.None)
            {
                isDrawVitualChess = true;
                posVC.x = x / sizeCell * sizeCell;
                posVC.y = y / sizeCell * sizeCell;
            }
        }
        update();
    }
    
    public void OnMouseLeave()
    {
        isDrawVitualChess = false;
        update();
    }
    
    public void setController(BoardController c)
    {
        addMouseListener(c);
        addMouseMotionListener(c);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        waitingCount++;
        if (waitingCount > 3)
        {
            waitingCount = 1;
        }
    }
}
