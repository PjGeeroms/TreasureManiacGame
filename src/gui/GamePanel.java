package gui;

import javafx.event.EventHandler;
import javafx.geometry.Orientation;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;

/**
 *
 * @author pieterjan
 */
public class GamePanel extends BorderPane{
    private Stickman player;
    
    public GamePanel(Stickman player) {
        this.player = player;
        buildGui();
    }
    
    private void buildGui() {
        FlowPane main = new FlowPane(Orientation.HORIZONTAL);
        main.getChildren().addAll(player);
        setCenter(main);
    }
    
    private void control() {
        this.setOnKeyPressed(new EventHandler<KeyEvent>() {

            @Override
            public void handle(KeyEvent t) {
                if (t.getCode() == KeyCode.ENTER) {
                    player.setX(player.getX() + 5);
                }
            }
        });
    }
    
    
}
