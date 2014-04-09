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
    
    public Frame(Game game, Controls controls) {
        this.game = game;
        this.controls = controls;
        buildFrame();
    }
    
    private void buildFrame() {
        setMinHeight(400);
        setMinWidth(600);
        setCenter(game);
        setBottom(controls);
    }
}
