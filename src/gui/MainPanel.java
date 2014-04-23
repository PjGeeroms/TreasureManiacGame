/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import domein.DomeinController;
import domein.Hero;
import javafx.geometry.Pos;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.StackPane;

/**
 *
 * @author Robin
 */
public class MainPanel extends StackPane {

    private Frame frame;
    private HeroPanel heroPanel;
    private Hero hero;
    private DetailHero detail;

    public MainPanel() {
        hero = null;
        detail=null;
        HeroView player = new HeroView();
        Game game = new Game(player);
        Controls controls = new Controls();
        frame = new Frame(game, controls);

        HeroPanel heroPanel = new HeroPanel(new DetailHero(), this);
        heroPanel.setAlignment(Pos.TOP_CENTER);
        heroPanel.setMaxWidth(350);
        heroPanel.setMaxHeight(400);

        /*  stage.setWidth(800);
         stage.setHeight(600);*/
        getChildren().addAll(frame, heroPanel);
        game.setFocusTraversable(false);
    }

    /**
     * Focus the game and enable movement control of the hero. Will be used
     * after creating a hero.
     */
    public void focusGame() {
        frame.focusGame();
    }

    public void initializeHero(Hero hero) {
        this.hero = hero;
        
        detail = new DetailHero(hero);
        FlowPane pane=new FlowPane();
        detail.getStyleClass().add("detailhero");
        detail.getStylesheets().add("css/gamebackground.css");
        detail.setMinHeight(80);
        detail.setMaxHeight(80);
        detail.setMaxWidth(160);
        detail.setMinWidth(160);
        pane.getChildren().addAll(detail);
        getChildren().addAll(pane);
    }

    public Hero getHero() //hero zal null zijn indien niet geïnitialiseerd
    {
        return hero;
    }
    
    public DetailHero getDetail()
    {
        return detail;
    }
}
