/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import domein.DomeinController;
import domein.Hero;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

/**
 *
 * @author Robin
 */
public class DetailHero extends GridPane {

    private DomeinController controller;
    private ImageView ivAvatar, ivPower, ivDefense, ivSpeed, ivAwareness;

    protected Label lblDetailName, lblDetailPower, lblDetailDefense, lblDetailSpeed, lblDetailAwareness;
    private static Image iPower = new Image(Main.class.getResourceAsStream("/images/icons/Sword.png"));
    private static Image iDefense = new Image(Main.class.getResourceAsStream("/images/icons/Shield.png"));
    private static Image iSpeed = new Image(Main.class.getResourceAsStream("/images/icons/Speed.png"));
    private static Image iAwareness = new Image(Main.class.getResourceAsStream("/images/icons/Awareness.png"));

    /**
     * Constructor
     *
     * @param controller the domain controller
     */
    public DetailHero() {
        buildPanel();
    }

    public DetailHero(Hero hero) {
        buildPanel(hero);
    }

    private void buildPanel(Hero hero) {
        setMinHeight(120);
        setMaxHeight(120);
        setMaxWidth(250);
        setMinWidth(250);
        
        Image img=new Image(Main.class.getResourceAsStream("/images/heroes/" + hero.getAvatar()));
        ivAvatar = new ImageView(img);
        ivAvatar.setFitHeight(80);
        
        ivPower = new ImageView(iPower);
        ivPower.setFitHeight(24);
        ivPower.setFitWidth(24);

        ivDefense = new ImageView(iDefense);
        ivDefense.setFitHeight(24);
        ivDefense.setFitWidth(24);

        ivSpeed = new ImageView(iSpeed);
        ivSpeed.setFitHeight(24);
        ivSpeed.setFitWidth(24);

        ivAwareness = new ImageView(iAwareness);
        ivAwareness.setFitHeight(24);
        ivAwareness.setFitWidth(24);

        lblDetailName = new Label(hero.getName());
        lblDetailName.setFont(Font.font("Arial", FontWeight.BOLD, 20));
        lblDetailName.setMaxWidth(190);
        lblDetailName.setMinWidth(190);
        lblDetailPower = new Label(String.valueOf(hero.getPower()), ivPower);
        lblDetailPower.setMaxWidth(50);
        lblDetailPower.setMinWidth(50);
        lblDetailDefense = new Label(String.valueOf(hero.getDefense()), ivDefense);
        lblDetailDefense.setMaxWidth(50);
        lblDetailDefense.setMinWidth(50);
        lblDetailSpeed = new Label(String.valueOf(hero.getSpeed()), ivSpeed);
        lblDetailSpeed.setMaxWidth(50);
        lblDetailSpeed.setMinWidth(50);
        lblDetailAwareness = new Label(String.valueOf(hero.getAwareness()), ivAwareness);
        lblDetailAwareness.setMaxWidth(50);
        lblDetailAwareness.setMinWidth(50);
        HBox padding1 = new HBox();
        padding1.setMinWidth(20);

        add(ivAvatar, 0, 0, 1, 2);
        add(lblDetailName, 1, 0, 3, 1);
        add(lblDetailPower, 1, 1);
        add(lblDetailDefense, 1, 2);
        add(padding1, 2, 1, 1, 2);
        add(lblDetailSpeed, 3, 1);
        add(lblDetailAwareness, 3, 2);
    }

    /**
     * Build the standard panel
     */
    private void buildPanel() {
        ivPower = new ImageView(iPower);
        ivPower.setFitHeight(24);
        ivPower.setFitWidth(24);

        ivDefense = new ImageView(iDefense);
        ivDefense.setFitHeight(24);
        ivDefense.setFitWidth(24);

        ivSpeed = new ImageView(iSpeed);
        ivSpeed.setFitHeight(24);
        ivSpeed.setFitWidth(24);

        ivAwareness = new ImageView(iAwareness);
        ivAwareness.setFitHeight(24);
        ivAwareness.setFitWidth(24);

        lblDetailName = new Label("Player");
        lblDetailName.setFont(Font.font("Arial", FontWeight.BOLD, 20));
        lblDetailName.setMaxWidth(190);
        lblDetailName.setMinWidth(190);
        lblDetailPower = new Label("0", ivPower);
        lblDetailPower.setMaxWidth(50);
        lblDetailPower.setMinWidth(50);
        lblDetailDefense = new Label("0", ivDefense);
        lblDetailDefense.setMaxWidth(50);
        lblDetailDefense.setMinWidth(50);
        lblDetailSpeed = new Label("0", ivSpeed);
        lblDetailSpeed.setMaxWidth(50);
        lblDetailSpeed.setMinWidth(50);
        lblDetailAwareness = new Label("0", ivAwareness);
        lblDetailAwareness.setMaxWidth(50);
        lblDetailAwareness.setMinWidth(50);
        HBox padding1 = new HBox();
        padding1.setMinWidth(20);

        add(lblDetailName, 0, 0, 3, 1);
        add(lblDetailPower, 0, 1);
        add(lblDetailDefense, 0, 2);
        add(padding1, 1, 1, 1, 2);
        add(lblDetailSpeed, 2, 1);
        add(lblDetailAwareness, 2, 2);
    }

    /**
     * Set the text of the name-label
     *
     * @param lblDetailName new text in name-label
     */
    public void setName(String lblDetailName) {
        this.lblDetailName.setText(lblDetailName);
    }

    /**
     * Set the text of the power-label
     *
     * @param lblDetailPower new text in power-label
     */
    public void setPower(String lblDetailPower) {
        this.lblDetailPower.setText("" + lblDetailPower);
    }

    /**
     * Set the text of the defense-label
     *
     * @param lblDetailDefense new text in defense-label
     */
    public void setDefense(String lblDetailDefense) {
        this.lblDetailDefense.setText(lblDetailDefense + "");
    }

    /**
     * Set the text of the speed-label
     *
     * @param lblDetailSpeed new text in speed-label
     */
    public void setSpeed(String lblDetailSpeed) {
        this.lblDetailSpeed.setText("" + lblDetailSpeed);
    }

    /**
     * Set the text of the awareness-label
     *
     * @param lblDetailAwareness new text in awareness-label
     */
    public void setAwareness(String lblDetailAwareness) {
        this.lblDetailAwareness.setText("" + lblDetailAwareness);
    }
}
