/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package gui;

import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.shape.Rectangle;

/**
 *
 * @author pieterjan
 */
public class HeroView extends ImageView{
    private static Image heroLeft = new Image(Main.class.getResourceAsStream("/images/heroes/left.gif"));
    private static Image heroRight = new Image(Main.class.getResourceAsStream("/images/heroes/right.gif"));
    private static Image heroIdleRight = new Image(Main.class.getResourceAsStream("/images/heroes/HeroIdleRight.gif"));
    private static Image heroIdleLeft = new Image(Main.class.getResourceAsStream("/images/heroes/HeroIdleLeft.gif"));
    
    public HeroView () {
        createHero();
    }
    
    protected void setHeroMoveLeft() {
        setImage(heroLeft);
    }
    
    protected void setHeroMoveRight() {
        setImage(heroRight);
    }
    
    protected void setHeroIdleRight() {
        setImage(heroIdleRight);
    }
    
    protected void setHeroIdleLeft() {
        setImage(heroIdleLeft);
    }
    
    private boolean createHero() {
        setHeroIdleRight();
        return true;
    }

}
