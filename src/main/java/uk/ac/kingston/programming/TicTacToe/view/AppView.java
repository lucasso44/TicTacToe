/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uk.ac.kingston.programming.TicTacToe.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import uk.ac.kingston.programming.TicTacToe.controller.BoardListener;
import uk.ac.kingston.programming.TicTacToe.model.Score;

/**
 *
 * @author lucas
 */
public final class AppView extends JFrame {
    
    private JLabel scoreLabel;
    
    private final JButton restartButton;
    
    public AppView() {
        
        setLayout(new BorderLayout());
        
        setTitle("Tic Tac Toe");
        
        ImageIcon image = new ImageIcon("image.jpg");
        setIconImage(image.getImage());
        
        Board board = new Board();
        board.setBoardListener(new BoardListener() {
            @Override
            public void onCancelClicked() {
                dispose();
            }

            @Override
            public void onNewScore(Score score) {
                scoreLabel.setText(score.toHTML());
            }
        });
        board.redraw();
        add(board, BorderLayout.CENTER);
        
        JPanel scorePanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        scorePanel.setBackground(Color.WHITE);
        
        scoreLabel = new JLabel(board.getScore().toHTML());
        scoreLabel.setFont(new Font("Sergio UI", Font.BOLD, 24));
        
        scorePanel.add(scoreLabel);
        
        add(scorePanel, BorderLayout.NORTH);
        
        JPanel optionPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));    
        optionPanel.setBackground(Color.WHITE);
        
        JCheckBox playCarolCheckBox = new JCheckBox("Play Carol");
        playCarolCheckBox.addActionListener((ActionEvent e) -> {
            board.setPlayCarol(playCarolCheckBox.isSelected());
        });        
        playCarolCheckBox.setSelected(board.isPlayCarol());
        
        optionPanel.add(playCarolCheckBox);

        JCheckBox easyModeCheckBox = new JCheckBox("Easy Mode");
        easyModeCheckBox.addActionListener((ActionEvent e) -> {
            board.setEasyMode(easyModeCheckBox.isSelected());
        });     
        easyModeCheckBox.setSelected(board.isEasyMode());
        
        optionPanel.add(easyModeCheckBox);
        
        JCheckBox playerAssistCheckBox = new JCheckBox("Player Assist");
        playerAssistCheckBox.addActionListener((ActionEvent e) -> {
            board.setPlayerAssist(playerAssistCheckBox.isSelected());
        });
        playerAssistCheckBox.setSelected(board.isPlayerAssist());
        
        optionPanel.add(playerAssistCheckBox);

        JCheckBox normalSizeCheckBox = new JCheckBox("4 x 4");
        normalSizeCheckBox.addActionListener((ActionEvent e) -> {
            board.setNormalSize(!normalSizeCheckBox.isSelected());
            board.redraw();
        });
        normalSizeCheckBox.setSelected(!board.isNormalSize());
        
        optionPanel.add(normalSizeCheckBox);
        
        restartButton = new JButton("Restart Game");
        restartButton.setForeground(new Color(36,43,69));
        restartButton.setBackground(Color.WHITE);
        restartButton.addActionListener((ActionEvent e) -> {
            board.redraw();
        });
        optionPanel.add(restartButton);
        
        add(optionPanel, BorderLayout.SOUTH);     
    }
}
