package network.packet;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */


import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 *
 * @author LHS
 */
public class ListOnliner implements Serializable {

    private Set listOnliner;

    public ListOnliner(Set listOnliner) {
        this.listOnliner = listOnliner;
    }

    public ListOnliner() {
        this.listOnliner = new HashSet();
    }
}
