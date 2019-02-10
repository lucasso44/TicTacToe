/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uk.ac.kingston.programming.TicTacToe.model;

/**
 *
 * @author lucas
 */
public class Score {
    private int o = 0;
    private int x = 0;

    /**
     * @return the o
     */
    public int getO() {
        return o;
    }

    /**
     * @param o the o to set
     */
    public void setO(int o) {
        this.o = o;
    }

    /**
     * @return the x
     */
    public int getX() {
        return x;
    }

    /**
     * @param x the x to set
     */
    public void setX(int x) {
        this.x = x;
    }
    
    public void setWinner(String player) {
        if(player.equals("X")) {
            setX(getX()+1);
        }
        else {
            setO(getO()+1);
        }
    }
    
    public String toHTML() {
        return "<html><body><b>X:</b>" + getX() + "<b>   O:</b>" + getO() + "</body></html>";
    }
    
}
