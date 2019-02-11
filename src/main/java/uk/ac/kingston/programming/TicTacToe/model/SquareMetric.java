/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uk.ac.kingston.programming.TicTacToe.model;

import uk.ac.kingston.programming.TicTacToe.view.Square;

/**
 *
 * @author lucas
 */
public class SquareMetric {
    private boolean canWinO = false;
    private boolean canWinX = false;
    private int numberOfMovesO = 0;    
    private int numberOfMovesX = 0;
    private Square square;
    
    public SquareMetric(Square square) {
        this.square = square;
    }
    
    /**
     * @return the canWinO
     */
    public boolean isCanWinO() {
        return canWinO;
    }

    /**
     * @param canWinO the canWinO to set
     */
    public void setCanWinO(boolean canWinO) {
        this.canWinO = canWinO;
    }

    /**
     * @return the canWinX
     */
    public boolean isCanWinX() {
        return canWinX;
    }

    /**
     * @param canWinX the canWinX to set
     */
    public void setCanWinX(boolean canWinX) {
        this.canWinX = canWinX;
    }

    /**
     * @return the numberOfMovesO
     */
    public int getNumberOfMovesO() {
        return numberOfMovesO;
    }

    /**
     * @param numberOfMovesO the numberOfMovesO to set
     */
    public void setNumberOfMovesO(int numberOfMovesO) {
        this.numberOfMovesO = numberOfMovesO;
    }

    /**
     * @return the numberOfMovesX
     */
    public int getNumberOfMovesX() {
        return numberOfMovesX;
    }

    /**
     * @param numberOfMovesX the numberOfMovesX to set
     */
    public void setNumberOfMovesX(int numberOfMovesX) {
        this.numberOfMovesX = numberOfMovesX;
    }

    /**
     * @return the square
     */
    public Square getSquare() {
        return square;
    }
    
    @Override
    public String toString() {
        return getSquare().getId() + " O:" + isCanWinO() + "/" + getNumberOfMovesO() + " X: " + isCanWinX() + "/" + getNumberOfMovesX();
    }
}
