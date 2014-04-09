/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package gui;

import javafx.scene.layout.BorderPane;

/**
 *
 * @author pieterjan
 */
public class Frame extends BorderPane {
    private Game game;
    private Controls controls;
    
    /**
     * Initialize the frame for the game
     * @param game The game to be added to the Frame
     * @param controls The controls to be added to the game
     */
    public Frame(Game game, Controls controls) {
        this.game = game;
        this.controls = controls;
        buildFrame();
    }
    
    /**
     * Configuration of the Frame
     */
    private void buildFrame() {
        setCenter(game);
        setBottom(controls);
    }
}
