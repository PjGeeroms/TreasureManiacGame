/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package gui;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.EventHandler;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;

/**
 *
 * @author pieterjan
 */
public class Game extends Pane {
    private Hero player;
    private double x = 0;
    private int index = 0;
    final int CONTROLSHEIGHT = 200;
    
    public Game(Hero player) {
        this.requestFocus();
        this.player = player;
        initialize();
        movement();
    }
    
    private boolean initialize() {
        getChildren().add(player);
        
        heightProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> ov, Number oldHeight, Number newHeight) {
                double heightHero = player.getBoundsInLocal().getMaxY();
                player.setLayoutY(newHeight.doubleValue() - CONTROLSHEIGHT - heightHero);
            }
        });
        
        setFocusTraversable(true); // set focus on game
        return true;
    }
    
    private void movement() {
        setOnKeyPressed(new EventHandler<KeyEvent>() {

            @Override
            public void handle(KeyEvent t) {
                index++;
                if ( (index & 1) == 0 ) {
                   player.setHeroMove1();
                } else {
                    player.setHeroMove2();
                }
                
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
