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
public class HeroView extends ImageView {

    private static Image hero = new Image(Main.class.getResourceAsStream("/images/heroes/Hero.png"));
    private static Image hero2 = new Image(Main.class.getResourceAsStream("/images/heroes/Hero2.png"));
    private static Image heroIdle = new Image(Main.class.getResourceAsStream("/images/heroes/HeroIdle.png"));

    public HeroView() {
        createHero();
    }

    protected void setHeroMove1() {
        setImage(hero);
    }

    protected void setHeroMove2() {
        setImage(hero2);
    }

    protected void setHeroIdle() {
        setImage(heroIdle);
    }

    private boolean createHero() {
        setHeroIdle();
        return true;
    }

}
