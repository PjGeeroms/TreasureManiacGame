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

    private HeroView player;
    private double x = 0;
    private int index = 0;
    final int CONTROLSHEIGHT = 150;

    /**
     * The game 'engine'
     *
     * @param player the hero to be added in the game
     */
    public Game(HeroView player) {
        this.requestFocus();
        this.player = player;
        initialize();
        movement();
    }

    /**
     * Settings for the game Css is imported here Player gets added here
     * HeightPropertyListner for dynamic hero placement
     *
     * @return True if successful, False if failed
     */
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
         * set focus on game True = focus on game, enables the movement with
         * keys False = focus isn't on game anymore, key listeners will not work
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

    /**
     * Movement of the hero Also contains the logic for the animating
     */
    private void movement() {
        setOnKeyPressed(new EventHandler<KeyEvent>() {

            @Override
            public void handle(KeyEvent t) {
                if ((index & 1) == 0) {
                    player.setHeroMove1();
                } else {
                    player.setHeroMove2();
                }

                if (t.getCode() == KeyCode.RIGHT) {
                    index++;
                    x = x + 10;
                    player.setTranslateX(x);
                }
            }
        });

        setOnKeyReleased(new EventHandler<KeyEvent>() {

            @Override
            public void handle(KeyEvent t) {
                if (t.getCode() == KeyCode.RIGHT) {
                    player.setHeroIdle();
                }
            }
        });

    }
}
