/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uk.ac.kingston.programming.TicTacToe;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import uk.ac.kingston.programming.TicTacToe.view.AppFrame;

/**
 *
 * @author lucas
 */
public class App {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            AppFrame appFrame = new AppFrame();
            appFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            appFrame.pack();
            appFrame.setLocationRelativeTo(null);
            appFrame.setVisible(true); 
        });
    }
}
