/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package gui;

import domein.Utility;
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
    final int CONTROLSHEIGHT = 150;
    
    public Game(Hero player) {
        this.requestFocus();
        this.player = player;
        initialize();
        movement();
    }
    
    private boolean initialize() {
        getStylesheets().add("css/gamebackground.css");
        generateBackground();
        // add player to game
        getChildren().add(player);
        
        // add listener to height on window for dynamic champion placement
        heightProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> ov, Number oldHeight, Number newHeight) {
                double heightHero = player.getBoundsInLocal().getMaxY();
                player.setLayoutY(newHeight.doubleValue() - CONTROLSHEIGHT - heightHero);
            }
        });
        
        /**
         * set focus on game
         * True = focus on game, enables the movement with keys
         * False = focus isn't on game anymore, key listeners will not work
         */
        setFocusTraversable(true); 
        
        return true;
    }
    
    /**
     * Dynamic generating FloorTiles
     */
    private void generateBackground() {
        int background = Utility.generateRandom(0, 2);
        String backgroundType = "";
        switch (background) {
            case 0: 
                backgroundType = "ruinedcity";
                break;
            case 1: 
                backgroundType = "sky";
                break;
            case 2:
                backgroundType = "stonewall";
                break;
            default:
                backgroundType = "stonewall";
                break;
        }
        this.getStyleClass().add(backgroundType);
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
