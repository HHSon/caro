package model;

import network.packet.Logout;
import network.packet.Packet;
import view.FrameManager;
import view.MainFrame;

public class MainFrameModel {

    private MainFrame mainFrame;
    
    public MainFrameModel(MainFrame mainFrame) {
        if (mainFrame == null) {
            throw new NullPointerException();
        }
        
        this.mainFrame = mainFrame;
    }
    
    public void logout() {
        Packet pkt = new Logout();
        synchronized (Global.getMainConnection()) {
            Global.getMainConnection().sendByte(pkt.createData());
            Global.closeMainConnection();
        }
        
        FrameManager.getInstance().disposeAllFrame();
        FrameManager.getInstance().showLoginFrame();
    }
}
