/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package log;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.logging.Level;

/**
 *
 * @author LHS
 */
public class Logger {

    public static synchronized void write(String log) {

        RandomAccessFile rdf = null;

        try {
            rdf = new RandomAccessFile("log/log.txt", "rw");
            rdf.seek((int) rdf.length());
        } catch (IOException ex) {
            java.util.logging.Logger.getLogger(Logger.class.getName()).log(Level.SEVERE, null, ex);
            return;
        }

        try {

            rdf.writeBytes(Time.now() + ":    " + log + "\r\n");
            rdf.close();

        } catch (IOException ex) {
            java.util.logging.Logger.getLogger(Logger.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
}
