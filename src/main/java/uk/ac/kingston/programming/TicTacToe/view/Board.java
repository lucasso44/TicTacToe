/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uk.ac.kingston.programming.TicTacToe.view;

import uk.ac.kingston.programming.TicTacToe.model.SquareMetric;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import uk.ac.kingston.programming.TicTacToe.controller.BoardListener;
import uk.ac.kingston.programming.TicTacToe.model.Score;

/**
 *
 * @author lucas
 */
public final class Board extends JPanel{
    
    private Score score = new Score();
    
    private boolean playCarol = true;
    private boolean playerAssist = false;
    private boolean easyMode = false;
    
    private String currentPlayer = "O";
    
    ArrayList<Square> squares = new ArrayList<>();
    
    private BoardListener boardListener;
    
    public Board() {
    }
    
    public void redraw() {
        
        removeAll();
        
        setLayout(new GridLayout(4,4));
        setBackground(Color.WHITE);       
        setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
        
        currentPlayer = "0";
        
        squares = new ArrayList<>();
        
        for(int x = 1; x <=4; x++) {
            for(int y = 1; y <= 4; y++) {
                Square square = new Square();
                squares.add(square);
                square.addMouseListener(new MouseListener() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        currentPlayer = currentPlayer.equals("X") ? "O" : "X";
                        square.setPlayer(currentPlayer);
                        if(checkForEndOfGame()) {
                            return;
                        }
                        if(isPlayCarol()) {
                            currentPlayer = currentPlayer.equals("X") ? "O" : "X";
                            pickBestSquareToWin("O").setPlayer(currentPlayer);
                            if(checkForEndOfGame()) {
                                return;
                            }
                            if(isPlayerAssist()) {
                                pickBestSquareToWin("X").setBackground(new Color(197,202,233));
                            }
                        }
                    }

                    @Override
                    public void mousePressed(MouseEvent e) {
                    }

                    @Override
                    public void mouseReleased(MouseEvent e) {
                    }

                    @Override
                    public void mouseEntered(MouseEvent e) {
                        square.setBackground(new Color(48,59,89));
                    }

                    @Override
                    public void mouseExited(MouseEvent e) {
                        square.setBackground(new Color(36,43,69));
                    }
                });
                add(square);
            }
        }
        
        updateUI();
    }

    public Square pickBestSquareToWin(String player) {
        
        ArrayList<Square> freeSquares = new ArrayList<>();
        squares.stream().filter((square) -> (!square.isPlayerSet())).forEachOrdered((square) -> {
            freeSquares.add(square);
        });
        
        Square[][] squareChecks = new Square[10][4];	 	       	   	      	       			
        squareChecks[0] = new Square[] {squares.get(0), squares.get(1), squares.get(2), squares.get(3)};
        squareChecks[1] = new Square[] {squares.get(4), squares.get(5), squares.get(6), squares.get(7)};
        squareChecks[2] = new Square[] {squares.get(8), squares.get(9), squares.get(10), squares.get(11)};
        squareChecks[3] = new Square[] {squares.get(12), squares.get(13), squares.get(14), squares.get(15)};
        squareChecks[4] = new Square[] {squares.get(0), squares.get(4), squares.get(8), squares.get(12)};
        squareChecks[5] = new Square[] {squares.get(1), squares.get(5), squares.get(9), squares.get(13)};
        squareChecks[6] = new Square[] {squares.get(2), squares.get(6), squares.get(10), squares.get(14)};
        squareChecks[7] = new Square[] {squares.get(3), squares.get(7), squares.get(11), squares.get(15)};
        squareChecks[8] = new Square[] {squares.get(0), squares.get(5), squares.get(10), squares.get(15)};
        squareChecks[9] = new Square[] {squares.get(3), squares.get(6), squares.get(9), squares.get(12)};
        
        ArrayList<SquareMetric> squareMetrics = new ArrayList<>();
        
        freeSquares.forEach((freeSquare) -> {
            for(Square[] squareCheck : squareChecks) {
                for(Square potentialSquare : squareCheck) {
                    if(potentialSquare.equals(freeSquare)) {
                        squareMetrics.add(getSquareMetric(freeSquare, squareCheck));
                    }
                }
            }
        });
        
        SquareMetric bestSquareMetric = squareMetrics.get(0);
        
        if(player.equals("O")) {
            for(SquareMetric squareMetric : squareMetrics) {
                if(squareMetric.isCanWinO() && squareMetric.getNumberOfMovesO() == 1) {
                    return squareMetric.getSquare();
                } 
               
                if(!isEasyMode()) {
                    if(squareMetric.isCanWinX() && squareMetric.getNumberOfMovesX() == 1) {
                        return squareMetric.getSquare();
                    }
                    else if(squareMetric.isCanWinX() && squareMetric.getNumberOfMovesX() == 2) {
                        return squareMetric.getSquare();
                    }
                }
                
                if(bestSquareMetric.getNumberOfMovesO() > squareMetric.getNumberOfMovesO()) {
                    bestSquareMetric = squareMetric;
                }
            }
        }
        else {
            for(SquareMetric squareMetric : squareMetrics) {
                if(squareMetric.isCanWinX() && squareMetric.getNumberOfMovesX() == 1) {
                    return squareMetric.getSquare();
                } else if(squareMetric.isCanWinO() && squareMetric.getNumberOfMovesO() == 1) {
                    return squareMetric.getSquare();
                } else if(squareMetric.isCanWinO() && squareMetric.getNumberOfMovesO() == 2) {
                    return squareMetric.getSquare();
                }
                if(bestSquareMetric.getNumberOfMovesX() > squareMetric.getNumberOfMovesX()) {
                    bestSquareMetric = squareMetric;
                }
            }            
        }
        return bestSquareMetric.getSquare();
    }
    
    public SquareMetric getSquareMetric(Square freeSquare, Square[] squareCheck) {
        
        SquareMetric squareMetric = new SquareMetric(freeSquare);
        
        int squaresFilledO = getSquaresFilled(squareCheck, "O");
        int squaresFilledX = getSquaresFilled(squareCheck, "X");
        
        if(squaresFilledO > 0 && squaresFilledX == 0) {
            squareMetric.setCanWinO(true);
            squareMetric.setNumberOfMovesO(4 - squaresFilledO);
            squareMetric.setCanWinX(false);
        }
        else if(squaresFilledO == 0 && squaresFilledX > 0) {
            squareMetric.setCanWinO(false);
            squareMetric.setCanWinX(true);            
            squareMetric.setNumberOfMovesX(4 - squaresFilledX);
        }
        else if(squaresFilledO == 0 && squaresFilledX == 0) {
            squareMetric.setCanWinO(false);
            squareMetric.setNumberOfMovesO(4);
            squareMetric.setCanWinX(true);            
            squareMetric.setNumberOfMovesX(4);            
        }
        else if(squaresFilledO > 0 && squaresFilledX > 0) {
            squareMetric.setCanWinO(false);
            squareMetric.setCanWinX(false);            
        }
        
        return squareMetric;
    }
    
    public int getSquaresFilled(Square[] squareCheck, String player) {
        int squaresFilled = 0;
        for(Square square : squareCheck) {
            if(square.getPlayer().equals(player)) {
                squaresFilled++;
            }
        }
        return squaresFilled;
    }
    
    public boolean checkForEndOfGame() {

        Square[][] squareChecks = new Square[10][4];	 	       	   	      	       			
        squareChecks[0] = new Square[] {squares.get(0), squares.get(1), squares.get(2), squares.get(3)};
        squareChecks[1] = new Square[] {squares.get(4), squares.get(5), squares.get(6), squares.get(7)};
        squareChecks[2] = new Square[] {squares.get(8), squares.get(9), squares.get(10), squares.get(11)};
        squareChecks[3] = new Square[] {squares.get(12), squares.get(13), squares.get(14), squares.get(15)};
        squareChecks[4] = new Square[] {squares.get(0), squares.get(4), squares.get(8), squares.get(12)};
        squareChecks[5] = new Square[] {squares.get(1), squares.get(5), squares.get(9), squares.get(13)};
        squareChecks[6] = new Square[] {squares.get(2), squares.get(6), squares.get(10), squares.get(14)};
        squareChecks[7] = new Square[] {squares.get(3), squares.get(7), squares.get(11), squares.get(15)};
        squareChecks[8] = new Square[] {squares.get(0), squares.get(5), squares.get(10), squares.get(15)};
        squareChecks[9] = new Square[] {squares.get(3), squares.get(6), squares.get(9), squares.get(12)};
        
        String[] players = new String[] {"X", "O" };
        
        for(String player : players) {	 	       	   	      	       			
          for(Square[] squareCheck : squareChecks) {	 	       	   	      	       			
            if(isWinningLine(squareCheck[0], squareCheck[1], squareCheck[2], squareCheck[3], player)) {	 	       	   	      	       			
              for(Square square : squareCheck) {
                  square.setBackground(new Color(197,202,233));
              }
              score.setWinner(player);
              if(boardListener != null) {
                  boardListener.onNewScore(score);
              }
              drawWinner(player);
              return true;
            }	 	       	   	      	       			
          }	 	       	   	      	       			
        }
        
        int squaresSet = 0;
        for(Square square : squares) {
            if(square.isPlayerSet()) {
                squaresSet++;
            }
        }
        
        if(squaresSet == squares.size()) {
            drawWinner("");
            return true;
        }
        
        return false;
    }
    
    private boolean isWinningLine(Square s1, Square s2, Square s3, Square s4, String player) {	 	       	   	      	       			
        return (isMatch(s1, player) && isMatch(s2, player) && isMatch(s3, player) && isMatch(s4, player));	 	       	   	      	       			
    }    
    
    private boolean isMatch(Square square, String player) {	 	       	   	      	       			
        return square.getPlayer().equals(player);	 	       	   	      	       			
    }

    /**
     * @param boardListener the boardListener to set
     */
    public void setBoardListener(BoardListener boardListener) {
        this.boardListener = boardListener;
    }

    private void drawWinner(String player) {
        
        removeAll();
        
        MouseListener mouseListener = new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                redraw();
                removeMouseListener(this);
            }

            @Override
            public void mousePressed(MouseEvent e) {
                
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                
            }

            @Override
            public void mouseExited(MouseEvent e) {
                
            }
        };
        
        addMouseListener(mouseListener);
        
        setBackground(new Color(36,43,69));
        
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setBorder(BorderFactory.createEmptyBorder(50, 50, 50, 50));
        
        JPanel playerPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        playerPanel.setOpaque(false);
        playerPanel.setBorder(BorderFactory.createEmptyBorder(50, 50, 0, 50));
        
        if(player.equals("")) {
            JLabel playerXLabel = new JLabel("X");
            playerXLabel.setFont(new Font("Sergio UI", Font.BOLD, 102));
            playerXLabel.setForeground(new Color(27,188,155));
            playerPanel.add(playerXLabel);

            JLabel playerOLabel = new JLabel("O");
            playerOLabel.setFont(new Font("Sergio UI", Font.BOLD, 102));
            playerOLabel.setForeground(new Color(226,80,67));
            playerPanel.add(playerOLabel);              
        }
        else {
            JLabel playerLabel = new JLabel(player);
            playerLabel.setFont(new Font("Sergio UI", Font.BOLD, 102));
            playerLabel.setForeground(player.equals("X") ? new Color(27,188,155) : new Color(226,80,67));
            playerPanel.add(playerLabel);            
        }
        
        add(playerPanel);

        JPanel winnerPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        winnerPanel.setOpaque(false);
        winnerPanel.setBorder(BorderFactory.createEmptyBorder(0, 50, 50, 50));
        
        JLabel winnerLabel = new JLabel(player.equals("") ? "DRAW!" : "WINNER!");
        winnerLabel.setFont(new Font("Sergio UI", Font.BOLD, 72));
        winnerLabel.setForeground(Color.WHITE);
        
        winnerPanel.add(winnerLabel);
        
        add(winnerPanel);
        
        updateUI();
    }

    public Score getScore() {
        return score;
    }

    /**
     * @return the playCarol
     */
    public boolean isPlayCarol() {
        return playCarol;
    }

    /**
     * @param playCarol the playCarol to set
     */
    public void setPlayCarol(boolean playCarol) {
        this.playCarol = playCarol;
    }

    /**
     * @return the playerAssist
     */
    public boolean isPlayerAssist() {
        return playerAssist;
    }

    /**
     * @param playerAssist the playerAssist to set
     */
    public void setPlayerAssist(boolean playerAssist) {
        this.playerAssist = playerAssist;
    }

    /**
     * @return the easyMode
     */
    public boolean isEasyMode() {
        return easyMode;
    }

    /**
     * @param easyMode the easyMode to set
     */
    public void setEasyMode(boolean easyMode) {
        this.easyMode = easyMode;
    }
}
