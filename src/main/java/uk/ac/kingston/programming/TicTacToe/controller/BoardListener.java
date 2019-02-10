/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uk.ac.kingston.programming.TicTacToe.controller;

import uk.ac.kingston.programming.TicTacToe.model.Score;

/**
 *
 * @author lucas
 */
public interface BoardListener {
    public void onCancelClicked();
    public void onNewScore(Score score);
}
