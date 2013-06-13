package controller;

import model.FriendListModel;
import view.FriendListPanel;

public class FriendListController {
    private FriendListPanel friendListPanel;
    private FriendListModel friendListModel;
    
    
    
    public FriendListController(FriendListPanel friendListPanel, FriendListModel friendListModel) {
        if ((friendListPanel == null) || (friendListModel == null)) {
            throw new NullPointerException();
        }
        
        this.friendListModel = friendListModel;
        this.friendListPanel = friendListPanel;
        
        friendListModel.getFriendListFromServer();
    }
}
