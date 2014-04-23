/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import domein.DomeinController;
import javafx.geometry.Pos;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.StackPane;

/**
 *
 * @author pieterjan
 */
public class Frame extends BorderPane {

    private Game game;
    private Controls controls;
    private DomeinController controller;

    /**
     * Initialize the frame for the game
     *
     * @param game The game to be added to the Frame
     * @param controls The controls to be added to the game
     */
    public Frame(Game game, Controls controls) {
        this.game = game;
        this.controls = controls;
        this.controller = new DomeinController();
        buildFrame();
    }

    /**
     * Configuration of the Frame
     */
    private void buildFrame() {
        setCenter(game);
        setBottom(controls);
    }
    
    /**
     * Focus the game and enable movement control of the hero.
     * Will be used while creating the MainPanel.
     */
    public void focusGame()
    {
        game.setFocusTraversable(true);
    }
}
