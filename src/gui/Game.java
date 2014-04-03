/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package gui;

import javafx.event.EventHandler;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;

/**
 *
 * @author pieterjan
 */
public class Game extends Pane {
    private Stickman player;
    private double x = 0;
    
    public Game(Stickman player) {
        this.requestFocus();
        this.player = player;
        initialize();
        movement();
    }
    
    private boolean initialize() {
        getChildren().add(player);
        return true;
    }
    
    private void movement() {
        setOnKeyPressed(new EventHandler<KeyEvent>() {

            @Override
            public void handle(KeyEvent t) {
                if (t.getCode() == KeyCode.RIGHT) {
                    x = x+10;
                    player.setTranslateX(x);
                }
                
                if(t.getCode() == KeyCode.LEFT) {
                    x = x-10;
                    player.setTranslateX(x);
                }
            }
        });
        
        
    }
}
