/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package gui;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

/**
 *
 * @author pieterjan
 */
public class Main extends Application {
    private static Image logo = new Image(Main.class.getResourceAsStream("/images/icons/Logo_icon.png"));
    
    @Override
    public void start(Stage stage) {
       GamePanel root = new GamePanel();
        
       Scene scene = new Scene(root);
       stage.setScene(scene);
       stage.setTitle("Treasure Maniac");
       stage.setFullScreen(true);
       stage.getIcons().add(logo);
       stage.show();
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
