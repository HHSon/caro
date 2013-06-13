package model;

import java.util.Vector;
import view.FriendListPanel;


public class FriendListModel {
    private FriendListPanel friendListPanel;
    private Vector<String> friendList;
    
    public FriendListModel(FriendListPanel friendListPanel) {
        if (friendListPanel == null) {
            throw new NullPointerException();
        }
        
        this.friendListPanel = friendListPanel;
        friendList = new Vector<String>();
    }
    
    public void getFriendListFromServer() {
        if (friendList.size() < 10) {
            for (int idx = 0; idx < 10; idx++)
                friendList.add("user " + idx);
        }
        
        friendListPanel.reloadFriendList();
    }
    
    public Vector<String> getFriendList() {
        return friendList;
    }
}
