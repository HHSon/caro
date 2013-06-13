package controller;

import model.MainFrameModel;
import view.MainFrame;


public class MainFrameController {
    private MainFrameModel mainFrameModel;
    private MainFrame mainFrame;
    
    public MainFrameController(MainFrame mainFrame, MainFrameModel mainFrameModel) {
        if ((mainFrameModel == null) || (mainFrame == null)) {
            throw new NullPointerException();
        }
        
        this.mainFrame = mainFrame;
        this.mainFrameModel = mainFrameModel;
    }
    
    public void logout() {
        mainFrameModel.logout();
    }
}
