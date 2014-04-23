/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import domein.DomeinController;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.StackPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 *
 * @author pieterjan
 */
public class Main extends Application {

    private MainPanel main;
    private static Image logo = new Image(Main.class.getResourceAsStream("/images/icons/Logo_icon.png"));
    private double x = 0;
    private DomeinController controller;

    @Override
    public void start(final Stage stage) {
        controller = new DomeinController();
        
        main = new MainPanel();

        Scene gameScene = new Scene(main);

        stage.setScene(gameScene);
        stage.setTitle("Treasure Maniac");
        stage.setFullScreen(false);
        stage.setResizable(false);
        stage.setWidth(800);
        stage.setHeight(600);
        stage.getIcons().add(logo);
        stage.show();

        //HeroWindow heroWindow = new HeroWindow(stage, "Create your hero", heroPanel);
        //heroWindow.showAndWait();
    }

    /**
     * The main() method is ignored in correctly deployed JavaFX application.
     * main() serves only as fallback in case the application can not be
     * launched through deployment artifacts, e.g., in IDEs with limited FX
     * support. NetBeans ignores main().
     *
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

}
