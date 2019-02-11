/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uk.ac.kingston.programming.TicTacToe.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author lucas
 */
public final class Square extends JPanel{
    
    private final JLabel label;
    private boolean playerSet = false;
    private int id = 0;
    public Square(boolean isNormalSize, int id) {
        super();
        
        this.id = id;
        
        setLayout(new FlowLayout(FlowLayout.CENTER));
        setBackground(new Color(36,43,69));
        
        label = new JLabel();
        
        if(isNormalSize) {
            setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(Color.WHITE, 1, false), BorderFactory.createEmptyBorder(30, 30, 30, 30)));
            setPreferredSize(new Dimension(160, 160));
            label.setFont(new Font("Sergio UI", Font.BOLD, 62));
        }
        else {
            setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(Color.WHITE, 1, false), BorderFactory.createEmptyBorder(20, 20, 20, 20)));
            setPreferredSize(new Dimension(120, 120));
            label.setFont(new Font("Sergio UI", Font.BOLD, 52));            
        }
        
        
        add(label);
    }
    
    public void setPlayer(String player){
        if(isPlayerSet()) {
            return;
        }
        setBackground(new Color(36,43,69));
        label.setForeground(player.equals("X") ? new Color(27,188,155) : new Color(226,80,67));
        label.setText(player);
        setPlayerSet(true);
        updateUI();
    }
    
    public String getPlayer(){
        return label.getText();
    }

    /**
     * @return the playerSet
     */
    public boolean isPlayerSet() {
        return playerSet;
    }

    /**
     * @param playerSet the playerSet to set
     */
    public void setPlayerSet(boolean playerSet) {
        this.playerSet = playerSet;
    }

    /**
     * @return the id
     */
    public int getId() {
        return id;
    }
}
