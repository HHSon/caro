/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import Controller.BoardController;
import Controller.GameController;
import Model.Game;
import Model.Player;
import events.TimeoutListener;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author Minh Khanh
 */
public class GamePanel extends JPanel implements TimeoutListener{

    private TimePanel timerPlayer1;
    private TimePanel timerPlayer2;
    private JButton btPlayAgain;
    private BoardPanel boardPanel;
    private GameController controller;
    private Game game;
    
    public GamePanel(GameController c) {  
        game = c.getGame();
        setController(c);
        initComponents();
    }
    
    private void initComponents(){
        this.setLayout(new FlowLayout(FlowLayout.LEFT, 0, 0));
        
        JPanel content = new JPanel();
        content.setLayout(new BoxLayout(content, BoxLayout.Y_AXIS));
        
        timerPlayer1 = new TimePanel();
        timerPlayer1.setNamePlayer(game.getYou().getName());
        timerPlayer1.addTiemoutListener(this);
        timerPlayer2 = new TimePanel();
        timerPlayer2.setNamePlayer(game.getFriend().getName());
        timerPlayer2.addTiemoutListener(this);
        
        JPanel timePanel = new JPanel();
        timePanel.setLayout(new BoxLayout(timePanel, BoxLayout.X_AXIS));
        timePanel.add(timerPlayer1);
        timePanel.add(Box.createRigidArea(new Dimension(10, 5)));
        timePanel.add(new JLabel("<html><b>VS</b>"));
        timePanel.add(Box.createRigidArea(new Dimension(10, 5)));
        timePanel.add(timerPlayer2);
        
        btPlayAgain = new JButton("Play");
        JPanel btpanel = new JPanel();
        btpanel.setLayout(new BoxLayout(btpanel, BoxLayout.Y_AXIS));
        btpanel.add(Box.createRigidArea(new Dimension(5, 25)));
        btpanel.add(btPlayAgain);
        btPlayAgain.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                controller.Play();
                boardPanel.update();
            }            
        });        
        
        JPanel time = new JPanel(new BorderLayout());
        time.add(timePanel, BorderLayout.WEST);
        time.add(btpanel, BorderLayout.EAST);  
        
        boardPanel = new BoardPanel(game);
        BoardController boardController = new BoardController(game, boardPanel, controller.getMode());
        boardPanel.setController(boardController);
        boardPanel.setPreferredSize(new Dimension(451, 451));
        boardPanel.setMaximumSize(new Dimension(451, 451));
        
        content.add(time);
        content.add(Box.createRigidArea(new Dimension(10, 10)));
        content.add(boardPanel);
        
        this.add(content);
    }    
    
    public void setController(GameController c)
    {
        this.controller = c;
    }

    @Override
    public void TimeoutHandle(TimePanel sender) {
        String name = sender.getNamePlayer();
        controller.GameTimeoutHandle(name);
    }

    public void PlayerMoved(Player player) {
        TimePanel temp = timerPlayer1;
        if (timerPlayer1.getNamePlayer().equals(player.getName()))
        {
            timerPlayer1.reset();
            timerPlayer2.start();
        }
        else
        {
            timerPlayer1.start();
            timerPlayer2.reset();
        }
    }

    public void StartGame() {
        if (game.NextPlayerIsYou())
        {
            timerPlayer1.start();
        }
        else
        {
            timerPlayer2.start();
        }
    }

    public void EndGame() {
        timerPlayer1.reset();
        timerPlayer2.reset();
    }
}
