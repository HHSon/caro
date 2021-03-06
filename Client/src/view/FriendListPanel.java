package view;

import controller.FriendListController;
import java.util.Vector;
import javax.swing.DefaultListModel;
import model.FriendListModel;


public class FriendListPanel extends javax.swing.JPanel {

    private FriendListController friendListController;
    private FriendListModel friendListModel;
    private DefaultListModel listModel;
    
    public FriendListPanel() {
        initComponents();
        initScreen();
        
        friendListModel = new FriendListModel(this);
        friendListController = new FriendListController(this, friendListModel);
    }
    
    private void initScreen() {
        listModel = new DefaultListModel();
        listFriends.setModel(listModel);
    }
    
    public void reloadFriendList() {
        listModel.clear();
        Vector<String> friendList = friendListModel.getFriendList();
        
        for (String user : friendList) {
            listModel.addElement(user);
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        listFriends = new javax.swing.JList();

        jScrollPane1.setViewportView(listFriends);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 252, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 387, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JList listFriends;
    // End of variables declaration//GEN-END:variables
}
