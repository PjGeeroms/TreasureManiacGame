/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import domein.Utility;
import javafx.scene.layout.Pane;

/**
 *
 * @author pieterjan
 */
public class Controls extends Pane {

    /**
     * Initializing bottom box of the game, contains the floor as background.
     */
    public Controls() {
        setMinHeight(150);
        getStylesheets().add("css/gameplatform.css");   // add stylesheet
        generatePlatform();
    }

    /**
     * Dynamic generating FloorTiles
     */
    private void generatePlatform() {
        int platform = Utility.generateRandom(0, 1);
        String platformType = "";
        switch (platform) {
            case 0:
                platformType = "wasteland";
                break;
            case 1:
                platformType = "greenhills";
                break;
            default:
                platformType = "greenhills";
                break;
        }
        this.getStyleClass().add(platformType);
    }
}
