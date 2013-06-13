/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import events.TimeoutListener;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.Timer;

/**
 *
 * @author Minh Khanh
 */
public class TimePanel extends JPanel implements ActionListener{
    
    private JProgressBar progbTimer;
    private JLabel namePlayer;
    private int counter;  
    private TimeoutListener timeoutListener;
    private static final int TIMEOUT = 30;
    private boolean isRun;
    private Timer timer;
        
    public TimePanel() {
        initComponents();      
        timer = new Timer(1000, this);
    }
    
    private void initComponents(){
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        
        counter = TIMEOUT;
        namePlayer = new JLabel();
        JPanel lbpanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        lbpanel.add(namePlayer);
        progbTimer = new JProgressBar(0, TIMEOUT);
        progbTimer.setPreferredSize(new Dimension(150, 30));
        progbTimer.setMaximumSize(new Dimension(150, 30)); 
        progbTimer.setValue(counter);
        
        add(lbpanel);
        add(progbTimer);
    }  
    
    /**
     * @return the namePlayer
     */
    public String getNamePlayer() {
        return namePlayer.getText();
    }

    /**
     * @param namePlayer the namePlayer to set
     */
    public void setNamePlayer(String namePlayer) {
        this.namePlayer.setText(namePlayer);
    }
    
    public void addTiemoutListener(TimeoutListener listener)
    {
        this.timeoutListener = listener;
    }
    
    public void reset()
    {
        timer.stop();        
        counter = TIMEOUT;
        progbTimer.setValue(counter);
    }
    
    public void start() {     
        counter = TIMEOUT;
        progbTimer.setValue(counter);
        timer.start();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        counter--;
        progbTimer.setValue(counter);

        if (counter <= 0) {
            isRun = false;
            if (timeoutListener != null) {
                timeoutListener.TimeoutHandle(TimePanel.this);
            }
        }
    }
}
