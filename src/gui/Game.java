/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package gui;

import domein.Utility;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.PathTransition;
import javafx.animation.Timeline;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;
import javafx.util.Duration;

/**
 *
 * @author pieterjan
 */
public class Game extends Pane {
    private HeroView player;
    private double x = 0;
    private int index = 0;
    private boolean enableMovement = true;
    final int CONTROLSHEIGHT = 150;
    final Timeline monsterMoveTo = new Timeline();
    private static Image treasure = new Image(Main.class.getResourceAsStream("/images/treasure/chest.png"));
    private static ImageView ivTreasure = new ImageView(treasure);
    
    private static Image monster = new Image(Main.class.getResourceAsStream("/images/monster/TheTree.gif"));
    private static ImageView ivMonster = new ImageView(monster);
    
    /**
     * The game 'engine'
     * @param player the hero to be added in the game
     */
    public Game(HeroView player) {
        this.requestFocus();
        this.player = player;
        initialize();
        movement();
    }
    
    /**
     * Settings for the game
     * Css is imported here
     * Player gets added here
     * HeightPropertyListner for dynamic hero placement
     * @return True if successful, False if failed
     */
    private boolean initialize() {
        getStylesheets().add("css/gamebackground.css");
        generateBackground();
        // add player to game
        ivTreasure.setFitWidth(100);
        ivTreasure.setFitHeight(90);
        getChildren().addAll(player, ivTreasure, ivMonster);
        
        // monster movement
        monsterMoveTo.setCycleCount(1);
        monsterMoveTo.setAutoReverse(false);
        final KeyValue kv = new KeyValue(ivMonster.xProperty(), -200);
        final KeyFrame kf = new KeyFrame(Duration.millis(500), kv);
        monsterMoveTo.getKeyFrames().add(kf);
        
        // add listener to height on window for dynamic champion placement
        heightProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> ov, Number oldHeight, Number newHeight) {
                double heightHero = player.getBoundsInLocal().getMaxY();
                player.setLayoutY(newHeight.doubleValue() - CONTROLSHEIGHT - heightHero);
                
                double heightTreasure = ivTreasure.getBoundsInLocal().getMaxY();
                ivTreasure.setLayoutY(newHeight.doubleValue() - CONTROLSHEIGHT - heightTreasure);
                
                double heightMonster = ivMonster.getBoundsInLocal().getMaxY();
                ivMonster.setLayoutY(newHeight.doubleValue() - CONTROLSHEIGHT - heightMonster);
            }
        });
        
        widthProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> ov, Number t, Number newWidth) {
                double widthTreasure = ivTreasure.getBoundsInLocal().getMaxX();
                ivTreasure.setLayoutX(newWidth.doubleValue() - widthTreasure);
                
                double widthMonster = ivMonster.getBoundsInLocal().getMaxX();
                ivMonster.setLayoutX(newWidth.doubleValue() - widthMonster);
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
    
    private void triggers() {
        System.out.println(player.getTranslateX());
        if (player.getTranslateX() == 180) {
            //Monster spawns
            enableMovement = false;
            monsterMoveTo.play();
            if (monsterMoveTo.getStatus() == Animation.Status.STOPPED) {
                // werkt niet
                enableMovement = true;
            }
            
        }
        
        
    }
    
    /**
     * Movement of the hero
     * Also contains the logic for the animating
     */
    private void movement() {
        setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent t) {
                triggers();
                if (enableMovement) {
                   if (t.getCode() == KeyCode.RIGHT) {
                        x = x+15;
                        player.setHeroMoveRight();
                        player.setTranslateX(x);
                    }

                    if (t.getCode() == KeyCode.LEFT) {
                        x = x-15;
                        player.setHeroMoveLeft();
                        player.setTranslateX(x);
                    } 
                }
                
            }
        });
        
        setOnKeyReleased(new EventHandler<KeyEvent>() {

            @Override
            public void handle(KeyEvent t) {
                if (t.getCode() == KeyCode.RIGHT) {
                    player.setHeroIdleRight();
                }
                if (t.getCode() == KeyCode.LEFT) {
                    player.setHeroIdleLeft();
                }
            }
        });
        
        
    }
}
